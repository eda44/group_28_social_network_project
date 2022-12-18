package ru.skillbox.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import ru.skillbox.dto.enums.Type;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
public class PostDto {
    private Long id;
    private String time;
    private String timeChanged;
    private Long authorId;
    private String title;
    private Type type;
    private String postText;
    private Boolean isDelete;
    private Boolean isBlocked;
    private Integer commentsCount;
    private String[] tags;
    private Integer likeAmount;
    private Boolean myLike;
    private String imagePath;
    private String publishDate;
}
