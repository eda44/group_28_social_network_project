package ru.skillbox.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import ru.skillbox.model.Tag;

import java.util.List;

@Getter
@Setter
public class PostAddRequest {
    private String title;
    private List<String> tags;
    @JsonProperty("post_text")
    private String postText;
    @JsonProperty("photo_url")
    private String photoUrl;
}
