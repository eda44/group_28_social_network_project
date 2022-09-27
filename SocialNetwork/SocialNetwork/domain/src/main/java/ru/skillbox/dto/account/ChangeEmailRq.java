package ru.skillbox.dto.account;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangeEmailRq {

    private String email;
    private String temp;
    private String code;
}
