package ru.skillbox.model;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.skillbox.dto.Pageable;
import ru.skillbox.response.post.PostResponse;

import java.util.List;

public interface PostInterface {

    ResponseEntity<PostResponse> getPostsAll(
            @RequestParam(name = "text", defaultValue = "string") String text,
            @RequestParam(required = false, name = "date_from") Long dateFrom,
            @RequestParam(required = false, name = "date_to") Long dateTo,
            @RequestParam(required = false, name = "author") User user,
            @RequestParam(required = false, name = "tags") List<Tag> tags,
            @RequestParam(name = "pageable") Pageable pageable);
}
