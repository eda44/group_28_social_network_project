package ru.skillbox.dto;

import lombok.Getter;
import lombok.Setter;
import ru.skillbox.dto.enums.MessagePermission;

@Getter
@Setter
public class AccountByIdDto {

    private long id;
    private String email;
    private String phone;
    private String photo;
    private String about;
    private CityDto city;
    private CountryDto country;
    private String firstName;
    private String lastName;
    private Long regDate;
    private long birthDate;
    private MessagePermission messagePermission;
    private long lastOnlineTime;
    private boolean isOnline;
    private boolean isBlocked;
}
