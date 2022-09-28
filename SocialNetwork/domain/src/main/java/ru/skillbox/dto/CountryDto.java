package ru.skillbox.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CountryDto {

    private long id;
    private String title;
    private CityDto[] cities;
}
