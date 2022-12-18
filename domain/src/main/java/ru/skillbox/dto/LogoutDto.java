package ru.skillbox.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LogoutDto {

    private String message;

    public LogoutDto(String message) {
        this.message = message;
    }
}
