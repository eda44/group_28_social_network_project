package ru.skillbox.model.api.request;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

public interface PostCommentInterface {

    ResponseEntity<String> getCommentByIdPost(@PathVariable Integer id,
                                              @RequestParam(name = "offset", defaultValue = "0") Integer offset,
                                              @RequestParam(name = "perPage ", defaultValue = "20") Integer perPage);
}
