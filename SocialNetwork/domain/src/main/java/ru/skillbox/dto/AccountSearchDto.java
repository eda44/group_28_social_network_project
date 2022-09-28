package ru.skillbox.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountSearchDto {

    private long[] ids;
    private String author;
    private String firstName;
    private String lastName;
    private long birthdateFrom;
    private long birthdateTo;
    private CityDto city;
    private long cityId;
    private CountryDto country;
    private long countryId;
    private boolean isBlocked;
    private boolean isDeleted;
    private PageableObject page;
}
