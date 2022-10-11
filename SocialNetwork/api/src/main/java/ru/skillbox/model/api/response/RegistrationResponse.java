package ru.skillbox.model.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import ru.skillbox.dto.AbstractDto;


import java.util.Date;

@Getter
@Setter
public class RegistrationResponse {
    private String error = "Неверный запрос";
    private Long timestamp = new Date().getTime();
    private AbstractDto data = new AbstractDto("ok");
    @JsonProperty("error_description")
    private String errorDescription = "Этот аккуант уже зарегистрирован";
}
