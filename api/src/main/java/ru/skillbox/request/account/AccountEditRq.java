package ru.skillbox.request.account;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Builder
@Getter
@Setter
public class AccountEditRq {

    private String phone;

    private String about;

    private String city;

    private String country;

    private String firstName;

    private String lastName;

    private Date birthDate;

    private String photoId;

    private String photoName;

}
