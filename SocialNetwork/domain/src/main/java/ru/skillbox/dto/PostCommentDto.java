package ru.skillbox.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostCommentDto {

    private int id;
    private long time;
    private AccountByIdDto author;
    private int parentId;
    private String commentText;
    private int postId;
    private boolean isBlocked;
    private int likes;
    private Boolean myLike;
    private PostCommentDto[] subComments;
}
