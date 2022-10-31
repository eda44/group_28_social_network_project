package ru.skillbox.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class PostCommentDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Long time;
//    private AccountByIdDto author;
    private String author;
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
