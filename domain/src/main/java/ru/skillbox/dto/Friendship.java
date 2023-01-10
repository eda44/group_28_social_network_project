package ru.skillbox.dto;

import lombok.Getter;
import lombok.Setter;
//todo удалить если не используется

@Getter
@Setter
public class Friendship {

    private Long id;
    private Long srcPersonId;
    private Long destPersonId;
    private Long statusId;
    private String previousStatus;
}
