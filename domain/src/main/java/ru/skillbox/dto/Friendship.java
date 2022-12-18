package ru.skillbox.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Friendship {

    private Long id;
    private Long srcPersonId;
    private Long destPersonId;
    private Long statusId;
    private FriendshipStatus status;
    private String previousStatus;
}
