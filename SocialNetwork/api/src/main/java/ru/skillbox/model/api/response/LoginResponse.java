package ru.skillbox.model.api.response;

import lombok.Getter;
import lombok.Setter;
import ru.skillbox.dto.AccountDto;
import ru.skillbox.model.User;

import java.util.Date;

@Getter
@Setter
public class LoginResponse {
    private String error;
    private Long timestamp;
    private AccountDto data;
    private String error_description;

    public static LoginResponse getCorrectResponseFrom(User user){
        LoginResponse response = new LoginResponse();
        response.setError("Неверный запрос");
        response.setTimestamp(new Date().getTime());
        AccountDto accountDto = AccountDto.getCorrectResponseFrom(user.getId(), user.getEmail(), user.getFirstName(), user.getLastName(), user.getRegDate());
        response.setData(accountDto);
        response.setError_description("Неверные учетные данные");
        return response;
    }

}
