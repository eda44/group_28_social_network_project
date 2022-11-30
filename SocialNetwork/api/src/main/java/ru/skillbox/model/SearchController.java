package ru.skillbox.model;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.skillbox.response.SearchResponse;

@RequestMapping("/api/v1/account")
public interface SearchController {
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Успешный вход (возвращаются поля timestamp, data, accessToken и tokenType)",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = SearchResponse.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Неверный запрос (возвращаются поля error и error_description)",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = SearchResponse.class))
                    }
            )
    })
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

