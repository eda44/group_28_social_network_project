package ru.skillbox.dto;

import lombok.Getter;
import lombok.Setter;
import ru.skillbox.dto.enums.Type;

@Getter
@Setter
public class PostDto {

    private int id;
    private long time;
    private AccountByIdDto author;
    private String title;
    private Type type;
    private String postText;
    private Boolean isBlocked;
    private PostCommentDto[] comments;
    private PostTagDto[] tags;
    private int likes;
    private Boolean myLike;
    private String photoUrl;
}
