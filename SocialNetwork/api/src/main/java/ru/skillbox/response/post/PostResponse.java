package ru.skillbox.response.post;

import lombok.Getter;
import lombok.Setter;
import ru.skillbox.model.Post;

import java.util.List;

@Getter
@Setter
public class PostResponse {

    private Long timestamp;
    private Integer page;
    private Integer size;
    private Integer total;
    private List<Post> data;
}
