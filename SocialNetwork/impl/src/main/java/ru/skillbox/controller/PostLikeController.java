package ru.skillbox.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skillbox.dto.enums.LikeType;
import ru.skillbox.model.Post;
import ru.skillbox.model.PostLike;
import ru.skillbox.request.PostLikeRequest;
import ru.skillbox.response.LikeResponse;
import ru.skillbox.response.post.PostLikeResponse;
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

    private static Logger logger = LogManager.getLogger(PostLikeController.class);


    @Autowired
    public PostLikeController(PostLikeService postLikeService, PostService postService, PostCommentService postCommentService) {
        this.postLikeService = postLikeService;
        this.postService = postService;
        this.postCommentService = postCommentService;
    }

    @PostMapping("/api/v1/post/{id}/like")
    public ResponseEntity<Object> createPostLike(@PathVariable String id) {
        Post post = postService.getPostById(Long.parseLong(id));
        PostLike postLike = new PostLike();

//        post.setPostLikes();
        return ResponseEntity.ok(HttpStatus.OK);
    }


    @GetMapping("/api/v1/liked")
    public ResponseEntity<PostLikeResponse> getLiked(Long id, LikeType type) {
        PostLikeResponse response = new PostLikeResponse();
        LikeResponse likeResponse = new LikeResponse();
        likeResponse.setLikes(2);
        likeResponse.setLiked(postLikeService.isLiked(id, type));
//        likeResponse.setUsers(postLikeService.getUsers(new Post()));
        response.setError("ok");
        response.setTimestamp(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));
        response.setErrorDescription("description");
        response.setData(List.of(likeResponse));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/api/v1/likes")
    public ResponseEntity<PostLikeResponse> getLikes(Long id, LikeType type) {
        PostLikeResponse response = new PostLikeResponse();
        LikeResponse likeResponse = new LikeResponse();
        likeResponse.setLiked(true);
        likeResponse.setLikes(2);
        response.setError("ok");
        response.setTimestamp(1644234125L);
        response.setErrorDescription("description");
        response.setData(List.of(likeResponse));
        if (type == LikeType.POST) {
            postService.getPostById(id).getPostLikes().forEach(postLike -> postLikeService.getAllLikes());
        }
        if (type == LikeType.COMMENT) {
            postCommentService.getPostCommentById(id);
        }

        return ResponseEntity.ok(response);
    }

    @PutMapping("/api/v1/likes")
    public ResponseEntity<PostLikeResponse> putLike(@RequestBody PostLikeRequest request) {
        request.setAuthorId(1);
        request.setType(LikeType.POST);
        request.setItemId(1);
        request.setPostId(1);
        PostLikeResponse response = new PostLikeResponse();
        LikeResponse likeResponse = new LikeResponse();
        Integer[] users = {1, 2};
        likeResponse.setLiked(true);
//        likeResponse.setUsers(users);
        likeResponse.setLikes(2);
        LikeResponse[] data = {likeResponse};
//        response.setData(data);
        response.setError("ok");
        response.setTimestamp(1644234125L);
        response.setErrorDescription("description");
        PostLike postLike = new PostLike();
        postLike.setTime(1644234125L);
//        like.setUser(userService.getUserById(request.getAuthorId()));
        postLike.setPost(postService.getPostById(request.getPostId()));
        postLikeService.saveLike(postLike);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/api/v1/likes")
    public ResponseEntity<PostLikeResponse> deleteLike() {
        PostLikeResponse response = new PostLikeResponse();
        LikeResponse likeResponse = new LikeResponse();
        likeResponse.setLiked(true);
        likeResponse.setLikes(1);
//        likeResponse.setUsers(List.of(1));
        response.setError("ok");
        response.setTimestamp(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));
        response.setErrorDescription("description");
        response.setData(List.of(likeResponse));
//        postLikeService.deleteLike();
        return ResponseEntity.ok(response);
    }
}
