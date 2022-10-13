package ru.skillbox.model.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import ru.skillbox.dto.PostDto;

import java.util.List;

@Setter
@Getter
public class FeedsResponseOK {
    @JsonProperty("timestamp")
    private long timeStamp;
    private int page;
    private int size;
    private int total;
    private List<PostDto> data;
}
