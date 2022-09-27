package ru.skillbox.dto.account;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecoveryAccountRq {

    private String email;
    private String password;
}
