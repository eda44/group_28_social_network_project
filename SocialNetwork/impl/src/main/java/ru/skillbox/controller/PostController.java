package ru.skillbox.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skillbox.dto.Pageable;
import ru.skillbox.exception.UserNotFoundException;
import ru.skillbox.model.*;
import ru.skillbox.request.PostAddRequest;
import ru.skillbox.response.ComplaintResponse;
import ru.skillbox.response.post.PostAddResponse;
import ru.skillbox.response.post.PostComplaintResponse;
import ru.skillbox.response.post.PostResponse;
import ru.skillbox.service.PersonService;
import ru.skillbox.service.PostLikeService;
import ru.skillbox.service.PostService;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

@RestController
public class PostController implements PostInterface {

    private final PostService postService;
    private final PostLikeService postLikeService;
    private final PersonService personService;

    @Autowired
    public PostController(PostService postService, PostLikeService postLikeService, PersonService personService) {
        this.postService = postService;
        this.postLikeService = postLikeService;
        this.personService = personService;
    }

    @PostMapping("/api/v1/post")
    public ResponseEntity<PostAddResponse> addNewPost(@RequestBody PostAddRequest request) {
        try {
            Post post = new Post();
            Person person = personService.setPerson();
            PostLike postLike = postLikeService.setPostLike();
            post.setTitle(request.getTitle());
            post.setPostText(request.getPostText());
            List<Tag> tagList = new ArrayList<>();
            for (String tag : request.getTags()) {
                Tag t = new Tag();
                t.setTag(tag);
                tagList.add(t);
            }
            PostFile postFile = postService.setPostFile(post, request.getTitle(), request.getPhotoUrl());
            post.setTags(tagList);
            post.setPostFiles(List.of(postFile));
            post.setTime(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));
            post.setPerson(personService.getPersonById(1));
            postLike.setPost(post);
            person.setPostList(List.of(post));
            person.setPostLikeList(List.of(postLike));
            postLike.setPerson(person);
            postService.savePost(post);
            PostAddResponse response = new PostAddResponse();
            response.setStatus(true);
            return ResponseEntity.ok(response);
        }catch (UserNotFoundException e){
            return ResponseEntity.badRequest().body(new PostAddResponse());//TODO: исправить
        }
    }


    @Override
    @GetMapping("/api/v1/post")
    public ResponseEntity<PostResponse> getPostsAll(@RequestParam(name = "text", defaultValue = "string") String text,
                                                    @RequestParam(required = false, name = "date_from") Long dateFrom,
                                                    @RequestParam(required = false, name = "date_to") Long dateTo,
                                                    @RequestParam(required = false, name = "author") User user,
                                                    @RequestParam(required = false, name = "tags") List<Tag> tags,
                                                    @RequestParam(name = "pageable") Pageable pageable) {
        PostResponse response = new PostResponse();
        response.setTimestamp(dateFrom);
        response.setTotal(1);
        response.setData(postService.getAllPosts());
        response.setSize(pageable.getSize());
        response.setPage(pageable.getPage());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/api/v1/post/wall")
    public ResponseEntity<PostResponse> getPostWall() {
        PostResponse response = new PostResponse();
        response.setTimestamp(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));
        response.setTotal(1);
        response.setData(postService.getAllPosts());
        response.setSize(20);
        response.setPage(1);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/api/v1/post/{id}")
    public ResponseEntity<PostResponse> deletePostById(@PathVariable String id) {

        PostResponse response = new PostResponse();
        response.setSize(20);
        response.setTotal(1);
        response.setPage(1);
        response.setTimestamp(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));
        response.setData(postService.getAllPosts());
        postService.deletePost(postService.getPostById(Long.parseLong(id)));
        return ResponseEntity.ok(response);
    }


    @GetMapping("/api/v1/post/{id}")
    public ResponseEntity<PostResponse> getPostById(@PathVariable String id) {
        PostResponse response = new PostResponse();
        response.setSize(20);
        response.setTotal(1);
        response.setPage(1);
        response.setTimestamp(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));
        response.setData(List.of(postService.getPostById(Long.parseLong(id))));
        return ResponseEntity.ok(response);
    }

    @PostMapping("/api/v1/post/{id}/report")
    public ResponseEntity<PostComplaintResponse> addNewComplaint(@PathVariable String id) {
        Post post = postService.getPostById(Long.parseLong(id));
        PostComplaintResponse response = new PostComplaintResponse();
        response.setErrorDescription("error");
        response.setTimestamp(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));
        ComplaintResponse complaintResponse = new ComplaintResponse();
        complaintResponse.setMessage("message");
        response.setData(List.of(complaintResponse));
        return ResponseEntity.ok(response);
    }

    @PutMapping("/api/v1/post/{id}")
    public ResponseEntity<PostResponse> putPostById(@RequestBody PostAddRequest request, @PathVariable String id) {
        PostResponse response = new PostResponse();
        response.setTimestamp(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));
        response.setSize(20);
        response.setTotal(1);
        response.setPage(1);
        response.setData(postService.getAllPosts());
        Post post = postService.getPostById(Long.parseLong(id));
        PostFile postFile = new PostFile();
        postFile.setName(request.getTitle());
        postFile.setPost(post);
        postFile.setPath(request.getPhotoUrl());
        post.setTitle(request.getTitle());
        post.setPostText(request.getPostText());
        post.setPostFiles(List.of(postFile));
        return ResponseEntity.ok(response);
    }

    @PutMapping("/api/v1/post/{id}/recover")
    public ResponseEntity<PostResponse> recoveryPostById(@PathVariable String id) {
        PostResponse response = new PostResponse();
        response.setPage(1);
        response.setTotal(1);
        response.setTimestamp(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));
        response.setSize(20);
        response.setData(postService.getAllPosts());
        Post post = postService.getPostById(Long.parseLong(id));
        postService.savePost(post);
        return ResponseEntity.ok(response);
    }
}