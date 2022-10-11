package ru.skillbox.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import ru.skillbox.dto.enums.MessagePermission;

@Getter
@Setter
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

    public static AccountDto getCorrectResponseFrom(Long id, String email, String firstName, String lastName, Long regDate) {
        AccountDto accountDto = new AccountDto();

        CityDto cityDto = new CityDto();

        CountryDto countryDto = new CountryDto();

        accountDto.setId(id);
        accountDto.setEmail(email);
        accountDto.setFirstName(firstName);
        accountDto.setLastName(lastName);
        accountDto.setRegDate(regDate);
        accountDto.setCity(cityDto);
        accountDto.setCountry(countryDto);
        accountDto.setToken("asd");
        return accountDto;
    }
}
