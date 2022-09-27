package ru.skillbox.dto.account;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecoveryPasswordRs {

    private String error;
    private long timestamp;
    private AbstractDto data;
    private String errorDescription;
}
