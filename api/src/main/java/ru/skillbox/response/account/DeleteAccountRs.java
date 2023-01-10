package ru.skillbox.response.account;

import lombok.Builder;
import lombok.Data;
import ru.skillbox.dto.AbstractDto;
//todo удалить если не используется

@Data
@Builder
public class DeleteAccountRs {

    private String error;

    private long timestamp;

    private AbstractDto data;

    private String errorDescription;

}
