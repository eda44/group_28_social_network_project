package ru.skillbox.response.post;

import lombok.Getter;
import lombok.Setter;
import ru.skillbox.dto.PostDto;

import java.util.List;

@Getter
@Setter
public class WallResponse {

    private Long timestamp;
    private Integer page;
    private Integer size;
    private Integer total;
    private List<PostDto> data;
}
