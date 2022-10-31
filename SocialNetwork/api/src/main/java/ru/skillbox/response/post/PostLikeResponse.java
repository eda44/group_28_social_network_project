package ru.skillbox.response.post;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import ru.skillbox.response.LikeResponse;

import java.util.List;

@Getter
@Setter
public class PostLikeResponse {

    private Long timestamp;
    private List<LikeResponse> data;
    private String error;
    @JsonProperty("error_description")
    private String errorDescription;
}
