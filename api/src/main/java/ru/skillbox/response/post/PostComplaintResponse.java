package ru.skillbox.response.post;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import ru.skillbox.response.ComplaintResponse;

import java.util.List;

@Getter
@Setter
public class PostComplaintResponse {

    private Long timestamp;
    private List<ComplaintResponse> data;
    @JsonProperty("error_description")
    private String errorDescription;
}
