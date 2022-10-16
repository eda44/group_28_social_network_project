package ru.skillbox.model.api.request;

import lombok.Getter;
import lombok.Setter;
import ru.skillbox.model.Tag;

import java.util.List;

@Getter
@Setter
public class PostAddRequest {
    private String title;
    private List<Tag> tags;
    private String postText;
    private String photo_url;
}
