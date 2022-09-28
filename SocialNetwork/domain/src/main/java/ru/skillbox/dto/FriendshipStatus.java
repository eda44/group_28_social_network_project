package ru.skillbox.dto;

import lombok.Getter;
import lombok.Setter;
import ru.skillbox.dto.enums.Code;

@Getter
@Setter
public class FriendshipStatus {

    private long id;
    private long time;
    private Code code;

}
