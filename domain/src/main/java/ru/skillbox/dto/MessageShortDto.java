package ru.skillbox.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageShortDto {

    private Long id;
    private Long time;
    private Long authorId;
    private String messageText;
}
