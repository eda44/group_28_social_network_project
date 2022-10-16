package ru.skillbox.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skillbox.model.api.request.CommentAddRequest;
import ru.skillbox.model.api.request.PostAddRequest;
import ru.skillbox.model.api.request.PostCommentInterface;
import ru.skillbox.service.PostService;

@RestController
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/api/v1/post")
    public ResponseEntity<String> addNewPost(@RequestBody PostAddRequest request) {
        postService.postAddRs(request);
        return ResponseEntity.ok("{\n" +
                "  \"status\" : false\n" +
                "}");
    }

    @GetMapping("/api/v1/post")
    public ResponseEntity<Object> getPostsAll() {
        return ResponseEntity.ok("{\n" +
                "  \"total\" : 1,\n" +
                "  \"size\" : 20,\n" +
                "  \"data\" : [ {\n" +
                "    \"is_blocked\" : true,\n" +
                "    \"comments\" : [ {\n" +
                "      \"comment_text\" : \"comment_text\",\n" +
                "      \"is_blocked\" : true,\n" +
                "      \"post_id\" : 4,\n" +
                "      \"my_like\" : true,\n" +
                "      \"parent_id\" : 2,\n" +
                "      \"id\" : 9,\n" +
                "      \"time\" : 3,\n" +
                "      \"sub_comments\" : [ null, null ],\n" +
                "      \"likes\" : 7\n" +
                "    }, {\n" +
                "      \"comment_text\" : \"comment_text\",\n" +
                "      \"is_blocked\" : true,\n" +
                "      \"post_id\" : 4,\n" +
                "      \"my_like\" : true,\n" +
                "      \"parent_id\" : 2,\n" +
                "      \"id\" : 9,\n" +
                "      \"time\" : 3,\n" +
                "      \"sub_comments\" : [ null, null ],\n" +
                "      \"likes\" : 7\n" +
                "    } ],\n" +
                "    \"my_like\" : true,\n" +
                "    \"author\" : {\n" +
                "      \"country\" : {\n" +
                "        \"id\" : 1,\n" +
                "        \"title\" : \"United States\"\n" +
                "      },\n" +
                "      \"city\" : {\n" +
                "        \"id\" : 1,\n" +
                "        \"title\" : \"Suchy Dab\"\n" +
                "      },\n" +
                "      \"birth_date\" : 702565308000,\n" +
                "      \"about\" : \"Maecenas tristique...\",\n" +
                "      \"photo\" : \"data:image/png;base64...\",\n" +
                "      \"last_name\" : \"Siegertsz\",\n" +
                "      \"reg_date\" : 1618070680000,\n" +
                "      \"is_blocked\" : false,\n" +
                "      \"message_permission\" : \"ALL\",\n" +
                "      \"last_online_time\" : 1644234125000,\n" +
                "      \"phone\" : \"+7 645 943 5082\",\n" +
                "      \"id\" : 1,\n" +
                "      \"is_online\" : true,\n" +
                "      \"first_name\" : \"Davida\",\n" +
                "      \"email\" : \"dsiegertsz0@fc2.com\"\n" +
                "    },\n" +
                "    \"id\" : 2,\n" +
                "    \"time\" : 7,\n" +
                "    \"photo_url\" : \"photo_url\",\n" +
                "    \"title\" : \"title\",\n" +
                "    \"type\" : \"POSTED\",\n" +
                "    \"post_text\" : \"post_text\",\n" +
                "    \"tags\" : [ null, null ],\n" +
                "    \"likes\" : 1\n" +
                "  }, {\n" +
                "    \"is_blocked\" : true,\n" +
                "    \"comments\" : [ {\n" +
                "      \"comment_text\" : \"comment_text\",\n" +
                "      \"is_blocked\" : true,\n" +
                "      \"post_id\" : 4,\n" +
                "      \"my_like\" : true,\n" +
                "      \"parent_id\" : 2,\n" +
                "      \"id\" : 9,\n" +
                "      \"time\" : 3,\n" +
                "      \"sub_comments\" : [ null, null ],\n" +
                "      \"likes\" : 7\n" +
                "    }, {\n" +
                "      \"comment_text\" : \"comment_text\",\n" +
                "      \"is_blocked\" : true,\n" +
                "      \"post_id\" : 4,\n" +
                "      \"my_like\" : true,\n" +
                "      \"parent_id\" : 2,\n" +
                "      \"id\" : 9,\n" +
                "      \"time\" : 3,\n" +
                "      \"sub_comments\" : [ null, null ],\n" +
                "      \"likes\" : 7\n" +
                "    } ],\n" +
                "    \"my_like\" : true,\n" +
                "    \"author\" : {\n" +
                "      \"country\" : {\n" +
                "        \"id\" : 1,\n" +
                "        \"title\" : \"United States\"\n" +
                "      },\n" +
                "      \"city\" : {\n" +
                "        \"id\" : 1,\n" +
                "        \"title\" : \"Suchy Dab\"\n" +
                "      },\n" +
                "      \"birth_date\" : 702565308000,\n" +
                "      \"about\" : \"Maecenas tristique...\",\n" +
                "      \"photo\" : \"data:image/png;base64...\",\n" +
                "      \"last_name\" : \"Siegertsz\",\n" +
                "      \"reg_date\" : 1618070680000,\n" +
                "      \"is_blocked\" : false,\n" +
                "      \"message_permission\" : \"ALL\",\n" +
                "      \"last_online_time\" : 1644234125000,\n" +
                "      \"phone\" : \"+7 645 943 5082\",\n" +
                "      \"id\" : 1,\n" +
                "      \"is_online\" : true,\n" +
                "      \"first_name\" : \"Davida\",\n" +
                "      \"email\" : \"dsiegertsz0@fc2.com\"\n" +
                "    },\n" +
                "    \"id\" : 2,\n" +
                "    \"time\" : 7,\n" +
                "    \"photo_url\" : \"photo_url\",\n" +
                "    \"title\" : \"title\",\n" +
                "    \"type\" : \"POSTED\",\n" +
                "    \"post_text\" : \"post_text\",\n" +
                "    \"tags\" : [ null, null ],\n" +
                "    \"likes\" : 1\n" +
                "  } ],\n" +
                "  \"page\" : 1,\n" +
                "  \"timestamp\" : 16442341250\n" +
                "}");
    }

    @DeleteMapping("/api/v1/post/{id}")
    public ResponseEntity<String> deletePostById(@PathVariable Integer id) {
        return ResponseEntity.ok("{\n" +
                "  \"total\" : 1,\n" +
                "  \"size\" : 20,\n" +
                "  \"data\" : [ {\n" +
                "    \"is_blocked\" : true,\n" +
                "    \"comments\" : [ {\n" +
                "      \"comment_text\" : \"comment_text\",\n" +
                "      \"is_blocked\" : true,\n" +
                "      \"post_id\" : 4,\n" +
                "      \"my_like\" : true,\n" +
                "      \"parent_id\" : 2,\n" +
                "      \"id\" : 9,\n" +
                "      \"time\" : 3,\n" +
                "      \"sub_comments\" : [ null, null ],\n" +
                "      \"likes\" : 7\n" +
                "    }, {\n" +
                "      \"comment_text\" : \"comment_text\",\n" +
                "      \"is_blocked\" : true,\n" +
                "      \"post_id\" : 4,\n" +
                "      \"my_like\" : true,\n" +
                "      \"parent_id\" : 2,\n" +
                "      \"id\" : 9,\n" +
                "      \"time\" : 3,\n" +
                "      \"sub_comments\" : [ null, null ],\n" +
                "      \"likes\" : 7\n" +
                "    } ],\n" +
                "    \"my_like\" : true,\n" +
                "    \"author\" : {\n" +
                "      \"country\" : {\n" +
                "        \"id\" : 1,\n" +
                "        \"title\" : \"United States\"\n" +
                "      },\n" +
                "      \"city\" : {\n" +
                "        \"id\" : 1,\n" +
                "        \"title\" : \"Suchy Dab\"\n" +
                "      },\n" +
                "      \"birth_date\" : 702565308000,\n" +
                "      \"about\" : \"Maecenas tristique...\",\n" +
                "      \"photo\" : \"data:image/png;base64...\",\n" +
                "      \"last_name\" : \"Siegertsz\",\n" +
                "      \"reg_date\" : 1618070680000,\n" +
                "      \"is_blocked\" : false,\n" +
                "      \"message_permission\" : \"ALL\",\n" +
                "      \"last_online_time\" : 1644234125000,\n" +
                "      \"phone\" : \"+7 645 943 5082\",\n" +
                "      \"id\" : 1,\n" +
                "      \"is_online\" : true,\n" +
                "      \"first_name\" : \"Davida\",\n" +
                "      \"email\" : \"dsiegertsz0@fc2.com\"\n" +
                "    },\n" +
                "    \"id\" : 2,\n" +
                "    \"time\" : 7,\n" +
                "    \"photo_url\" : \"photo_url\",\n" +
                "    \"title\" : \"title\",\n" +
                "    \"type\" : \"POSTED\",\n" +
                "    \"post_text\" : \"post_text\",\n" +
                "    \"tags\" : [ null, null ],\n" +
                "    \"likes\" : 1\n" +
                "  }, {\n" +
                "    \"is_blocked\" : true,\n" +
                "    \"comments\" : [ {\n" +
                "      \"comment_text\" : \"comment_text\",\n" +
                "      \"is_blocked\" : true,\n" +
                "      \"post_id\" : 4,\n" +
                "      \"my_like\" : true,\n" +
                "      \"parent_id\" : 2,\n" +
                "      \"id\" : 9,\n" +
                "      \"time\" : 3,\n" +
                "      \"sub_comments\" : [ null, null ],\n" +
                "      \"likes\" : 7\n" +
                "    }, {\n" +
                "      \"comment_text\" : \"comment_text\",\n" +
                "      \"is_blocked\" : true,\n" +
                "      \"post_id\" : 4,\n" +
                "      \"my_like\" : true,\n" +
                "      \"parent_id\" : 2,\n" +
                "      \"id\" : 9,\n" +
                "      \"time\" : 3,\n" +
                "      \"sub_comments\" : [ null, null ],\n" +
                "      \"likes\" : 7\n" +
                "    } ],\n" +
                "    \"my_like\" : true,\n" +
                "    \"author\" : {\n" +
                "      \"country\" : {\n" +
                "        \"id\" : 1,\n" +
                "        \"title\" : \"United States\"\n" +
                "      },\n" +
                "      \"city\" : {\n" +
                "        \"id\" : 1,\n" +
                "        \"title\" : \"Suchy Dab\"\n" +
                "      },\n" +
                "      \"birth_date\" : 702565308000,\n" +
                "      \"about\" : \"Maecenas tristique...\",\n" +
                "      \"photo\" : \"data:image/png;base64...\",\n" +
                "      \"last_name\" : \"Siegertsz\",\n" +
                "      \"reg_date\" : 1618070680000,\n" +
                "      \"is_blocked\" : false,\n" +
                "      \"message_permission\" : \"ALL\",\n" +
                "      \"last_online_time\" : 1644234125000,\n" +
                "      \"phone\" : \"+7 645 943 5082\",\n" +
                "      \"id\" : 1,\n" +
                "      \"is_online\" : true,\n" +
                "      \"first_name\" : \"Davida\",\n" +
                "      \"email\" : \"dsiegertsz0@fc2.com\"\n" +
                "    },\n" +
                "    \"id\" : 2,\n" +
                "    \"time\" : 7,\n" +
                "    \"photo_url\" : \"photo_url\",\n" +
                "    \"title\" : \"title\",\n" +
                "    \"type\" : \"POSTED\",\n" +
                "    \"post_text\" : \"post_text\",\n" +
                "    \"tags\" : [ null, null ],\n" +
                "    \"likes\" : 1\n" +
                "  } ],\n" +
                "  \"page\" : 1,\n" +
                "  \"timestamp\" : 16442341250\n" +
                "}");
    }


    @GetMapping("/api/v1/post/{id}")
    public ResponseEntity<String> getPostById(@PathVariable Integer id) {
        return ResponseEntity.ok("{\n" +
                "  \"total\" : 1,\n" +
                "  \"size\" : 20,\n" +
                "  \"data\" : [ {\n" +
                "    \"is_blocked\" : true,\n" +
                "    \"comments\" : [ {\n" +
                "      \"comment_text\" : \"comment_text\",\n" +
                "      \"is_blocked\" : true,\n" +
                "      \"post_id\" : 4,\n" +
                "      \"my_like\" : true,\n" +
                "      \"parent_id\" : 2,\n" +
                "      \"id\" : 9,\n" +
                "      \"time\" : 3,\n" +
                "      \"sub_comments\" : [ null, null ],\n" +
                "      \"likes\" : 7\n" +
                "    }, {\n" +
                "      \"comment_text\" : \"comment_text\",\n" +
                "      \"is_blocked\" : true,\n" +
                "      \"post_id\" : 4,\n" +
                "      \"my_like\" : true,\n" +
                "      \"parent_id\" : 2,\n" +
                "      \"id\" : 9,\n" +
                "      \"time\" : 3,\n" +
                "      \"sub_comments\" : [ null, null ],\n" +
                "      \"likes\" : 7\n" +
                "    } ],\n" +
                "    \"my_like\" : true,\n" +
                "    \"author\" : {\n" +
                "      \"country\" : {\n" +
                "        \"id\" : 1,\n" +
                "        \"title\" : \"United States\"\n" +
                "      },\n" +
                "      \"city\" : {\n" +
                "        \"id\" : 1,\n" +
                "        \"title\" : \"Suchy Dab\"\n" +
                "      },\n" +
                "      \"birth_date\" : 702565308000,\n" +
                "      \"about\" : \"Maecenas tristique...\",\n" +
                "      \"photo\" : \"data:image/png;base64...\",\n" +
                "      \"last_name\" : \"Siegertsz\",\n" +
                "      \"reg_date\" : 1618070680000,\n" +
                "      \"is_blocked\" : false,\n" +
                "      \"message_permission\" : \"ALL\",\n" +
                "      \"last_online_time\" : 1644234125000,\n" +
                "      \"phone\" : \"+7 645 943 5082\",\n" +
                "      \"id\" : 1,\n" +
                "      \"is_online\" : true,\n" +
                "      \"first_name\" : \"Davida\",\n" +
                "      \"email\" : \"dsiegertsz0@fc2.com\"\n" +
                "    },\n" +
                "    \"id\" : 2,\n" +
                "    \"time\" : 7,\n" +
                "    \"photo_url\" : \"photo_url\",\n" +
                "    \"title\" : \"title\",\n" +
                "    \"type\" : \"POSTED\",\n" +
                "    \"post_text\" : \"post_text\",\n" +
                "    \"tags\" : [ null, null ],\n" +
                "    \"likes\" : 1\n" +
                "  }, {\n" +
                "    \"is_blocked\" : true,\n" +
                "    \"comments\" : [ {\n" +
                "      \"comment_text\" : \"comment_text\",\n" +
                "      \"is_blocked\" : true,\n" +
                "      \"post_id\" : 4,\n" +
                "      \"my_like\" : true,\n" +
                "      \"parent_id\" : 2,\n" +
                "      \"id\" : 9,\n" +
                "      \"time\" : 3,\n" +
                "      \"sub_comments\" : [ null, null ],\n" +
                "      \"likes\" : 7\n" +
                "    }, {\n" +
                "      \"comment_text\" : \"comment_text\",\n" +
                "      \"is_blocked\" : true,\n" +
                "      \"post_id\" : 4,\n" +
                "      \"my_like\" : true,\n" +
                "      \"parent_id\" : 2,\n" +
                "      \"id\" : 9,\n" +
                "      \"time\" : 3,\n" +
                "      \"sub_comments\" : [ null, null ],\n" +
                "      \"likes\" : 7\n" +
                "    } ],\n" +
                "    \"my_like\" : true,\n" +
                "    \"author\" : {\n" +
                "      \"country\" : {\n" +
                "        \"id\" : 1,\n" +
                "        \"title\" : \"United States\"\n" +
                "      },\n" +
                "      \"city\" : {\n" +
                "        \"id\" : 1,\n" +
                "        \"title\" : \"Suchy Dab\"\n" +
                "      },\n" +
                "      \"birth_date\" : 702565308000,\n" +
                "      \"about\" : \"Maecenas tristique...\",\n" +
                "      \"photo\" : \"data:image/png;base64...\",\n" +
                "      \"last_name\" : \"Siegertsz\",\n" +
                "      \"reg_date\" : 1618070680000,\n" +
                "      \"is_blocked\" : false,\n" +
                "      \"message_permission\" : \"ALL\",\n" +
                "      \"last_online_time\" : 1644234125000,\n" +
                "      \"phone\" : \"+7 645 943 5082\",\n" +
                "      \"id\" : 1,\n" +
                "      \"is_online\" : true,\n" +
                "      \"first_name\" : \"Davida\",\n" +
                "      \"email\" : \"dsiegertsz0@fc2.com\"\n" +
                "    },\n" +
                "    \"id\" : 2,\n" +
                "    \"time\" : 7,\n" +
                "    \"photo_url\" : \"photo_url\",\n" +
                "    \"title\" : \"title\",\n" +
                "    \"type\" : \"POSTED\",\n" +
                "    \"post_text\" : \"post_text\",\n" +
                "    \"tags\" : [ null, null ],\n" +
                "    \"likes\" : 1\n" +
                "  } ],\n" +
                "  \"page\" : 1,\n" +
                "  \"timestamp\" : 16442341250\n" +
                "}");
    }

    @PostMapping("/api/v1/post/{id}/report")
    public ResponseEntity<String> addNewComplaint(@PathVariable Integer id) {
        return ResponseEntity.ok("{\n" +
                "  \"data\" : [ {\n" +
                "    \"message\" : \"message\"\n" +
                "  }, {\n" +
                "    \"message\" : \"message\"\n" +
                "  } ],\n" +
                "  \"error_description\" : \"items: OrderedMap { \\\"type\\\": \\\"object\\\", \\\"properties\\\": OrderedMap { \\\"error_description\\\": OrderedMap { \\\"type\\\": \\\"string\\\", \\\"description\\\": \\\"Error description\\\", \\\"enum\\\": List [ \\\"Unauthorized\\\", \\\"An authorization code must be supplied\\\", \\\"Redirect URI mismatch\\\", \\\"Invalid authorization code: CODE\\\", \\\"Bad credentials\\\" ] } }, \\\"$$ref\\\": \\\"#/components/schemas/ErrorDescription\",\n" +
                "  \"timestamp\" : 16442341250\n" +
                "}");
    }

    @PutMapping("/api/v1/post/{id}")
    public ResponseEntity<String> putPostById(@RequestBody PostAddRequest request, @PathVariable Integer id) {
        return ResponseEntity.ok("{\n" +
                "  \"total\" : 1,\n" +
                "  \"size\" : 20,\n" +
                "  \"data\" : [ {\n" +
                "    \"is_blocked\" : true,\n" +
                "    \"comments\" : [ {\n" +
                "      \"comment_text\" : \"comment_text\",\n" +
                "      \"is_blocked\" : true,\n" +
                "      \"post_id\" : 4,\n" +
                "      \"my_like\" : true,\n" +
                "      \"parent_id\" : 2,\n" +
                "      \"id\" : 9,\n" +
                "      \"time\" : 3,\n" +
                "      \"sub_comments\" : [ null, null ],\n" +
                "      \"likes\" : 7\n" +
                "    }, {\n" +
                "      \"comment_text\" : \"comment_text\",\n" +
                "      \"is_blocked\" : true,\n" +
                "      \"post_id\" : 4,\n" +
                "      \"my_like\" : true,\n" +
                "      \"parent_id\" : 2,\n" +
                "      \"id\" : 9,\n" +
                "      \"time\" : 3,\n" +
                "      \"sub_comments\" : [ null, null ],\n" +
                "      \"likes\" : 7\n" +
                "    } ],\n" +
                "    \"my_like\" : true,\n" +
                "    \"author\" : {\n" +
                "      \"country\" : {\n" +
                "        \"id\" : 1,\n" +
                "        \"title\" : \"United States\"\n" +
                "      },\n" +
                "      \"city\" : {\n" +
                "        \"id\" : 1,\n" +
                "        \"title\" : \"Suchy Dab\"\n" +
                "      },\n" +
                "      \"birth_date\" : 702565308000,\n" +
                "      \"about\" : \"Maecenas tristique...\",\n" +
                "      \"photo\" : \"data:image/png;base64...\",\n" +
                "      \"last_name\" : \"Siegertsz\",\n" +
                "      \"reg_date\" : 1618070680000,\n" +
                "      \"is_blocked\" : false,\n" +
                "      \"message_permission\" : \"ALL\",\n" +
                "      \"last_online_time\" : 1644234125000,\n" +
                "      \"phone\" : \"+7 645 943 5082\",\n" +
                "      \"id\" : 1,\n" +
                "      \"is_online\" : true,\n" +
                "      \"first_name\" : \"Davida\",\n" +
                "      \"email\" : \"dsiegertsz0@fc2.com\"\n" +
                "    },\n" +
                "    \"id\" : 2,\n" +
                "    \"time\" : 7,\n" +
                "    \"photo_url\" : \"photo_url\",\n" +
                "    \"title\" : \"title\",\n" +
                "    \"type\" : \"POSTED\",\n" +
                "    \"post_text\" : \"post_text\",\n" +
                "    \"tags\" : [ null, null ],\n" +
                "    \"likes\" : 1\n" +
                "  }, {\n" +
                "    \"is_blocked\" : true,\n" +
                "    \"comments\" : [ {\n" +
                "      \"comment_text\" : \"comment_text\",\n" +
                "      \"is_blocked\" : true,\n" +
                "      \"post_id\" : 4,\n" +
                "      \"my_like\" : true,\n" +
                "      \"parent_id\" : 2,\n" +
                "      \"id\" : 9,\n" +
                "      \"time\" : 3,\n" +
                "      \"sub_comments\" : [ null, null ],\n" +
                "      \"likes\" : 7\n" +
                "    }, {\n" +
                "      \"comment_text\" : \"comment_text\",\n" +
                "      \"is_blocked\" : true,\n" +
                "      \"post_id\" : 4,\n" +
                "      \"my_like\" : true,\n" +
                "      \"parent_id\" : 2,\n" +
                "      \"id\" : 9,\n" +
                "      \"time\" : 3,\n" +
                "      \"sub_comments\" : [ null, null ],\n" +
                "      \"likes\" : 7\n" +
                "    } ],\n" +
                "    \"my_like\" : true,\n" +
                "    \"author\" : {\n" +
                "      \"country\" : {\n" +
                "        \"id\" : 1,\n" +
                "        \"title\" : \"United States\"\n" +
                "      },\n" +
                "      \"city\" : {\n" +
                "        \"id\" : 1,\n" +
                "        \"title\" : \"Suchy Dab\"\n" +
                "      },\n" +
                "      \"birth_date\" : 702565308000,\n" +
                "      \"about\" : \"Maecenas tristique...\",\n" +
                "      \"photo\" : \"data:image/png;base64...\",\n" +
                "      \"last_name\" : \"Siegertsz\",\n" +
                "      \"reg_date\" : 1618070680000,\n" +
                "      \"is_blocked\" : false,\n" +
                "      \"message_permission\" : \"ALL\",\n" +
                "      \"last_online_time\" : 1644234125000,\n" +
                "      \"phone\" : \"+7 645 943 5082\",\n" +
                "      \"id\" : 1,\n" +
                "      \"is_online\" : true,\n" +
                "      \"first_name\" : \"Davida\",\n" +
                "      \"email\" : \"dsiegertsz0@fc2.com\"\n" +
                "    },\n" +
                "    \"id\" : 2,\n" +
                "    \"time\" : 7,\n" +
                "    \"photo_url\" : \"photo_url\",\n" +
                "    \"title\" : \"title\",\n" +
                "    \"type\" : \"POSTED\",\n" +
                "    \"post_text\" : \"post_text\",\n" +
                "    \"tags\" : [ null, null ],\n" +
                "    \"likes\" : 1\n" +
                "  } ],\n" +
                "  \"page\" : 1,\n" +
                "  \"timestamp\" : 16442341250\n" +
                "}");
    }

    @PutMapping("/api/v1/post/{id}/recover")
    public ResponseEntity<String> recoveryPostById(@PathVariable Integer id) {
return ResponseEntity.ok("{\n" +
        "  \"total\" : 1,\n" +
        "  \"size\" : 20,\n" +
        "  \"data\" : [ {\n" +
        "    \"is_blocked\" : true,\n" +
        "    \"comments\" : [ {\n" +
        "      \"comment_text\" : \"comment_text\",\n" +
        "      \"is_blocked\" : true,\n" +
        "      \"post_id\" : 4,\n" +
        "      \"my_like\" : true,\n" +
        "      \"parent_id\" : 2,\n" +
        "      \"id\" : 9,\n" +
        "      \"time\" : 3,\n" +
        "      \"sub_comments\" : [ null, null ],\n" +
        "      \"likes\" : 7\n" +
        "    }, {\n" +
        "      \"comment_text\" : \"comment_text\",\n" +
        "      \"is_blocked\" : true,\n" +
        "      \"post_id\" : 4,\n" +
        "      \"my_like\" : true,\n" +
        "      \"parent_id\" : 2,\n" +
        "      \"id\" : 9,\n" +
        "      \"time\" : 3,\n" +
        "      \"sub_comments\" : [ null, null ],\n" +
        "      \"likes\" : 7\n" +
        "    } ],\n" +
        "    \"my_like\" : true,\n" +
        "    \"author\" : {\n" +
        "      \"country\" : {\n" +
        "        \"id\" : 1,\n" +
        "        \"title\" : \"United States\"\n" +
        "      },\n" +
        "      \"city\" : {\n" +
        "        \"id\" : 1,\n" +
        "        \"title\" : \"Suchy Dab\"\n" +
        "      },\n" +
        "      \"birth_date\" : 702565308000,\n" +
        "      \"about\" : \"Maecenas tristique...\",\n" +
        "      \"photo\" : \"data:image/png;base64...\",\n" +
        "      \"last_name\" : \"Siegertsz\",\n" +
        "      \"reg_date\" : 1618070680000,\n" +
        "      \"is_blocked\" : false,\n" +
        "      \"message_permission\" : \"ALL\",\n" +
        "      \"last_online_time\" : 1644234125000,\n" +
        "      \"phone\" : \"+7 645 943 5082\",\n" +
        "      \"id\" : 1,\n" +
        "      \"is_online\" : true,\n" +
        "      \"first_name\" : \"Davida\",\n" +
        "      \"email\" : \"dsiegertsz0@fc2.com\"\n" +
        "    },\n" +
        "    \"id\" : 2,\n" +
        "    \"time\" : 7,\n" +
        "    \"photo_url\" : \"photo_url\",\n" +
        "    \"title\" : \"title\",\n" +
        "    \"type\" : \"POSTED\",\n" +
        "    \"post_text\" : \"post_text\",\n" +
        "    \"tags\" : [ null, null ],\n" +
        "    \"likes\" : 1\n" +
        "  }, {\n" +
        "    \"is_blocked\" : true,\n" +
        "    \"comments\" : [ {\n" +
        "      \"comment_text\" : \"comment_text\",\n" +
        "      \"is_blocked\" : true,\n" +
        "      \"post_id\" : 4,\n" +
        "      \"my_like\" : true,\n" +
        "      \"parent_id\" : 2,\n" +
        "      \"id\" : 9,\n" +
        "      \"time\" : 3,\n" +
        "      \"sub_comments\" : [ null, null ],\n" +
        "      \"likes\" : 7\n" +
        "    }, {\n" +
        "      \"comment_text\" : \"comment_text\",\n" +
        "      \"is_blocked\" : true,\n" +
        "      \"post_id\" : 4,\n" +
        "      \"my_like\" : true,\n" +
        "      \"parent_id\" : 2,\n" +
        "      \"id\" : 9,\n" +
        "      \"time\" : 3,\n" +
        "      \"sub_comments\" : [ null, null ],\n" +
        "      \"likes\" : 7\n" +
        "    } ],\n" +
        "    \"my_like\" : true,\n" +
        "    \"author\" : {\n" +
        "      \"country\" : {\n" +
        "        \"id\" : 1,\n" +
        "        \"title\" : \"United States\"\n" +
        "      },\n" +
        "      \"city\" : {\n" +
        "        \"id\" : 1,\n" +
        "        \"title\" : \"Suchy Dab\"\n" +
        "      },\n" +
        "      \"birth_date\" : 702565308000,\n" +
        "      \"about\" : \"Maecenas tristique...\",\n" +
        "      \"photo\" : \"data:image/png;base64...\",\n" +
        "      \"last_name\" : \"Siegertsz\",\n" +
        "      \"reg_date\" : 1618070680000,\n" +
        "      \"is_blocked\" : false,\n" +
        "      \"message_permission\" : \"ALL\",\n" +
        "      \"last_online_time\" : 1644234125000,\n" +
        "      \"phone\" : \"+7 645 943 5082\",\n" +
        "      \"id\" : 1,\n" +
        "      \"is_online\" : true,\n" +
        "      \"first_name\" : \"Davida\",\n" +
        "      \"email\" : \"dsiegertsz0@fc2.com\"\n" +
        "    },\n" +
        "    \"id\" : 2,\n" +
        "    \"time\" : 7,\n" +
        "    \"photo_url\" : \"photo_url\",\n" +
        "    \"title\" : \"title\",\n" +
        "    \"type\" : \"POSTED\",\n" +
        "    \"post_text\" : \"post_text\",\n" +
        "    \"tags\" : [ null, null ],\n" +
        "    \"likes\" : 1\n" +
        "  } ],\n" +
        "  \"page\" : 1,\n" +
        "  \"timestamp\" : 16442341250\n" +
        "}");
    }
}