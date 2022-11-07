package ru.skillbox.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PostCommentDto {

    private Integer id;
    private Long time;
    private AccountDto author;
    @JsonProperty("parent_id")
    private Integer parentId;
    @JsonProperty("comment_text")
    private String commentText;
    @JsonProperty("post_id")
    private Integer postId;
    @JsonProperty("is_blocked")
    private Boolean isBlocked;
    private Integer likes;
    @JsonProperty("my_like")
    private Boolean myLike;
    @JsonProperty("sub_comments")
    private String[] subComments;
}
