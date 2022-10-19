package ru.skillbox.mapper;

import ru.skillbox.dto.AccountByIdDto;
import ru.skillbox.model.Person;

public class PersonToAccountById {

  public static AccountByIdDto personTo(Person person){
    return AccountByIdDto.builder()
        .id(person.getId())
        .email(person.getEmail())
        .phone(person.getPhone())
        .photo(person.getPhoto())
        .about(person.getAbout())
        .firstName(person.getFirstName())
        .lastName(person.getLastName())
        .regDate(person.getRegDate().getTime())
        .birthDate(person.getBirthDate().getTime())
        .messagePermission(person.getMessagePermission())
        .lastOnlineTime(person.getLastOnlineTime().getTime())
        .isOnline(true)//как получить этот флаг
        .isBlocked(person.isBlocking())
        .build();
  }

}
