package ru.skillbox.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import ru.skillbox.dto.enums.MessagePermission;
import ru.skillbox.dto.enums.StatusCode;
import ru.skillbox.model.Person;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
public class AccountAndStatusByIdDto {

    private Long id;
    private String email;
    private String phone;
    private String photo;
    private String about;
    private CityDto city;
    private CountryDto country;
    @JsonProperty("status_code")
    private StatusCode statusCode;
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

    public static AccountAndStatusByIdDto getCorrectResponseFrom(Person person) {
        return AccountAndStatusByIdDto.builder()
                .id(person.getId())
                .email(person.getEmail())
                .phone(person.getPhone())
                .photo(person.getPhoto())
                .about(person.getAbout())
                .city(new CityDto())//TODO: заглушка
                .country(new CountryDto())//TODO: заглушка
                //TODO: statusCode
                .firstName(person.getFirstName())
                .lastName(person.getLastName())
                .regDate(person.getRegDate())
                .birthDate(person.getBirthDate())
                .messagePermission(person.getMessagePermission())
                .lastOnlineTime(person.getLastOnlineTime())
                //TODO: isOnline
//                .isBlocked(person.isBlocked())
                .build();
    }
    public static List<AccountAndStatusByIdDto> getCorrectListResponseFrom(List<Person> persons) {
        List<AccountAndStatusByIdDto> accountDtos = new ArrayList<>();
        for (Person person : persons) {
            accountDtos.add(getCorrectResponseFrom(person));
        }
        return accountDtos;
    }
}
