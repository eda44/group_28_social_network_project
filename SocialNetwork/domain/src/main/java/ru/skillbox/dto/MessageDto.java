package ru.skillbox.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageDto {

    private long id;
    private long time;
    private String status;
    private long authorId;
    private long recipientId;
    private String messageText;
}
