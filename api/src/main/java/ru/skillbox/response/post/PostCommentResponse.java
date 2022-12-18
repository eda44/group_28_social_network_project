package ru.skillbox.response.post;

import lombok.Getter;
import lombok.Setter;
import ru.skillbox.dto.CommentDto;
import ru.skillbox.dto.Pageable;
import ru.skillbox.dto.Sort;
import ru.skillbox.model.PostComment;

import java.util.List;

@Getter
@Setter
public class PostCommentResponse {

    private Integer totalPages;
    private Long totalElements;
    private Integer number;
    private Integer size;
    private List<CommentDto> content;
    private Sort sort;
    private Boolean first;
    private Boolean last;
    private Integer numberOfElements;
    private Pageable pageable;
    private Boolean empty;
}
