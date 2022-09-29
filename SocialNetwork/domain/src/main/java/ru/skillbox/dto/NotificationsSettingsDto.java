package ru.skillbox.dto;

import lombok.Getter;
import lombok.Setter;
import ru.skillbox.dto.enums.NameNotification;

@Getter
@Setter
public class NotificationsSettingsDto {

    private Long time;
    private SettingRq[] array;
    private Integer user_id;


    @Getter
    @Setter
    static class SettingRq {

        private Boolean enable;
        private NameNotification notificationType;
    }
}
