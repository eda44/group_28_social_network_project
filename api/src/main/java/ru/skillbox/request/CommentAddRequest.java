package ru.skillbox.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import ru.skillbox.dto.enums.LikeType;

@Getter
@Setter
public class CommentAddRequest {

    private Long id;
    private LikeType commentType;
    private Long time;
    private Long timeChanged;
    private Long authorId;
    private Long parentId;
    private String commentText;
    private Long postId;
    private Boolean isBlocked;
    private Boolean isDelete;
    private Integer likeAmount;
    private Boolean myLike;
    private Integer commentsCount;
    private String imagePath;
}
