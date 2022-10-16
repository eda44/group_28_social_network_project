package ru.skillbox.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import ru.skillbox.dto.enums.Type;
import ru.skillbox.model.Like;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

@Getter
@Setter
public class PostDto {

    private Integer id;
    private Long time;
    private AccountByIdDto author;
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
