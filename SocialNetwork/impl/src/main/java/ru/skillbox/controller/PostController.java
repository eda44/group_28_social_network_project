package ru.skillbox.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skillbox.dto.Pageable;
import ru.skillbox.dto.PostSearchDto;
import ru.skillbox.exception.UserNotFoundException;
import ru.skillbox.model.Post;
import ru.skillbox.request.PostAddRequest;
import ru.skillbox.response.post.PagePostDto;
import ru.skillbox.response.post.PostResponse;
import ru.skillbox.response.post.WallResponse;
import ru.skillbox.service.PersonService;
import ru.skillbox.service.PostService;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

@RestController
public class PostController {

    private final PostService postService;
    private final PersonService personService;


    private static final Logger logger = LogManager.getLogger(PostController.class);

    @Autowired
    public PostController(PostService postService, PersonService personService) {
        this.postService = postService;
        this.personService = personService;
    }

    @PostMapping("/api/v1/post")
    public ResponseEntity<Object> addNewPost(
            @RequestBody PostAddRequest request) {
        postService.setPost(request);
        logger.info("saving post");
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/api/v1/post/{id}")
    public ResponseEntity<Object> deletePostById(@PathVariable String id) {
        postService.deletePost(postService.getPostById(Long.parseLong(id)));
        logger.info("deleting post");
        return ResponseEntity.ok("ok");
    }

    @GetMapping("/api/v1/post/{id}")
    public ResponseEntity<PostResponse> getPostById(@PathVariable String id) {
        Post post = postService.getPostById(Long.parseLong(id));
        logger.info("getting post by id");
        PostResponse response = postService.setPostResponse(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/api/v1/post/{id}")
    public ResponseEntity<Object> putPostById(@RequestBody PostAddRequest request, @PathVariable String id) throws UserNotFoundException {
        Post post = postService.getPostById(Long.parseLong(id));
        post.setTitle(request.getTitle());
        post.setPostText(request.getPostText());
        post.setPerson(personService.getPersonById(request.getAuthorId()));
        post.setType(request.getType());
        post.setIsBlocked(request.getIsBlocked());
        post.setTags(postService.convertStringToTag(request.getTags()));
        post.setTime(request.getTime());
        post.setPostFiles(List.of(postService.setPostFile(post, request.getTitle(), request.getImagePath())));
        postService.savePost(post);
        logger.info("updating post");
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/api/v1/post")
    public ResponseEntity<PagePostDto> getPostsAll(PostSearchDto searchDto,
                                                   @RequestParam Pageable pageable) {
        PagePostDto response = new PagePostDto();
        response.setTotalPages(pageable.getPage());
        response.setSize(pageable.getSize());
        response.setNumber(searchDto.getAccountIds().size());
        PostResponse postResponse = new PostResponse();
        postResponse.setTags(searchDto.getTags());
        postResponse.setPostText(searchDto.getPostText());
        postResponse.setTitle(searchDto.getTitle());
        postResponse.setTimeChanged(searchDto.getDateTo());
        postResponse.setTime(searchDto.getDateFrom());
        postResponse.setIsDelete(searchDto.getIsDelete());
        response.setContent(List.of(postResponse));

        return ResponseEntity.ok(response);
    }

    @GetMapping("/api/v1/post/wall")
    public ResponseEntity<WallResponse> getPostWall() {
        WallResponse response = new WallResponse();

        response.setPage(1);
        response.setTotal(1);
        response.setSize(20);
        response.setTimestamp(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));
        return ResponseEntity.ok(response);
    }

    //    @PostMapping("/api/v1/post/{id}/report")
//    public ResponseEntity<PostComplaintResponse> addNewComplaint(@PathVariable String id) {
//        Post post = postService.getPostById(Long.parseLong(id));
//        PostComplaintResponse response = new PostComplaintResponse();
//        response.setErrorDescription("error");
//        response.setTimestamp(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));
//        ComplaintResponse complaintResponse = new ComplaintResponse();
//        complaintResponse.setMessage("message");
//        response.setData(List.of(complaintResponse));
//        return ResponseEntity.ok(response);
//    }
//
//
//    @PutMapping("/api/v1/post/{id}/recover")
//    public ResponseEntity<PostResponse> recoveryPostById(@PathVariable String id) {
//        PostResponse response = new PostResponse();
//        Post post = postService.getPostById(Long.parseLong(id));
//        postService.savePost(post);
//        return ResponseEntity.ok(response);
//    }
//
}