package ru.skillbox.dto;

import lombok.Getter;
import lombok.Setter;
import ru.skillbox.dto.enums.NameNotification;

@Getter
@Setter
public class NotificationInputDto {

    private Long userId;
    private NameNotification nameNotification;
    private String content;

}
