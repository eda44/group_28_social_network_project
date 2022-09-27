package ru.skillbox.dto.account;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRs {

    private String error;
    private long timestamp;
    private AccountDto data;
    private String errorDescription;
}
