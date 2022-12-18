package ru.skillbox.response.post;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostAddResponseError {
    private String error;
    @JsonProperty("error_description")
    private String errorDescription;
}
