package ru.skillbox.dto;

import lombok.Getter;
import lombok.Setter;
import ru.skillbox.dto.enums.NameNotification;

@Getter
@Setter
public class NotificationsSettingsDto {

    private long time;
    private SettingRq[] array;
    private int user_id;


    @Getter
    @Setter
    static class SettingRq {

        private boolean enable;
        private NameNotification notificationType;
    }
}
