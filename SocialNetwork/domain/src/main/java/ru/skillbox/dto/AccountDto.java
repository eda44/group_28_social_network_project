package ru.skillbox.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import ru.skillbox.dto.enums.MessagePermission;
import ru.skillbox.model.User;

@Builder
@Getter
public class AccountDto {
    private Long id;
    private String email;
    private String phone;
    private String photo;
    private String about;
    private CityDto city;
    private CountryDto country;
    private String token;
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
    @JsonProperty("is_deleted")
    private Boolean isDeleted;

    public static AccountDto getCorrectResponseFrom(User user) {
        return AccountDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .regDate(user.getRegDate())
                .city(new CityDto())
                .country(new CountryDto())
                .token("token")
                .build();
    }
}
