package ru.skillbox.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageDto {

    private Long id;
    private Long time;
    private String status;
    private Long authorId;
    private Long recipientId;
    private String messageText;
}
