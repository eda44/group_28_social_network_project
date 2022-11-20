package ru.skillbox.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skillbox.dto.enums.LikeType;
import ru.skillbox.model.Post;
import ru.skillbox.model.PostComment;
import ru.skillbox.model.PostLike;
import ru.skillbox.request.PostLikeRequest;
import ru.skillbox.response.LikeResponse;
import ru.skillbox.response.post.PostLikeResponse;
import ru.skillbox.service.CommentLikeService;
import ru.skillbox.service.PostCommentService;
import ru.skillbox.service.PostLikeService;
import ru.skillbox.service.PostService;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

@RestController
public class PostLikeController {

    private final PostLikeService postLikeService;
    private final PostService postService;
    private final PostCommentService postCommentService;
    private final CommentLikeService commentLikeService;
    private static Logger logger = LogManager.getLogger(PostLikeController.class);


    @Autowired
    public PostLikeController(PostLikeService postLikeService, PostService postService, PostCommentService postCommentService, CommentLikeService commentLikeService) {
        this.postLikeService = postLikeService;
        this.postService = postService;
        this.postCommentService = postCommentService;
        this.commentLikeService = commentLikeService;
    }

    @PostMapping("/api/v1/post/{id}/like")
    public ResponseEntity<Object> createPostLike(@PathVariable String id) {
        Post post = postService.getPostById(Long.parseLong(id));
        PostLike postLike = new PostLike();
        postLike.setTime(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));
        postLike.setPost(post);
        post.setPostLikes(List.of(postLike));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/api/v1/post/{id}/comment/{commentId}/like")
    public ResponseEntity<Object> likeToComment(@PathVariable String id,
                                                @PathVariable String commentId) {
        Post post = postService.getPostById(Long.parseLong(id));
        postCommentService.getPostCommentById(Long.parseLong(commentId));
        PostLike postLike = new PostLike();
        postLike.setPost(post);
        postLikeService.saveLike(postLike);
        logger.info("adding like to comment");
        postLike.setTime(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));
        return ResponseEntity.ok("ok");
    }

    @DeleteMapping("/api/v1/post/{id}/like")
    public ResponseEntity<Object> deleteLike(@PathVariable String id) {
        Post post = postService.getPostById(Long.parseLong(id));
        List<PostLike> postLikes = post.getPostLikes();
        if (!postLikes.isEmpty() ||
                postLikeService.isLiked(Long.valueOf(id), LikeType.POST)) {
            postLikeService.deleteLike(postLikes.get(0));
            logger.info("deleting post like");
            postLikes.remove(0);
        }
        return ResponseEntity.ok("ok");
    }

    @DeleteMapping("/api/v1/post/{id}/comment/{commentId}/like")
    public ResponseEntity<Object> deleteLikeToComment(@PathVariable String id,
                                                      @PathVariable String commentId) {
        Post post = postService.getPostById(Long.parseLong(id));
        PostComment comment = postCommentService.getPostCommentById(Long.parseLong(commentId));
        if (post.getPostCommentList().contains(comment) ||
                postLikeService.isLiked(Long.valueOf(commentId), LikeType.COMMENT)) {
            commentLikeService.delete(comment.getCommentLikes().get(0));
            logger.info("deleting comment like");
        }
        return ResponseEntity.ok("ok");
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
