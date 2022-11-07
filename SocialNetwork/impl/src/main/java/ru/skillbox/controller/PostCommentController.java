package ru.skillbox.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skillbox.model.Post;
import ru.skillbox.model.PostComment;
import ru.skillbox.model.PostCommentInterface;
import ru.skillbox.request.CommentAddRequest;
import ru.skillbox.response.CommentComplaintResponse;
import ru.skillbox.response.ComplaintResponse;
import ru.skillbox.response.post.PostCommentResponse;
import ru.skillbox.service.PersonService;
import ru.skillbox.service.PostCommentService;
import ru.skillbox.service.PostService;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

@RestController
public class PostCommentController implements PostCommentInterface {

    private final PostCommentService postCommentService;
    private final PostService postService;
    private final PersonService personService;

    @Autowired
    public PostCommentController(PostCommentService postCommentService, PostService postService, PersonService personService) {
        this.postCommentService = postCommentService;
        this.postService = postService;
        this.personService = personService;
    }

    @PostMapping("/api/v1/post/{id}/comments")
    public ResponseEntity<PostCommentResponse> addCommentByIdPost(
            @PathVariable String id, @RequestBody CommentAddRequest request) {

        PostCommentResponse response = new PostCommentResponse();
        response.setTimestamp(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));
        response.setOffset(20);
        response.setTotal(1);
        response.setPerPage(2);
        Post post = postService.getPostById(Long.parseLong(id));
        PostComment postComment = new PostComment();
        postComment.setCommentText(request.getCommentText());
        postComment.setPerson(personService.getPersonById(request.getParentId()));
        postComment.setPost(post);
        postComment.setTime(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));
        postComment.setIsBlocked(false);
        postCommentService.savePostComment(postComment);
        post.setPostCommentList(List.of(postComment));
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/api/v1/post/{id}/comments/{comment_id}")
    public ResponseEntity<PostCommentResponse> deleteCommentByIdPost(@PathVariable String id,
                                                                     @PathVariable String comment_id) {
        PostCommentResponse response = new PostCommentResponse();
        response.setTimestamp(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));
        response.setOffset(20);
        response.setTotal(1);
        response.setPerPage(2);
        Post post = postService.getPostById(Long.parseLong(id));
        PostComment postComment = postCommentService.getPostCommentById(Long.parseLong(comment_id));
        if (post.getPostCommentList().contains(postComment)) {
            postCommentService.deletePostComment(postComment);
        }
        response.setData(List.of(postComment));
        return ResponseEntity.ok(response);
    }

    @PostMapping("/api/v1/post/{id}/comments/{comment_id}/report")
    public ResponseEntity<CommentComplaintResponse> addCommentComplaint(
            @PathVariable String id, @PathVariable String comment_id) {
        CommentComplaintResponse response = new CommentComplaintResponse();
        response.setErrorDescription("error");
        response.setTimestamp(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));
        ComplaintResponse complaintResponse = new ComplaintResponse();
        complaintResponse.setMessage("message");
        response.setData(List.of(complaintResponse));
        Post post = postService.getPostById(Long.parseLong(id));
        PostComment postComment = postCommentService.getPostCommentById(Long.parseLong(comment_id));
        // как привязать жалобу
        return ResponseEntity.ok(response);
    }

    @Override
    @GetMapping("/api/v1/post/{id}/comments")
    public ResponseEntity<PostCommentResponse> getCommentByIdPost(Integer id, Integer offset, Integer perPage) {

        PostCommentResponse response = new PostCommentResponse();
        response.setTimestamp(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));
        response.setOffset(offset);
        response.setTotal(1);
        response.setPerPage(perPage);
        Post post = postService.getPostById(id);
        response.setData(post.getPostCommentList());
        return ResponseEntity.ok(response);
    }

    @PutMapping("/api/v1/post/{id}/comments/{comment_id}")
    public ResponseEntity<PostCommentResponse> putCommentByIdPost(@RequestBody CommentAddRequest request,
                                                                  @PathVariable String id, @PathVariable String comment_id) {
        PostCommentResponse response = new PostCommentResponse();
        response.setTimestamp(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));
        response.setOffset(20);
        response.setTotal(1);
        response.setPerPage(2);
        Post post = postService.getPostById(Long.parseLong(id));
        PostComment postComment = postCommentService.getPostCommentById(Long.parseLong(comment_id));
        if (post.getPostCommentList().contains(postComment)) {
            postComment.setCommentText(request.getCommentText());
            postComment.setTime(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));
            postComment.setIsBlocked(false);
            postComment.setPerson(personService.getPersonById(request.getParentId()));
            postComment.setPost(post);
        }
        response.setData(List.of(postComment));
        return ResponseEntity.ok(response);
    }

    @PutMapping("/api/v1/post/{id}/comments/{comment_id}/recover")
    public ResponseEntity<PostCommentResponse> recoveryCommentByIdPost(@PathVariable String id,
                                                                       @PathVariable String comment_id) {
        PostCommentResponse response = new PostCommentResponse();
        response.setTimestamp(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));
        response.setOffset(20);
        response.setTotal(1);
        response.setPerPage(2);
        Post post = postService.getPostById(Long.parseLong(id));
        PostComment postComment = postCommentService.getPostCommentById(Long.parseLong(comment_id));
        if (post.getPostCommentList().contains(postComment)) {
            postCommentService.savePostComment(postComment);
        }
        response.setData(List.of());
        return ResponseEntity.ok(response);
    }
}
