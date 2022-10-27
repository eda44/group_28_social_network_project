package ru.skillbox.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DialogDto {

    private Long id;
    private AccountByIdDto conversationPartner;

    @JsonProperty("unread_count")
    private Long unreadCount;

    @JsonProperty("last_message")
    private MessageDto lastMessage;
}
