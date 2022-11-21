package ru.skillbox.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.skillbox.config.CloudinaryConfig;
import ru.skillbox.dto.PostSearchDto;
import ru.skillbox.dto.enums.Type;
import ru.skillbox.exception.UserNotFoundException;
import ru.skillbox.model.Post;
import ru.skillbox.model.PostFile;
import ru.skillbox.model.Tag;
import ru.skillbox.repository.PostRepository;
import ru.skillbox.request.PostAddRequest;
import ru.skillbox.response.post.PagePostDto;
import ru.skillbox.response.post.PostResponse;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final PostFileService postFileService;
    private final PersonService personService;
    private final CloudinaryConfig cloudinaryConfig;

    @Autowired
    public PostService(PostRepository postRepository, PostFileService postFileService,
                       PersonService personService, CloudinaryConfig cloudinaryConfig) {
        this.postRepository = postRepository;
        this.postFileService = postFileService;
        this.personService = personService;
        this.cloudinaryConfig = cloudinaryConfig;
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post getPostById(long id) {
        return postRepository.findById(id).get();
    }

    public void savePost(Post post) {
        postRepository.save(post);
    }

    public List<Tag> convertStringToTag(List<String> tagsFromRequest) {
        List<Tag> tagList = new ArrayList<>();
        for (String tagString : tagsFromRequest) {
            Tag tag = new Tag();
            tag.setTag(tagString);
            tagList.add(tag);
        }
        return tagList;
    }

    public List<String> convertTagToString(List<Tag> tagList) {
        List<String> tags = new ArrayList<>();
        for (Tag tag : tagList) {
            tags.add(tag.getTag());
        }
        return tags;
    }

    public void deletePost(Post post) {
        postRepository.delete(post);
    }

    public void setPost(PostAddRequest request) {
        Post post = new Post();

        post.setTitle(request.getTitle());
        post.setPostText(request.getPostText());
        post.setTags(convertStringToTag(request.getTags()));
        post.setIsBlocked(request.getIsBlocked());
        if (request.getPublishDate() != null) {
            post.setType(Type.QUEUED);
        } else {
            post.setType(Type.POSTED);
        }
        switch (post.getType()) {
            case POSTED:
                post.setTime(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));
                break;
            case QUEUED:
                post.setTime(request.getPublishDate());
        }
        savePost(post);
    }

    public void uploadImage(MultipartFile multipartFile) {
        postFileService.savePostFile(cloudinaryConfig.uploadImage(multipartFile));
    }

    public PostResponse setPostResponse(String id) {
        Post post = getPostById(Long.parseLong(id));
        PostResponse response = new PostResponse();
        response.setPostText(post.getPostText());
        response.setId(post.getId());
        response.setTime(post.getTime());
        response.setTitle(post.getTitle());
        response.setIsBlocked(post.getIsBlocked());
        response.setAuthorId(post.getPerson().getId());
        response.setType(post.getType());
        response.setCommentsCount(post.getPostCommentList().size());
        response.setTags(convertTagToString(post.getTags()));
        return response;
    }

    public ResponseEntity<PagePostDto> getPostsAll(@RequestParam PostSearchDto searchDto,
                                                   @RequestParam(name = "page", defaultValue = "0")  int page,
                                                   @RequestParam(name = "size", defaultValue = "1") int size,
                                                   @RequestParam(name = "sort", defaultValue = "time") String[] sort,
                                                   @RequestParam(name = "offset", defaultValue = "0") int offset,
                                                   @RequestParam(name = "limit", defaultValue = "20") int limit) {
        PagePostDto response = new PagePostDto();
        Pageable pageable = PageRequest.of(page,size, Sort.by(sort).descending());


        return ResponseEntity.ok(response);
    }

    public PostFile setPostFile(Post post, String name, String path) {
        PostFile postFile = new PostFile();
        postFile.setPost(post);
        postFile.setName(name);
        postFile.setPath(path);
        return postFile;
    }

    public void updatePost(PostAddRequest request, String id) {
        Post post = getPostById(Long.parseLong(id));
        post.setTitle(request.getTitle());
        post.setPostText(request.getPostText());
        try {
            post.setPerson(personService.getPersonById(request.getAuthorId()));
        } catch (UserNotFoundException e) {
            throw new RuntimeException(e);
        }
        post.setType(request.getType());
        post.setIsBlocked(request.getIsBlocked());
        post.setTags(convertStringToTag(request.getTags()));
        post.setTime(request.getTime());
        post.setPostFiles(List.of(setPostFile(post, request.getTitle(), request.getImagePath())));
        savePost(post);
    }
}
