package ru.skillbox.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skillbox.dto.enums.LikeType;
import ru.skillbox.model.*;
import ru.skillbox.request.PostLikeRequest;
import ru.skillbox.response.LikeResponse;
import ru.skillbox.response.post.PostLikeResponse;
import ru.skillbox.service.*;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

@RestController
@RequestMapping("/api/v1/post/{id}")
public class PostLikeController {

    private final PostLikeService postLikeService;

    private final PersonService personService;
    private final PostService postService;
    private final PostCommentService postCommentService;
    private final CommentLikeService commentLikeService;
    private static Logger logger = LogManager.getLogger(PostLikeController.class);


    @Autowired
    public PostLikeController(PostLikeService postLikeService, PostService postService,
                              PostCommentService postCommentService, CommentLikeService commentLikeService,
                              PersonService personService) {
        this.postLikeService = postLikeService;
        this.postService = postService;
        this.postCommentService = postCommentService;
        this.commentLikeService = commentLikeService;
        this.personService = personService;
    }

    @PostMapping("/like")
    public ResponseEntity<Object> createPostLike(@PathVariable String id) {
        Post post = postService.getPostById(Long.parseLong(id));
        PostLike postLike = new PostLike();
        postLike.setTime(LocalDateTime.now()
                .toEpochSecond(ZoneOffset.systemDefault().getRules().getOffset(LocalDateTime.now())));
        postLike.setPost(post);
        postLike.setPerson(personService.getCurrentPerson());
        postLikeService.saveLike(postLike);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/comment/{commentId}/like")
    public ResponseEntity<Object> likeToComment(@PathVariable String id,
                                                @PathVariable String commentId) {
        PostComment postComment = postCommentService.getPostCommentById(Long.parseLong(commentId));
        CommentLike commentLike = new CommentLike();
        commentLike.setTime(LocalDateTime.now()
                .toEpochSecond(ZoneOffset.systemDefault().getRules().getOffset(LocalDateTime.now())));
        commentLike.setComment(postComment);
        commentLike.setPerson(personService.getCurrentPerson());
        postLikeService.saveCommentLike(commentLike);
        logger.info("adding like to comment");
        return ResponseEntity.ok("ok");
    }

    @DeleteMapping("/like")
    public ResponseEntity<Object> deleteLike(@PathVariable String id) {
        Post post = postService.getPostById(Long.parseLong(id));
        Person person = personService.getCurrentPerson();
        PostLike postLike = postLikeService.getLikeByPostIdAndPersonId(post.getId(), person.getId());
        if (post.getPostLikes().contains(postLike)) {

            logger.info("deleting postLike");
            post.getPostLikes().remove(postLike);
            postService.savePost(post);
           if(person.getPostLikeList().contains(postLike)){
               person.getPostLikeList().remove(postLike);
            }
            postLikeService.deleteLike(postLike);
        }
        return ResponseEntity.ok("ok");
    }

    @DeleteMapping("/comment/{commentId}/like")
    public ResponseEntity<Object> deleteLikeToComment(@PathVariable String id,
                                                      @PathVariable String commentId) {
        PostComment comment = postCommentService.getPostCommentById(Long.parseLong(commentId));
        Person person = personService.getCurrentPerson();
        CommentLike commentLike = postLikeService.getLikeByCommentIdAndPersonId(comment.getId(), person.getId());
        if (comment.getCommentLikes().contains(commentLike)) {

            logger.info("deleting commentLike");
            comment.getCommentLikes().remove(commentLike);
            postCommentService.savePostComment(comment);
            postLikeService.deleteCommentLike(commentLike);
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
