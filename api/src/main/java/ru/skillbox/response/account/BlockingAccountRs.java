package ru.skillbox.response.account;

import lombok.Builder;
import lombok.Data;
import ru.skillbox.dto.AccountWithPhotoNameDto;
//todo удалить если не используется

@Data
@Builder
public class BlockingAccountRs {

    private String error;

    private long timestamp;

    private AccountWithPhotoNameDto data;

    private String errorDescription;

}
