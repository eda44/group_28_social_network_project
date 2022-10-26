package ru.skillbox.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class FriendsControllerImpl {

    @GetMapping("/friends")
    public ResponseEntity<String> getAllFriends() {

        return createResponse();
    }

    @GetMapping("/all.my.friends/{id}")
    public ResponseEntity<String> getAllMyFriendship(@PathVariable String id) {
        return ResponseEntity.ok("{\n" +
                "  \"destPersonId\" : 1,\n" +
                "  \"statusId\" : 5,\n" +
                "  \"srcPersonId\" : 6,\n" +
                "  \"id\" : 0,\n" +
                "  \"status\" : {\n" +
                "    \"code\" : \"FRIEND\",\n" +
                "    \"id\" : 5,\n" +
                "    \"time\" : 2\n" +
                "  },\n" +
                "  \"previousStatus\" : \"previousStatus\"\n" +
                "}, {\n" +
                "  \"destPersonId\" : 1,\n" +
                "  \"statusId\" : 5,\n" +
                "  \"srcPersonId\" : 6,\n" +
                "  \"id\" : 0,\n" +
                "  \"status\" : {\n" +
                "    \"code\" : \"FRIEND\",\n" +
                "    \"id\" : 5,\n" +
                "    \"time\" : 2\n" +
                "  },\n" +
                "  \"previousStatus\" : \"previousStatus\"\n" +
                "}");
    }
    @GetMapping("/friends/recommendations")
    public ResponseEntity<String> getByRecommendation() {
        return createResponse();
    }

    @PostMapping("/friends/subscribe/{id}")
    public ResponseEntity<String> subscribe(@PathVariable String id){
        return createAbstractResponse();
    }

    @PostMapping("/friends/{id}")
    public ResponseEntity<String> addUserAsFriend(@PathVariable String id){
        return createAbstractResponse();
    }

    @PutMapping("/friends/block/{id}")
    public ResponseEntity<String> blockById(@PathVariable String id){
        return createAbstractResponse();
    }

    @DeleteMapping("/friends/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable String id){
        return ResponseEntity.ok("{\n" +
                "  \"data\" : {\n" +
                "    \"message\" : \"Ок\"\n" +
                "  },\n" +
                "  \"timestamp\" : 1644234125000\n" +
                "}");
    }

    private ResponseEntity<String> createResponse(){
        return ResponseEntity.ok("{\n" +
                "  \"data\" : [ {\n" +
                "    \"country\" : {\n" +
                "      \"id\" : 1,\n" +
                "      \"title\" : \"United States\"\n" +
                "    },\n" +
                "    \"city\" : {\n" +
                "      \"id\" : 1,\n" +
                "      \"title\" : \"Suchy Dab\"\n" +
                "    },\n" +
                "    \"birth_date\" : 702565308000,\n" +
                "    \"about\" : \"Maecenas tristique...\",\n" +
                "    \"photo\" : \"data:image/png;base64...\",\n" +
                "    \"last_name\" : \"Siegertsz\",\n" +
                "    \"reg_date\" : 1618070680000,\n" +
                "    \"is_blocked\" : false,\n" +
                "    \"message_permission\" : \"ALL\",\n" +
                "    \"last_online_time\" : 1644234125000,\n" +
                "    \"phone\" : \"+7 645 943 5082\",\n" +
                "    \"id\" : 1,\n" +
                "    \"is_online\" : true,\n" +
                "    \"first_name\" : \"Davida\",\n" +
                "    \"email\" : \"dsiegertsz0@fc2.com\",\n" +
                "    \"statusCode\" : \"FRIEND\"\n" +
                "  }, {\n" +
                "    \"country\" : {\n" +
                "      \"id\" : 1,\n" +
                "      \"title\" : \"United States\"\n" +
                "    },\n" +
                "    \"city\" : {\n" +
                "      \"id\" : 1,\n" +
                "      \"title\" : \"Suchy Dab\"\n" +
                "    },\n" +
                "    \"birth_date\" : 702565308000,\n" +
                "    \"about\" : \"Maecenas tristique...\",\n" +
                "    \"photo\" : \"data:image/png;base64...\",\n" +
                "    \"last_name\" : \"Siegertsz\",\n" +
                "    \"reg_date\" : 1618070680000,\n" +
                "    \"is_blocked\" : false,\n" +
                "    \"message_permission\" : \"ALL\",\n" +
                "    \"last_online_time\" : 1644234125000,\n" +
                "    \"phone\" : \"+7 645 943 5082\",\n" +
                "    \"id\" : 1,\n" +
                "    \"is_online\" : true,\n" +
                "    \"first_name\" : \"Davida\",\n" +
                "    \"email\" : \"dsiegertsz0@fc2.com\",\n" +
                "    \"statusCode\" : \"FRIEND\"\n" +
                "  } ],\n" +
                "  \"error_description\" : \"Неверные учетные данные\",\n" +
                "  \"error\" : \"Неверный запрос\",\n" +
                "  \"timestamp\" : 1644234125000\n" +
                "}");
    }

    private ResponseEntity<String> createAbstractResponse(){
        return ResponseEntity.ok("{\n" +
                "  \"data\" : {\n" +
                "    \"message\" : \"Ок\"\n" +
                "  },\n" +
                "  \"timestamp\" : 1644234125000\n" +
                "}");
    }

}
