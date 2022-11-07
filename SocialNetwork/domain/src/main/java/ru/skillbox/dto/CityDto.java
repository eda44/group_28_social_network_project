package ru.skillbox.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CityDto {

    private Long id;
    private String title;
    @JsonProperty("country_id")
    private Integer countryId;
}
