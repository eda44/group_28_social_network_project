package ru.skillbox.response.settings;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ru.skillbox.dto.enums.NameNotification;

@Getter
@Setter
@Builder
public class NotificationDto {

    private Long id;
    private String time;
    private Long authorId;
    private Long userId;
    private String content;
    private NameNotification notificationType;
    private boolean isStatusSent;
}
