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
        likeService.setPostLike(id);
    }

    @PostMapping("/comment/{commentId}/like")
    public void likeToComment(@PathVariable String id,
                              @PathVariable String commentId) {
        likeService.setCommentLike(id, commentId);
    }

    @DeleteMapping("/like")
    public void deleteLike(@PathVariable String id) {
        likeService.deletePostLike(id);
    }

    @DeleteMapping("/comment/{commentId}/like")
    public void deleteLikeToComment(@PathVariable String id,
                                    @PathVariable String commentId) {
        likeService.deleteCommentLike(id, commentId);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

//    @GetMapping("/api/v1/liked")
//    public ResponseEntity<PostLikeResponse> getLiked(Long id, LikeType type) {
//        PostLikeResponse response = new PostLikeResponse();
//        LikeResponse likeResponse = new LikeResponse();
//        likeResponse.setLikes(2);
//        likeResponse.setLiked(postLikeService.isLiked(id, type));
////        likeResponse.setUsers(postLikeService.getUsers(new Post()));
//        response.setError("ok");
//        response.setTimestamp(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));
//        response.setErrorDescription("description");
//        response.setData(List.of(likeResponse));
//        return ResponseEntity.ok(response);
//    }
//
//    @GetMapping("/api/v1/likes")
//    public ResponseEntity<PostLikeResponse> getLikes(Long id, LikeType type) {
//        PostLikeResponse response = new PostLikeResponse();
//        LikeResponse likeResponse = new LikeResponse();
//        likeResponse.setLiked(true);
//        likeResponse.setLikes(2);
//        response.setError("ok");
//        response.setTimestamp(1644234125L);
//        response.setErrorDescription("description");
//        response.setData(List.of(likeResponse));
//        if (type == LikeType.POST) {
//            postService.getPostById(id).getPostLikes().forEach(postLike -> postLikeService.getAllLikes());
//        }
//        if (type == LikeType.COMMENT) {
//            postCommentService.getPostCommentById(id);
//        }
//
//        return ResponseEntity.ok(response);
//    }
//
//    @PutMapping("/api/v1/likes")
//    public ResponseEntity<PostLikeResponse> putLike(@RequestBody PostLikeRequest request) {
//        request.setAuthorId(1);
//        request.setType(LikeType.POST);
//        request.setItemId(1);
//        request.setPostId(1);
//        PostLikeResponse response = new PostLikeResponse();
//        LikeResponse likeResponse = new LikeResponse();
//        Integer[] users = {1, 2};
//        likeResponse.setLiked(true);
////        likeResponse.setUsers(users);
//        likeResponse.setLikes(2);
//        LikeResponse[] data = {likeResponse};
////        response.setData(data);
//        response.setError("ok");
//        response.setTimestamp(1644234125L);
//        response.setErrorDescription("description");
//        PostLike postLike = new PostLike();
//        postLike.setTime(1644234125L);
////        like.setUser(userService.getUserById(request.getAuthorId()));
//        postLike.setPost(postService.getPostById(request.getPostId()));
//        postLikeService.saveLike(postLike);
//        return ResponseEntity.ok(response);
//    }
}
