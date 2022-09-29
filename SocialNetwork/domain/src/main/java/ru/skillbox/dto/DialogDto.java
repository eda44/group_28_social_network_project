package ru.skillbox.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DialogDto {

    private Long id;
    private AccountByIdDto conversationPartner;
    private Long unreadCount;
    private MessageDto last_message;
}
