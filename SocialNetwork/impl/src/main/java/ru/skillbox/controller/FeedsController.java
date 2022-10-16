package ru.skillbox.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.skillbox.dto.*;
import ru.skillbox.dto.enums.MessagePermission;
import ru.skillbox.dto.enums.Type;
import ru.skillbox.model.api.request.FeedsInterface;
import ru.skillbox.model.api.response.FeedsResponseOK;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class FeedsController implements FeedsInterface {
    @Override
    @GetMapping("/api/v1/feeds")
    public ResponseEntity<Object> getFeedsSearch(int page, int size, String[] sort, int offset, int limit) throws SQLException {

        FeedsResponseOK feedsResponse = new FeedsResponseOK();
        feedsResponse.setTimeStamp(16442341250L);
        feedsResponse.setPage(1);
        feedsResponse.setSize(20);
        feedsResponse.setTotal(1);
        List<PostDto> postDtoList = new ArrayList<>();
        PostDto postDto = new PostDto();
        postDtoInit(postDto);
        postDtoList.add(postDto);

        feedsResponse.setData(postDtoList);
        return ResponseEntity.ok(feedsResponse);
    }

    private static void postDtoInit(PostDto postDto) {
        postDto.setId(0);
        postDto.setTime(0L);

        postDto.setTitle("Очевидное - невероятное");
        postDto.setType(Type.POSTED);
        postDto.setIsBlocked(true);
        postDto.setMyLike(true);
        postDto.setPhotoUrl("Фото");
        PostTagDto postTagDto = new PostTagDto();
        postTagDto.setId(0);
        postTagDto.setTag("Интересное");
        List<PostTagDto> listPostTagDto = new ArrayList<>();
        listPostTagDto.add(postTagDto);
        postDto.setTags(listPostTagDto.toArray(new PostTagDto[0]));
        AccountByIdDto accountByIdDto = accountByIdDtoInit();
        postDto.setAuthor(accountByIdDto);
        postDto.setPostText("Пингвины научились летать");
        postDto.setLikes(0);
        PostCommentDto postCommentDto = new PostCommentDto();
        postCommentDtoInit(postCommentDto);
        postDto.setComments(new PostCommentDto[]{postCommentDto});
    }

    private static void postCommentDtoInit(PostCommentDto postCommentDto) {
        AccountByIdDto accountByIdDto = accountByIdDtoInit();
        postCommentDto.setAuthor(accountByIdDto);
        postCommentDto.setCommentText("Сколько граммов принял на грудь, когда писал это?");
        postCommentDto.setId(0);
        postCommentDto.setIsBlocked(true);
        postCommentDto.setLikes(0);
        postCommentDto.setMyLike(true);
        postCommentDto.setParentId(0);
        postCommentDto.setPostId(0);
        postCommentDto.setSubComments(new String[]{"Я только чуть-чуть-)"});
        postCommentDto.setTime(0L);
    }

    private static AccountByIdDto accountByIdDtoInit() {
        AccountByIdDto accountByIdDto = new AccountByIdDto();
        accountByIdDto.setId(1L);
        CityDto cityDto = new CityDto();
        cityDto.setId(0L);
        cityDto.setTitle("Москва");
        cityDto.setCountryId(0);
        CountryDto countryDto = new CountryDto();
        countryDto.setId(0L);
        countryDto.setTitle("Россия");
        countryDto.setCities(new CityDto[]{cityDto});

        accountByIdDto.setCountry(countryDto);
        accountByIdDto.setCity(cityDto);
        accountByIdDto.setFirstName("Davida");
        accountByIdDto.setEmail("dsiegertsz0@fc2.com");
        accountByIdDto.setLastName("Siegertsz");
        accountByIdDto.setAbout("Maecenas tristique...");
        accountByIdDto.setBirthDate(702565308000L);
        accountByIdDto.setIsBlocked(false);
        accountByIdDto.setIsOnline(true);
        accountByIdDto.setLastOnlineTime(1644234125000L);
        accountByIdDto.setMessagePermission(MessagePermission.ALL);
        accountByIdDto.setRegDate(1618070680000L);
        accountByIdDto.setPhone("+7 645 943 5082");
        accountByIdDto.setPhoto("data:image/png;base64...");
        return accountByIdDto;
    }


    @GetMapping("/api/v1/notifications")
    public ResponseEntity<Object> getNotifications() {
        return ResponseEntity.ok("{\n" +
                "  \"timestamp\": 0,\n" +
                "  \"data\": [\n" +
                "    {\n" +
                "      \"id\": 0,\n" +
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
                "      \"sent_time\": 0\n" +
                "    }\n" +
                "  ]\n" +
                "}");
    }

    @GetMapping("/api/v1/users/me")
    public ResponseEntity<Object> getUsersMe() {
        return ResponseEntity.ok("{\n" +
                "  \"error\": \"Неверный запрос\",\n" +
                "  \"timestamp\": 1644234125000,\n" +
                "  \"data\": {\n" +
                "    \"id\": 1,\n" +
                "    \"email\": \"dsiegertsz0@fc2.com\",\n" +
                "    \"phone\": \"+7 645 943 5082\",\n" +
                "    \"photo\": \"http://res.cloudinary.com/...\",\n" +
                "    \"about\": \"Maecenas tristique...\",\n" +
                "    \"city\": {\n" +
                "      \"id\": 0,\n" +
                "      \"title\": \"string\",\n" +
                "      \"country_id\": 0\n" +
                "    },\n" +
                "    \"country\": {\n" +
                "      \"id\": 0,\n" +
                "      \"title\": \"string\",\n" +
                "      \"cities\": [\n" +
                "        {\n" +
                "          \"id\": 0,\n" +
                "          \"title\": \"string\",\n" +
                "          \"country_id\": 0\n" +
                "        }\n" +
                "      ]\n" +
                "    },\n" +
                "    \"first_name\": \"Davida\",\n" +
                "    \"last_name\": \"Siegertsz\",\n" +
                "    \"reg_date\": 1618070680000,\n" +
                "    \"birth_date\": 702565308000,\n" +
                "    \"photo_name\": \"Photo\",\n" +
                "    \"message_permission\": \"ALL\",\n" +
                "    \"last_online_time\": 1644234125000,\n" +
                "    \"is_online\": true,\n" +
                "    \"is_blocked\": false,\n" +
                "    \"is_deleted\": false\n" +
                "  },\n" +
                "  \"error_description\": \"Неверный код авторизации\"\n" +
                "}");
    }

    @GetMapping("/api/v1/friends/recommendations")
    public ResponseEntity<Object> getRecommendations() {
        return ResponseEntity.ok("{\n" +
                "  \"error\": \"Неверный запрос\",\n" +
                "  \"timestamp\": 1644234125000,\n" +
                "  \"data\": [\n" +
                "    {\n" +
                "      \"id\": 1,\n" +
                "      \"email\": \"dsiegertsz0@fc2.com\",\n" +
                "      \"phone\": \"+7 645 943 5082\",\n" +
                "      \"photo\": \"data:image/png;base64...\",\n" +
                "      \"about\": \"Maecenas tristique...\",\n" +
                "      \"city\": {\n" +
                "        \"id\": 0,\n" +
                "        \"title\": \"string\",\n" +
                "        \"country_id\": 0\n" +
                "      },\n" +
                "      \"country\": {\n" +
                "        \"id\": 0,\n" +
                "        \"title\": \"string\",\n" +
                "        \"cities\": [\n" +
                "          {\n" +
                "            \"id\": 0,\n" +
                "            \"title\": \"string\",\n" +
                "            \"country_id\": 0\n" +
                "          }\n" +
                "        ]\n" +
                "      },\n" +
                "      \"first_name\": \"Davida\",\n" +
                "      \"last_name\": \"Siegertsz\",\n" +
                "      \"reg_date\": 1618070680000,\n" +
                "      \"birth_date\": 702565308000,\n" +
                "      \"message_permission\": \"ALL\",\n" +
                "      \"last_online_time\": 1644234125000,\n" +
                "      \"is_online\": true,\n" +
                "      \"is_blocked\": false\n" +
                "    }\n" +
                "  ],\n" +
                "  \"error_description\": \"Неверные учетные данные\"\n" +
                "}");
    }


}
