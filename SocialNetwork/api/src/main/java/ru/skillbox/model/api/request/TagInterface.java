package ru.skillbox.model.api.request;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import ru.skillbox.model.Tag;

public interface TagInterface {
    ResponseEntity<String> getTags(Tag tag,
                                   @RequestParam(name = "offset", defaultValue = "0") Integer offset,
                                   @RequestParam(name = "itemPerPage", defaultValue = "20") Integer itemPerPage);
}
