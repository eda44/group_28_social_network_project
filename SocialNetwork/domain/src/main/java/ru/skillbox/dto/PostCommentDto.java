package ru.skillbox.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostCommentDto {

    private Integer id;
    private Long time;
    private AccountByIdDto author;
    private Integer parentId;
    private String commentText;
    private Integer postId;
    private Boolean isBlocked;
    private Integer likes;
    private Boolean myLike;
    private PostCommentDto[] subComments;
}
