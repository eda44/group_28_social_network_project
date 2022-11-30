package ru.skillbox.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.skillbox.exception.UserNotAuthorized;
import ru.skillbox.model.SearchController;
import ru.skillbox.response.SearchResponse;
import ru.skillbox.service.SearchService;

@RestController
@RequiredArgsConstructor
public class SearchControllerImpl implements SearchController {

    private final SearchService searchService;

    @Override
    public ResponseEntity<?> search(String author, String firstName, String lastName, Integer ageFrom, Integer ageTo, Integer city, Integer country, Integer page, Integer size) {
        /*try {
            return ResponseEntity.ok(searchService.search(author, firstName, lastName, ageFrom, ageTo, city, country, page, size));
        } catch (UserNotAuthorized e) {
            return ResponseEntity.badRequest().body(SearchResponse.getBadResponse());
        }*/
        return ResponseEntity.ok("{\n" +
                "  \"error\": \"Неверный запрос\",\n" +
                "  \"timestamp\": 1644234125000,\n" +
                "  \"total\": 50,\n" +
                "  \"page\": 1,\n" +
                "  \"size\": 10,\n" +
                "  \"content\": [\n" +
                "    {\n" +
                "      \"id\": 1,\n" +
                "      \"email\": \"strifdng\",\n" +
                "      \"phone\": \"stridfgng\",\n" +
                "      \"photo\": \"stridfgng\",\n" +
                "      \"about\": \"strdfging\",\n" +
                "      \"city\": \"strdfging\",\n" +
                "      \"country\": \"strdfging\",\n" +
                "      \"token\": \"string\",\n" +
                "      \"statusCode\": \"NONE\",\n" +
                "      \"firstName\": \"stfdring\",\n" +
                "      \"lastName\": \"sgfdgtring\",\n" +
                "      \"regDate\": \"2022-11-29T10:08:10.706Z\",\n" +
                "      \"birthDate\": \"2022-11-29T10:08:10.706Z\",\n" +
                "      \"messagePermission\": \"string\",\n" +
                "      \"lastOnlineTime\": \"2022-11-29T10:08:10.706Z\",\n" +
                "      \"isOnline\": true,\n" +
                "      \"isBlocked\": false,\n" +
                "      \"isDeleted\": false,\n" +
                "      \"photoId\": null,\n" +
                "      \"photoName\": null,\n" +
                "      \"role\": \"USER\",\n" +
                "      \"createdOn\": \"2022-11-29T10:08:10.706Z\",\n" +
                "      \"updatedOn\": \"2022-11-29T10:08:10.706Z\",\n" +
                "      \"password\": \"string\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 2,\n" +
                "      \"email\": \"string\",\n" +
                "      \"phone\": \"string\",\n" +
                "      \"photo\": \"string\",\n" +
                "      \"about\": \"string\",\n" +
                "      \"city\": \"string\",\n" +
                "      \"country\": \"string\",\n" +
                "      \"token\": \"string\",\n" +
                "      \"statusCode\": \"FRIEND\",\n" +
                "      \"firstName\": \"string\",\n" +
                "      \"lastName\": \"string\",\n" +
                "      \"regDate\": \"2022-11-29T10:08:10.706Z\",\n" +
                "      \"birthDate\": \"2022-11-29T10:08:10.706Z\",\n" +
                "      \"messagePermission\": \"string\",\n" +
                "      \"lastOnlineTime\": \"2022-11-29T10:08:10.706Z\",\n" +
                "      \"isOnline\": true,\n" +
                "      \"isBlocked\": true,\n" +
                "      \"isDeleted\": true,\n" +
                "      \"photoId\": \"string\",\n" +
                "      \"photoName\": \"string\",\n" +
                "      \"role\": \"USER\",\n" +
                "      \"createdOn\": \"2022-11-29T10:08:10.706Z\",\n" +
                "      \"updatedOn\": \"2022-11-29T10:08:10.706Z\",\n" +
                "      \"password\": \"string\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"error_description\": \"Неверный код авторизации\"\n" +
                "}");
    }
}
