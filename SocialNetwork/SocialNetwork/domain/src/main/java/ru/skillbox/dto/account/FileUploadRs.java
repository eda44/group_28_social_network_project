package ru.skillbox.dto.account;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileUploadRs {

    private String error;
    private long timestamp;
    private FileUploadDto data;
    private String errorDescription;
}
