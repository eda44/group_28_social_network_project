package ru.skillbox.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentAddRequest {
    @JsonProperty("parent_id")
    private Long parentId;
    @JsonProperty("comment_text")
    private String commentText;
}
