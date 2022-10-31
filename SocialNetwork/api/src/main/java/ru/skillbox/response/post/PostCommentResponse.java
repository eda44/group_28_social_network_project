package ru.skillbox.response.post;

import lombok.Getter;
import lombok.Setter;
import ru.skillbox.model.PostComment;

import java.util.List;

@Getter
@Setter
public class PostCommentResponse {

    private Long timestamp;
    private Integer total;
    private Integer offset;
    private Integer perPage;
    private List<PostComment> data;
}
