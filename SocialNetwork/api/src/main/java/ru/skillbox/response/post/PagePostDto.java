package ru.skillbox.response.post;

import lombok.Getter;
import lombok.Setter;
import ru.skillbox.dto.Pageable;
import ru.skillbox.dto.Sort;

import java.util.List;

@Getter
@Setter
public class PagePostDto {

    private Integer totalPages;
    private Long totalElements;
    private Integer number;
    private Integer size;
    private List<PostResponse> content;
    private Sort sort;
    private Boolean first;
    private Boolean last;
    private Integer numberOfElements;
    private Pageable pageable;
    private Boolean empty;
}
