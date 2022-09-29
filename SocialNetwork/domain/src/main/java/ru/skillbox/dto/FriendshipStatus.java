package ru.skillbox.dto;

import lombok.Getter;
import lombok.Setter;
import ru.skillbox.dto.enums.Code;

@Getter
@Setter
public class FriendshipStatus {

    private Long id;
    private Long time;
    private Code code;

}
