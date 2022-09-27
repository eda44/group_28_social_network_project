package ru.skillbox.dto.account;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchAccountRs {

    private String error;
    private long timestamp;
    private int total;
    private int page;
    private int size;
    private AccountAndStatusByIdDto[] data;
    private String errorDescription;
}
