package ru.skillbox.request.account;

import lombok.Data;

@Data
public class AccountRecoveryRequest {

  private String email;

  private String password;

}
