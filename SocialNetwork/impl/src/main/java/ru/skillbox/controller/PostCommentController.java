package ru.skillbox.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skillbox.model.api.request.CommentAddRequest;
import ru.skillbox.model.api.request.PostAddRequest;
import ru.skillbox.model.api.request.PostCommentInterface;

@RestController
public class PostCommentController implements PostCommentInterface {

    @PostMapping("/api/v1/post/{id}/comments")
    public ResponseEntity<String> addCommentByIdPost(@PathVariable Integer id, @RequestBody CommentAddRequest request) {
        return ResponseEntity.ok("{\n" +
                "  \"total\" : 0,\n" +
                "  \"perPage\" : 1,\n" +
                "  \"offset\" : 6,\n" +
                "  \"data\" : [ {\n" +
                "    \"comment_text\" : \"comment_text\",\n" +
                "    \"is_blocked\" : true,\n" +
                "    \"post_id\" : 4,\n" +
                "    \"my_like\" : true,\n" +
                "    \"parent_id\" : 2,\n" +
                "    \"id\" : 9,\n" +
                "    \"time\" : 3,\n" +
                "    \"sub_comments\" : [ null, null ],\n" +
                "    \"likes\" : 7\n" +
                "  }, {\n" +
                "    \"comment_text\" : \"comment_text\",\n" +
                "    \"is_blocked\" : true,\n" +
                "    \"post_id\" : 4,\n" +
                "    \"my_like\" : true,\n" +
                "    \"parent_id\" : 2,\n" +
                "    \"id\" : 9,\n" +
                "    \"time\" : 3,\n" +
                "    \"sub_comments\" : [ null, null ],\n" +
                "    \"likes\" : 7\n" +
                "  } ],\n" +
                "  \"timestamp\" : 1644234125000\n" +
                "}");
    }

    @DeleteMapping("/api/v1/post/{id}/comments/{comment_id}")
    public ResponseEntity<String> deleteCommentByIdPost(@PathVariable Integer id, @PathVariable Integer comment_id) {
        return ResponseEntity.ok("{\n" +
                "  \"total\" : 0,\n" +
                "  \"perPage\" : 1,\n" +
                "  \"offset\" : 6,\n" +
                "  \"data\" : [ {\n" +
                "    \"comment_text\" : \"comment_text\",\n" +
                "    \"is_blocked\" : true,\n" +
                "    \"post_id\" : 4,\n" +
                "    \"my_like\" : true,\n" +
                "    \"parent_id\" : 2,\n" +
                "    \"id\" : 9,\n" +
                "    \"time\" : 3,\n" +
                "    \"sub_comments\" : [ null, null ],\n" +
                "    \"likes\" : 7\n" +
                "  }, {\n" +
                "    \"comment_text\" : \"comment_text\",\n" +
                "    \"is_blocked\" : true,\n" +
                "    \"post_id\" : 4,\n" +
                "    \"my_like\" : true,\n" +
                "    \"parent_id\" : 2,\n" +
                "    \"id\" : 9,\n" +
                "    \"time\" : 3,\n" +
                "    \"sub_comments\" : [ null, null ],\n" +
                "    \"likes\" : 7\n" +
                "  } ],\n" +
                "  \"timestamp\" : 1644234125000\n" +
                "}");
    }

    @PostMapping("/api/v1/post/{id}/comments/{comment_id}/report")
    public ResponseEntity<String> addCommentComplaint(@PathVariable Integer id, @PathVariable Integer comment_id) {
        return ResponseEntity.ok("{\n" +
                "  \"data\" : [ {\n" +
                "    \"message\" : \"message\"\n" +
                "  }, {\n" +
                "    \"message\" : \"message\"\n" +
                "  } ],\n" +
                "  \"error_description\" : \"error_description\",\n" +
                "  \"timestamp\" : 1644234125000\n" +
                "}");
    }

