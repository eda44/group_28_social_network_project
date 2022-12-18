package ru.skillbox.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageableObject {

    private Long offset;
    private Sort sort;
    private Integer pageNumber;
    private Integer pageSize;
    private Boolean unpaged;
    private Boolean paged;

}
