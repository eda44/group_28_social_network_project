package ru.skillbox.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ru.skillbox.model.Person;

@Setter
@Getter
@Builder
@AllArgsConstructor
public class LikeDto {
    private Person person;
}
