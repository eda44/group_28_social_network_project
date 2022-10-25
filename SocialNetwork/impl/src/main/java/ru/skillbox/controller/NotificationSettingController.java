package ru.skillbox.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skillbox.dto.AccountByIdDto;
import ru.skillbox.dto.NotificationInputDto;
import ru.skillbox.request.SettingRq;

@RestController
@RequestMapping("/api/v1")
public class NotificationSettingController {


    @GetMapping("/notifications/settings")
    public ResponseEntity<Object> getNotificationsSettings(){
        return ResponseEntity.ok("{\n" +
                "  \"time\": 0,\n" +
                "  \"data\": [\n" +
                "    {\n" +
                "      \"enable\": true,\n" +
                "      \"notification_type\": \"MESSAGE\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"user_id\": 1\n" +
                "}");
    }

    @PutMapping("/notifications/settings")
    public ResponseEntity<Object> putNotificationsSettings(@RequestBody SettingRq settingRq){
        return ResponseEntity.ok("{\n" +
                "  \"enable\": true,\n" +
                "  \"notification_type\": \"MESSAGE\"\n" +
                "}");
    }

    @PostMapping("/notifications/settings")
    public ResponseEntity<Object> createSettingsNotification(@RequestBody AccountByIdDto accountByIdDto){
        return ResponseEntity.ok("{\n" +
                "  \"id\": 1,\n" +
                "  \"email\": \"dsiegertsz0@fc2.com\",\n" +
                "  \"phone\": \"+7 645 943 5082\",\n" +
                "  \"photo\": \"data:image/png;base64...\",\n" +
                "  \"about\": \"Maecenas tristique...\",\n" +
                "  \"city\": {\n" +
                "    \"id\": 0,\n" +
                "    \"title\": \"string\",\n" +
                "    \"country_id\": 0\n" +
                "  },\n" +
                "  \"country\": {\n" +
                "    \"id\": 0,\n" +
                "    \"title\": \"string\",\n" +
                "    \"cities\": [\n" +
                "      {\n" +
                "        \"id\": 0,\n" +
                "        \"title\": \"string\",\n" +
                "        \"country_id\": 0\n" +
                "      }\n" +
                "    ]\n" +
                "  },\n" +
                "  \"first_name\": \"Davida\",\n" +
                "  \"last_name\": \"Siegertsz\",\n" +
                "  \"reg_date\": 1618070680000,\n" +
                "  \"birth_date\": 702565308000,\n" +
                "  \"message_permission\": \"ALL\",\n" +
                "  \"last_online_time\": 1644234125000,\n" +
                "  \"is_online\": true,\n" +
                "  \"is_blocked\": false\n" +
                "}");
    }
    @GetMapping("/notifications")
    public ResponseEntity<Object> getNotification(){return ResponseEntity.ok("{\n" +
            "  \"timestamp\": 0,\n" +
            "  \"data\": [\n" +
            "    {\n" +
            "      \"id\": 1,\n" +
            "      \"author\": {\n" +
            "        \"id\": 1,\n" +
            "        \"email\": \"dsiegertsz0@fc2.com\",\n" +
            "        \"phone\": \"+7 645 943 5082\",\n" +
            "        \"photo\": \"data:image/png;base64...\",\n" +
            "        \"about\": \"Maecenas tristique...\",\n" +
            "        \"city\": {\n" +
            "          \"id\": 0,\n" +
            "          \"title\": \"string\",\n" +
            "          \"country_id\": 0\n" +
            "        },\n" +
            "        \"country\": {\n" +
            "          \"id\": 0,\n" +
            "          \"title\": \"string\",\n" +
            "          \"cities\": [\n" +
            "            {\n" +
            "              \"id\": 0,\n" +
            "              \"title\": \"string\",\n" +
            "              \"country_id\": 0\n" +
            "            }\n" +
            "          ]\n" +
            "        },\n" +
            "        \"first_name\": \"Davida\",\n" +
            "        \"last_name\": \"Siegertsz\",\n" +
            "        \"reg_date\": 1618070680000,\n" +
            "        \"birth_date\": 702565308000,\n" +
            "        \"message_permission\": \"ALL\",\n" +
            "        \"last_online_time\": 1644234125000,\n" +
            "        \"is_online\": true,\n" +
            "        \"is_blocked\": false\n" +
            "      },\n" +
            "      \"content\": \"string\",\n" +
            "      \"notification_type\": \"FRIEND_REQUEST\",\n" +
            "      \"sent_time\": -2\n" +
            "    }\n" +
            "  ]\n" +
            "}");
    }

    @PostMapping("/notifications")
    public ResponseEntity<Object> createNotification(@RequestBody NotificationInputDto notif){
        return ResponseEntity.ok("{\n" +
                "  \"userId\": 2,\n" +
                "  \"nameNotification\": \"FRIEND_REQUEST\",\n" +
                "  \"content\": \"что-то пришло\"\n" +
                "}");
    }

    @GetMapping("/notifications/count")
    public ResponseEntity<Object> getNotificationCount(){
        return ResponseEntity.ok("{\n" +
                "  \"timestamp\": 0,\n" +
                "  \"data\": {\n" +
                "    \"count\": 1\n" +
                "  }\n" +
                "}");
    }
}
