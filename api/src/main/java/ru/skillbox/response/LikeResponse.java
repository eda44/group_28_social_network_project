package ru.skillbox.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class LikeResponse {

    private Boolean liked;
    private Integer likes;
    private List<Long> users;
}
