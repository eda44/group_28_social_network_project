package ru.skillbox.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;
@Builder
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoginResponse {
    private String error;
    private Long timestamp;
    @JsonProperty("error_description")
    private String errorDescription;
    private String accessToken;
    private String tokenType;

    public static LoginResponse getOkResponse(String token){
        return LoginResponse.builder()
                .accessToken(token)
                .tokenType("Bearer")
                .build();
    }

    public static LoginResponse getBadResponse(){
        return LoginResponse.builder()
                .error("Неверный запрос")
                .timestamp(new Date().getTime())
                .errorDescription("Неверные учетные данные")
                .build();
    }
}
