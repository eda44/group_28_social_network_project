package ru.skillbox.dto.account;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountEditRs {

    private String error;
    private long timestamp;
    private AccountWithPhotoNameDto data;
    private String errorDescription;

}
