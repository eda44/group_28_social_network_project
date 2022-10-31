package ru.skillbox.model;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import ru.skillbox.response.post.PostCommentResponse;

public interface PostCommentInterface {

    ResponseEntity<PostCommentResponse> getCommentByIdPost(
            @PathVariable Integer id,
            @RequestParam(required = false, name = "offset", defaultValue = "0") Integer offset,
            @RequestParam(required = false, name = "perPage ", defaultValue = "20") Integer perPage);
}
