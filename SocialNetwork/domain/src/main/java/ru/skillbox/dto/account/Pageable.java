package ru.skillbox.dto.account;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pageable {

    private int page;
    private int size;
    private String[] sort;
}
