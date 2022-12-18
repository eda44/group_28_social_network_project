package ru.skillbox.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import ru.skillbox.dto.enums.MessagePermission;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountByIdDto {

    private Long id;
    private String email;
    private String phone;
    private String photo;
    private String about;
    private CityDto city;
    private CountryDto country;
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
    @JsonProperty("reg_date")
    private Long regDate;
    @JsonProperty("birth_date")
    private Long birthDate;
    @JsonProperty("message_permission")
    private MessagePermission messagePermission;
    @JsonProperty("last_online_time")
    private Long lastOnlineTime;
    @JsonProperty("is_online")
    private Boolean isOnline;
    @JsonProperty("is_blocked")
    private Boolean isBlocked;
}
