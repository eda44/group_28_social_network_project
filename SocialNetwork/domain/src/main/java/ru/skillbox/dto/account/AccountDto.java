package ru.skillbox.dto.account;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountDto {

    private long id;
    private String email;
    private String phone;
    private String photo;
    private String about;
    private CityDto city;
    private CountryDto country;
    private String token;
    private String firstName;
    private String lastName;
    private Long regDate;
    private long birthDate;
    private MessagePermission messagePermission;
    private long lastOnlineTime;
    private boolean isOnline;
    private boolean isBlocked;
    private boolean isDeleted;
}
