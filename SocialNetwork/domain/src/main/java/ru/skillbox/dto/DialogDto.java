package ru.skillbox.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DialogDto {

    private long id;
    private AccountByIdDto conversationPartner;
    private long unreadCount;
    private MessageDto last_message;
}
