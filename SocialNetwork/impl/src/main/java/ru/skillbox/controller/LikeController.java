package ru.skillbox.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skillbox.dto.enums.LikeType;
import ru.skillbox.request.PostLikeRequest;

@RestController
public class LikeController {

    @GetMapping("/api/v1/liked")
    public ResponseEntity<String> getLiked(Long id, LikeType type) {
        return ResponseEntity.ok("{\n" +
                "  \"data\" : [ {\n" +
                "    \"liked\" : true,\n" +
                "    \"users\" : [ 6, 6 ],\n" +
                "    \"likes\" : 0\n" +
                "  }, {\n" +
                "    \"liked\" : true,\n" +
                "    \"users\" : [ 6, 6 ],\n" +
                "    \"likes\" : 0\n" +
                "  } ],\n" +
                "  \"errorDescription\" : \"errorDescription\",\n" +
                "  \"error\" : \"error\",\n" +
                "  \"timestamp\" : 16442341250\n" +
                "}");
    }

    @GetMapping("/api/v1/likes")
    public ResponseEntity<String> getLikes(Long id, LikeType type) {
        return ResponseEntity.ok("{\n" +
                "  \"data\" : [ {\n" +
                "    \"liked\" : true,\n" +
                "    \"users\" : [ 6, 6 ],\n" +
                "    \"likes\" : 0\n" +
                "  }, {\n" +
                "    \"liked\" : true,\n" +
                "    \"users\" : [ 6, 6 ],\n" +
                "    \"likes\" : 0\n" +
                "  } ],\n" +
                "  \"errorDescription\" : \"errorDescription\",\n" +
                "  \"error\" : \"error\",\n" +
                "  \"timestamp\" : 16442341250\n" +
                "}");
    }

    @PutMapping("/api/v1/likes")
    public ResponseEntity<String> putLike(@RequestBody PostLikeRequest request) {
        return ResponseEntity.ok("{\n" +
                "  \"data\" : [ {\n" +
                "    \"liked\" : true,\n" +
                "    \"users\" : [ 6, 6 ],\n" +
                "    \"likes\" : 0\n" +
                "  }, {\n" +
                "    \"liked\" : true,\n" +
                "    \"users\" : [ 6, 6 ],\n" +
                "    \"likes\" : 0\n" +
                "  } ],\n" +
                "  \"errorDescription\" : \"errorDescription\",\n" +
                "  \"error\" : \"error\",\n" +
                "  \"timestamp\" : 16442341250\n" +
                "}");
    }

    @DeleteMapping("/api/v1/likes")
    public ResponseEntity<String> deleteLike() {
        return ResponseEntity.ok("{\n" +
                "  \"data\" : [ {\n" +
                "    \"liked\" : true,\n" +
                "    \"users\" : [ 6, 6 ],\n" +
                "    \"likes\" : 0\n" +
                "  }, {\n" +
                "    \"liked\" : true,\n" +
                "    \"users\" : [ 6, 6 ],\n" +
                "    \"likes\" : 0\n" +
                "  } ],\n" +
                "  \"errorDescription\" : \"errorDescription\",\n" +
                "  \"error\" : \"error\",\n" +
                "  \"timestamp\" : 16442341250\n" +
                "}");
    }

}
