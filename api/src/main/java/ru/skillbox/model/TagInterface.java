package ru.skillbox.model;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import ru.skillbox.request.PostTagRequest;
import ru.skillbox.response.post.PostTagResponse;

public interface TagInterface {
    ResponseEntity<PostTagResponse> getTags(
            Tag tag,
            @RequestParam(required = false, name = "offset", defaultValue = "0") Integer offset,
            @RequestParam(required = false, name = "itemPerPage", defaultValue = "20") Integer itemPerPage);

    ResponseEntity<PostTagResponse> putTag(String postId, PostTagRequest request);
}
