package ru.skillbox.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ru.skillbox.dto.enums.MessagePermission;
import ru.skillbox.dto.enums.StatusCode;

@Builder
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountDto {
    private Long id;
    private String email;
    private String phone;
    private String photo;
    private String about;
    private String city;
    private String country;
    private String firstName;
    private String lastName;
    private Long regDate;
    private Long birthDate;
    private MessagePermission messagePermission;
    private Long lastOnlineTime;
    private Boolean isOnline;
    private Boolean isBlocked;
    private Boolean isDeleted;
    private StatusCode statusCode;
}
