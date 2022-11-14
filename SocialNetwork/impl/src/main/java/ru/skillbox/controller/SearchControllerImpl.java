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
    public ResponseEntity<SearchResponse> search(String author, String firstName, String lastName, Integer ageFrom, Integer ageTo, Integer city, Integer country, int page, int size) {
        try {
            return ResponseEntity.ok(searchService.search(author, firstName, lastName, ageFrom, ageTo, city, country, page, size));
        } catch (UserNotAuthorized e) {
            return ResponseEntity.badRequest().body(SearchResponse.getBadResponse());
        }
    }
}
