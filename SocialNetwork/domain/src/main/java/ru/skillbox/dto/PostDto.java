package ru.skillbox.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import ru.skillbox.dto.enums.Type;

@Getter
@Setter
public class PostDto {

    private Long id;
    private Long time;
    private AccountDto author;
    private String title;
    private Type type;
    @JsonProperty("post_text")
    private String postText;
    @JsonProperty("is_blocked")
    private Boolean isBlocked;
    private PostCommentDto[] comments;
    private PostTagDto[] tags;
    private Integer likes;
    @JsonProperty("my_like")
    private Boolean myLike;
    @JsonProperty("photo_url")
    private String photoUrl;
}
