package ru.skillbox.model.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FeedsResponseError {
    private String error;
    @JsonProperty("error_description")
    private String errorDescription;
}
