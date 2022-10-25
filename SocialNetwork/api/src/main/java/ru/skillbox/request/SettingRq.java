package ru.skillbox.request;

import lombok.Getter;
import lombok.Setter;
import ru.skillbox.dto.enums.NameNotification;

@Getter
@Setter
public class SettingRq {
    private Boolean enable;
    private NameNotification notificationType;
}
