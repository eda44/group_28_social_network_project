package ru.skillbox.model.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import ru.skillbox.dto.enums.LikeType;
@Getter
@Setter
public class PostLikeRequest {

    private LikeType type;
    @JsonProperty("author_id")
    private Integer authorId;
    @JsonProperty("item_id")
    private Integer itemId;
    @JsonProperty("post_id")
    private Integer postId;

}
