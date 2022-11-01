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

    public static LoginResponse getOkResponse(Person person){
        return LoginResponse.builder()
                .timestamp(new Date().getTime())
                .data(AccountDto.getCorrectRsLogin(person, "token"))//TODO: token
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
