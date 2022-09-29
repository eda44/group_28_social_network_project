package ru.skillbox.dto;

import lombok.Getter;
import lombok.Setter;
import ru.skillbox.dto.enums.NameNotification;

@Getter
@Setter
public class NotificationSentDto {

    private Long timestamp;
    private NotificationDataRs[] data;


    @Getter
    @Setter
    static class NotificationDataRs {

        private Integer id;
        private AccountByIdDto author;
        private String content;
        private NameNotification notificationType;
        private Long sentTime;
    }
}
