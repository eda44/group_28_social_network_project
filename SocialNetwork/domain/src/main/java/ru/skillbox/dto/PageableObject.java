package ru.skillbox.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageableObject {

    private long offset;
    private Sort sort;
    private int pageNumber;
    private int pageSize;
    private Boolean unpaged;
    private Boolean paged;

}
