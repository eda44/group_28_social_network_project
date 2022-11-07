package ru.skillbox.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostTagDto {

    private Integer id;
    private String tag;

}
