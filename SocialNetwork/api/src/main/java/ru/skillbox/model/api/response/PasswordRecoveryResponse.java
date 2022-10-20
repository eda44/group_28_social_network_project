package ru.skillbox.model.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import ru.skillbox.dto.AbstractDto;

import java.util.Date;

@Getter
@Builder
public class PasswordRecoveryResponse {
    private String error;
    private Long timestamp;
    private AbstractDto data;
    @JsonProperty("error_description")
    private String errorDescription;

    public static PasswordRecoveryResponse getOkResponse() {
        return PasswordRecoveryResponse.builder()
                .timestamp(new Date().getTime())
                .data(new AbstractDto("ok"))
                .build();
    }

    public static PasswordRecoveryResponse getBadResponse() {
        return PasswordRecoveryResponse.builder()
                .error("Неверный запрос")
                .timestamp(new Date().getTime())
                .errorDescription("Неверный код авторизации")
                .build();
    }
}
