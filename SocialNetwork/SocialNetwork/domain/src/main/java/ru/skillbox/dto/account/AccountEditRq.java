package ru.skillbox.dto.account;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountEditRq {
    private String phone;
    private String about;
    private long city;
    private long country;
    private String firstName;
    private String lastName;
    private String birthDate;
    private String photoId;
    private String photoName;

}
