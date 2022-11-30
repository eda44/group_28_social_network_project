package ru.skillbox.model;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.skillbox.response.SearchResponse;

@RequestMapping("/api/v1/account")
public interface SearchController {
    @GetMapping("/search")
    ResponseEntity<?> search(@RequestParam(required = false) String author,
                                          @RequestParam(name = "first_name", required = false) String firstName,
                                          @RequestParam(name = "last_name", required = false) String lastName,
                                          @RequestParam(name = "age_from", required = false) Integer ageFrom,
                                          @RequestParam(name = "age_to", required = false) Integer ageTo,
                                          @RequestParam(required = false) Integer city,
                                          @RequestParam(required = false) Integer country,
                                          @RequestParam(required = false) Integer page,
                                          @RequestParam(required = false) Integer size);

}

