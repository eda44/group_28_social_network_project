package ru.skillbox.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skillbox.model.Tag;
import ru.skillbox.request.PostTagRequest;
import ru.skillbox.model.TagInterface;

@RestController
public class TagController implements TagInterface {

    @GetMapping("/api/v1/tags/")
    public ResponseEntity<String> getTags(Tag tag, Integer offset, Integer itemPerPage) {
        return ResponseEntity.ok("{\n" +
                "  \"total\" : 6,\n" +
                "  \"perPage\" : 5,\n" +
                "  \"offset\" : 1,\n" +
                "  \"data\" : [ {\n" +
                "    \"id\" : 5,\n" +
                "    \"tag\" : \"tag\"\n" +
                "  }, {\n" +
                "    \"id\" : 5,\n" +
                "    \"tag\" : \"tag\"\n" +
                "  } ],\n" +
                "  \"dataPosts\" : [ {\n" +
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
                "  \"timestamp\" : 0\n" +
                "}");
    }

    @PutMapping("/api/v1/tags/{postId}")
    public ResponseEntity<String> putTag(@PathVariable Integer postId, @RequestBody PostTagRequest request) {
        return ResponseEntity.ok("{\n" +
                "  \"total\" : 6,\n" +
                "  \"perPage\" : 5,\n" +
                "  \"offset\" : 1,\n" +
                "  \"data\" : [ {\n" +
                "    \"id\" : 5,\n" +
                "    \"tag\" : \"tag\"\n" +
                "  }, {\n" +
                "    \"id\" : 5,\n" +
                "    \"tag\" : \"tag\"\n" +
                "  } ],\n" +
                "  \"dataPosts\" : [ {\n" +
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
                "  \"timestamp\" : 0\n" +
                "}");
    }

    @DeleteMapping("/api/v1/tags/{postId}/{tagId}")
    public ResponseEntity<String> deleteTag(@PathVariable Integer postId, @PathVariable Integer tagId) {
        return ResponseEntity.ok("{\n" +
                "  \"message\" : \"message\",\n" +
                "  \"timestamp\" : 0\n" +
                "}");
    }
}
