package ru.skillbox.dto;

import lombok.Getter;
import lombok.Setter;
import ru.skillbox.dto.enums.NameNotification;

@Getter
@Setter
public class NotificationSentDto {

    private long timestamp;
    private NotificationDataRs[] data;


    @Getter
    @Setter
    static class NotificationDataRs {

        private int id;
        private AccountByIdDto author;
        private String content;
        private NameNotification notificationType;
        private long sentTime;
    }
}
