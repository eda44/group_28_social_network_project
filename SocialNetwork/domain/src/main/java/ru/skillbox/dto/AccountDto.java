package ru.skillbox.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;
import ru.skillbox.dto.enums.MessagePermission;
import ru.skillbox.dto.enums.StatusCode;
import ru.skillbox.model.Person;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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


    private String token;

    @JsonProperty("is_deleted")
    private Boolean isDeleted;

    @JsonProperty("status_code")
    private StatusCode statusCode;

    public static AccountDto getCorrectRsLogin(Person person, String token) {
        AccountDto dto = getBase(person);
        dto.setToken(token);
        return dto;
    }

    public static AccountDto getBase(Person person) {
        return AccountDto.builder()
                .id(person.getId())
                .email(person.getEmail())
                .phone(person.getPhone())
                .photo(person.getPhoto())
                .about(person.getAbout())
                .city(new CityDto())//TODO:заглушка
                .country(new CountryDto())//TODO:заглушка
                .firstName(person.getFirstName())
                .lastName(person.getLastName())
                .regDate(person.getRegDate())
                .birthDate(person.getBirthDate())
                .messagePermission(person.getMessagePermission())
                .lastOnlineTime(person.getLastOnlineTime())
                .isOnline(checkIsOnline(person.getLastOnlineTime()))
                .isBlocked(person.getIsBlocked())
                .build();
    }

    public static AccountDto getCorrectRsSearch(Person person, StatusCode statusCode) {
        AccountDto dto = getBase(person);
        dto.setStatusCode(statusCode);
        return dto;
    }
    public static List<AccountDto> getCorrectListRsSearch(Page<Person> people) {
        List<AccountDto> accountDtos = new ArrayList<>();
        people.stream().forEach(person -> accountDtos.add(getCorrectRsSearch(person, StatusCode.NONE)));//TODO: StatusCode
        return accountDtos;
    }

    private final static long TIME = 5 * 60 * 1000;

    private static boolean checkIsOnline(long lastOnlineTime) {
        long ms = new Date().getTime() - lastOnlineTime;
        return ms < TIME;
    }
}
