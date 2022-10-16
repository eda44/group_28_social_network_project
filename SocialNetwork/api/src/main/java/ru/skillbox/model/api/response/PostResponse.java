package ru.skillbox.model.api.response;

import lombok.Getter;
import lombok.Setter;
import ru.skillbox.dto.PostDto;

import java.util.List;

@Getter
@Setter
public class PostResponse {

    private Long timestamp;
    private Integer page;
    private Integer size;
    private Integer total;
    private List<PostDto> data;
}
