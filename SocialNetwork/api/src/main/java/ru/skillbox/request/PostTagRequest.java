package ru.skillbox.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PostTagRequest {

    private List<String> tag;
}
