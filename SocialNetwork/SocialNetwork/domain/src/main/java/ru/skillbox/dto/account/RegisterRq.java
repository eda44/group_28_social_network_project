package ru.skillbox.dto.account;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRq {

    private String email;
    private String passwd1;
    private String passwd2;
    private String firstName;
    private String lastName;
    private String code;
    private String token;
}
