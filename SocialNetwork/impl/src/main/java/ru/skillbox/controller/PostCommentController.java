package ru.skillbox.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.skillbox.request.CommentAddRequest;
import ru.skillbox.service.PostCommentService;

@RestController
@RequestMapping("/api/v1/post/{id}/comment")
public class PostCommentController {

    private final PostCommentService postCommentService;

    @Autowired
    public PostCommentController(PostCommentService postCommentService) {
        this.postCommentService = postCommentService;
    }

    @PostMapping
    public void addCommentByIdPost(
            @PathVariable String id, @RequestBody CommentAddRequest request) {
        postCommentService.addComment(id, request);
    }

    @DeleteMapping("/{commentId}")
    public void deleteCommentByIdPost(@PathVariable String id, @PathVariable String commentId) {
        postCommentService.deleteComment(id, commentId);
    }

    @PutMapping("/{commentId}")
    public void putCommentByIdPost(@RequestBody CommentAddRequest request,
                                   @PathVariable String id, @PathVariable String commentId) {
        postCommentService.updateComment(request, id, commentId);
    }
}
