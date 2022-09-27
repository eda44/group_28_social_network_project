package ru.skillbox.dto.account;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangePasswordRq {

    private String temp;
    private String password;
    private String code;
}
