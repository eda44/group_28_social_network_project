package ru.skillbox.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skillbox.model.Post;
import ru.skillbox.model.Tag;
import ru.skillbox.model.TagInterface;
import ru.skillbox.request.PostTagRequest;
import ru.skillbox.response.TagResponse;
import ru.skillbox.response.post.PostTagResponse;
import ru.skillbox.service.PostService;
import ru.skillbox.service.TagService;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/tags")
public class TagController implements TagInterface {

    private final TagService tagService;
    private final PostService postService;

    @Autowired
    public TagController(TagService tagService, PostService postService) {
        this.tagService = tagService;
        this.postService = postService;
    }

    @Override
    @GetMapping
    public ResponseEntity<PostTagResponse> getTags(Tag tag, Integer offset, Integer itemPerPage) {
        PostTagResponse response = new PostTagResponse();
        response.setTotal(6);
        response.setOffset(offset);
        response.setPerPage(itemPerPage);
        response.setDataPosts(postService.getAllPosts());
        response.setData(List.of(tag));
        response.setTimestamp(LocalDateTime.now().toEpochSecond(ZoneOffset
                .systemDefault().getRules().getOffset(LocalDateTime.now())));
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{postId}")
    public ResponseEntity<PostTagResponse> putTag(@PathVariable String postId, @RequestBody PostTagRequest request) {
        Post post = postService.getPostById(Long.parseLong(postId));
        List<Tag> tagList = new ArrayList<>();
        for (String s : request.getTag()) {
            Tag tag = new Tag();
            tag.setTag(s);
            tagList.add(tag);
            tagService.saveTag(tag);
        }
        post.setTags(tagList);
        PostTagResponse response = new PostTagResponse();
        response.setData(tagList);
        response.setTotal(6);
        response.setTimestamp(LocalDateTime.now().toEpochSecond(ZoneOffset.systemDefault()
                .getRules().getOffset(LocalDateTime.now())));
        response.setOffset(1);
        response.setPerPage(5);
        response.setDataPosts(List.of(post));
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{postId}/{tagId}")
    public ResponseEntity<TagResponse> deleteTag(@PathVariable String postId, @PathVariable String tagId) {
        TagResponse response = new TagResponse();
        response.setMessage("ok");
        response.setTimestamp(LocalDateTime.now().toEpochSecond(ZoneOffset
                .systemDefault().getRules().getOffset(LocalDateTime.now())));
        Post post = postService.getPostById(Long.parseLong(postId));
        if (post.getTags().contains(tagService.findTagById(Long.parseLong(tagId))))
            tagService.deleteTagById(Long.parseLong(tagId));
        return ResponseEntity.ok(response);
    }
}
