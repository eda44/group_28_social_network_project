package ru.skillbox.model.api.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationRequest {
    private String email;
    private String passwd1;
    private String passwd2;
    private String firstName;
    private String lastName;
    private String code;
    private String token;
}
