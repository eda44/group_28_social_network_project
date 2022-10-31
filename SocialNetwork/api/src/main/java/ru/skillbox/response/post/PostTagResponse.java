package ru.skillbox.response.post;

import lombok.Getter;
import lombok.Setter;
import ru.skillbox.model.Post;
import ru.skillbox.model.Tag;

import java.util.List;

@Getter
@Setter
public class PostTagResponse {

    private Long timestamp;
    private Integer total;
    private Integer offset;
    private Integer perPage;
    private List<Tag> data;
    private List<Post> dataPosts;

}
