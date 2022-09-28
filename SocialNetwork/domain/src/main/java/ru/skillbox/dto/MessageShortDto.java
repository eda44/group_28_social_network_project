package ru.skillbox.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageShortDto {

    private long id;
    private long time;
    private long authorId;
    private String messageText;
}
