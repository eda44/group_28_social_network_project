package ru.skillbox.request.account;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AccountEditRq {

  private String phone;

  private String about;

  private int city;

  private int country;

  private int firstName;

  private String lastName;

  private String birthDay;

  private String photoId;

  private String photoName;

}
