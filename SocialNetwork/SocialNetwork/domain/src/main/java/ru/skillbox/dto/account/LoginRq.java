package ru.skillbox.dto.account;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRq {

    private String email;
    private String password;
}
