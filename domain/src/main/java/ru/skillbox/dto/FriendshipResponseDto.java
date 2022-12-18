package ru.skillbox.dto;

import lombok.Data;
import ru.skillbox.dto.enums.FriendshipCodeDto;
import ru.skillbox.model.Person;


@Data
public class FriendshipResponseDto extends Person {
    private FriendshipCodeDto friendshipStatus;
}
