package ru.skillbox.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CityDto {

    private long id;
    private String title;
    private int countryId;
}
