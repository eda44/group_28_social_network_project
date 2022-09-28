package ru.skillbox.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Friendship {

    private long id;
    private long srcPersonId;
    private long destPersonId;
    private long statusId;
    private FriendshipStatus status;
    private String previousStatus;
}
