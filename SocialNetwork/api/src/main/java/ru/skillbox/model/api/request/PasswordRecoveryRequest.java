package ru.skillbox.model.api.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PasswordRecoveryRequest {
    private String email;
}
