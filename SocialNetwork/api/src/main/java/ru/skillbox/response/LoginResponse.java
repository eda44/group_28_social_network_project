package ru.skillbox.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import ru.skillbox.dto.AccountDto;
import ru.skillbox.model.Person;

import java.util.Date;
@Builder
@Getter
public class LoginResponse {
    private String error;
    private Long timestamp;
    private AccountDto data;
    @JsonProperty("error_description")
    private String errorDescription;
    private String accessToken;
    private String tokenType;

    public static LoginResponse getOkResponse(Person person, String token){
        return LoginResponse.builder()
                .timestamp(new Date().getTime())
                .data(AccountDto.getCorrectRsLogin(person, token))
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
