package ru.skillbox.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
//todo удалить если не используется

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Sort {

    private Boolean empty;
    private Boolean sorted;
    private Boolean unsorted;
}
