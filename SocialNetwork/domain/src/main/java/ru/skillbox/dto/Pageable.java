package ru.skillbox.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pageable {

    private Integer page;
    private Integer size;
    private String[] sort;
}
