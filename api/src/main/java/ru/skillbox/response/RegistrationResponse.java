package ru.skillbox.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import ru.skillbox.dto.LogoutDto;

import java.util.Date;

@Getter
@Builder
public class RegistrationResponse {
    private String error;
    private Long timestamp;
    private LogoutDto data;
    @JsonProperty("error_description")
    private String errorDescription;

    public static RegistrationResponse getOkResponse() {
        return RegistrationResponse.builder()
                .timestamp(new Date().getTime())
                .data(new LogoutDto("ok"))
                .build();
    }

    public static RegistrationResponse getBadResponse() {
        return RegistrationResponse.builder()
                .error("Неверный запрос")
                .timestamp(new Date().getTime())
                .errorDescription("Этот аккуант уже зарегистрирован")
                .build();
    }


    public static RegistrationResponse getCaptchaResponse() {
        return RegistrationResponse.builder()
                .error("Неверный запрос")
                .timestamp(new Date().getTime())
                .errorDescription("Неверный код")
                .build();
    }
}
