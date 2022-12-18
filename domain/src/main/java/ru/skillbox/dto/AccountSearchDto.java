package ru.skillbox.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountSearchDto {

    private Long[] ids;
    private String author;
    private String firstName;
    private String lastName;
    private Long birthdateFrom;
    private Long birthdateTo;
    private CityDto city;
    private Long cityId;
    private CountryDto country;
    private Long countryId;
    private Boolean isBlocked;
    private Boolean isDeleted;
    private PageableObject page;
}