    @Override
    @GetMapping("/api/v1/post/{id}/comments")
    public ResponseEntity<String> getCommentByIdPost(Integer id, Integer offset, Integer perPage) {
        return ResponseEntity.ok("{\n" +
                "  \"total\" : 0,\n" +
                "  \"perPage\" : 1,\n" +
                "  \"offset\" : 6,\n" +
                "  \"data\" : [ {\n" +
                "    \"comment_text\" : \"comment_text\",\n" +
                "    \"is_blocked\" : true,\n" +
                "    \"post_id\" : 4,\n" +
                "    \"my_like\" : true,\n" +
                "    \"parent_id\" : 2,\n" +
                "    \"id\" : 9,\n" +
                "    \"time\" : 3,\n" +
                "    \"sub_comments\" : [ null, null ],\n" +
                "    \"likes\" : 7\n" +
                "  }, {\n" +
                "    \"comment_text\" : \"comment_text\",\n" +
                "    \"is_blocked\" : true,\n" +
                "    \"post_id\" : 4,\n" +
                "    \"my_like\" : true,\n" +
                "    \"parent_id\" : 2,\n" +
                "    \"id\" : 9,\n" +
                "    \"time\" : 3,\n" +
                "    \"sub_comments\" : [ null, null ],\n" +
                "    \"likes\" : 7\n" +
                "  } ],\n" +
                "  \"timestamp\" : 1644234125000\n" +
                "}");
    }

    @PutMapping("/api/v1/post/{id}/comments/{comment_id}")
    public ResponseEntity<String> putCommentByIdPost(@RequestBody CommentAddRequest request,
                                                     @PathVariable Integer id, @PathVariable Integer comment_id) {
        return ResponseEntity.ok("{\n" +
                "  \"total\" : 0,\n" +
                "  \"perPage\" : 1,\n" +
                "  \"offset\" : 6,\n" +
                "  \"data\" : [ {\n" +
                "    \"comment_text\" : \"comment_text\",\n" +
                "    \"is_blocked\" : true,\n" +
                "    \"post_id\" : 4,\n" +
                "    \"my_like\" : true,\n" +
                "    \"parent_id\" : 2,\n" +
                "    \"id\" : 9,\n" +
                "    \"time\" : 3,\n" +
                "    \"sub_comments\" : [ null, null ],\n" +
                "    \"likes\" : 7\n" +
                "  }, {\n" +
                "    \"comment_text\" : \"comment_text\",\n" +
                "    \"is_blocked\" : true,\n" +
                "    \"post_id\" : 4,\n" +
                "    \"my_like\" : true,\n" +
                "    \"parent_id\" : 2,\n" +
                "    \"id\" : 9,\n" +
                "    \"time\" : 3,\n" +
                "    \"sub_comments\" : [ null, null ],\n" +
                "    \"likes\" : 7\n" +
                "  } ],\n" +
                "  \"timestamp\" : 1644234125000\n" +
                "}");
    }

    @PutMapping("/api/v1/post/{id}/comments/{comment_id}/recover")
    public ResponseEntity<String> recoveryCommentByIdPost(@PathVariable Integer id, @PathVariable Integer comment_id) {
        return ResponseEntity.ok("{\n" +
                "  \"total\" : 0,\n" +
                "  \"perPage\" : 1,\n" +
                "  \"offset\" : 6,\n" +
                "  \"data\" : [ {\n" +
                "    \"comment_text\" : \"comment_text\",\n" +
                "    \"is_blocked\" : true,\n" +
                "    \"post_id\" : 4,\n" +
                "    \"my_like\" : true,\n" +
                "    \"parent_id\" : 2,\n" +
                "    \"id\" : 9,\n" +
                "    \"time\" : 3,\n" +
                "    \"sub_comments\" : [ null, null ],\n" +
                "    \"likes\" : 7\n" +
                "  }, {\n" +
                "    \"comment_text\" : \"comment_text\",\n" +
                "    \"is_blocked\" : true,\n" +
                "    \"post_id\" : 4,\n" +
                "    \"my_like\" : true,\n" +
                "    \"parent_id\" : 2,\n" +
                "    \"id\" : 9,\n" +
                "    \"time\" : 3,\n" +
                "    \"sub_comments\" : [ null, null ],\n" +
                "    \"likes\" : 7\n" +
                "  } ],\n" +
                "  \"timestamp\" : 1644234125000\n" +
                "}");
    }
}
