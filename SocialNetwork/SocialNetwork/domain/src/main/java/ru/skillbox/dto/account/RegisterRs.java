package ru.skillbox.dto.account;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRs {

    private String error;
    private long timestamp;
    private LogoutDto data;
    private String[] errorDescription;
}
