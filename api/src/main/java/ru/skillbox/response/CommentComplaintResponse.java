package ru.skillbox.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CommentComplaintResponse {

    private Long timestamp;
    private List<ComplaintResponse> data;
    @JsonProperty("error_description")
    private String errorDescription;
}
