package ru.skillbox.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.skillbox.service.LikeService;

@RestController
@RequestMapping("/api/v1/post/{id}")
public class PostLikeController {

    private final LikeService likeService;

    @Autowired
    public PostLikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @PostMapping("/like")
    public void createPostLike(@PathVariable String id) {
        likeService.addPostLike(id);
    }

    @PostMapping("/comment/{commentId}/like")
    public void likeToComment(@PathVariable String id,
                              @PathVariable String commentId) {
        likeService.addCommentLike(id, commentId);
    }

    @DeleteMapping("/like")
    public void deleteLike(@PathVariable String id) {
        likeService.deletePostLike(id);
    }

    @DeleteMapping("/comment/{commentId}/like")
    public void deleteLikeToComment(@PathVariable String id, @PathVariable String commentId) {
        likeService.deleteCommentLike(id, commentId);
    }
}
