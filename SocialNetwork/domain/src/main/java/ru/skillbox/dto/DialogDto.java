package ru.skillbox.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class DialogDto {

    private Long id;
    private AccountDto conversationPartner;
    private Long unreadCount;
    private MessageDto lastMessage;
}
