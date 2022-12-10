package ru.skillbox.response.settings;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import ru.skillbox.dto.AccountDto;
import ru.skillbox.dto.enums.NameNotification;

@Getter
@Setter
public class NotificationDataRs {

    private Long id;
    @JsonProperty(value = "author")
    private AccountDto author;
    private String content;
    @JsonProperty(value = "notificationType")
    private NameNotification notificationType;
    private String sentTime;
}
