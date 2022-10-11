package ru.skillbox.dto;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class AbstractDto {

    private String message;

    public AbstractDto(String message) {
        this.message = message;
    }
}
