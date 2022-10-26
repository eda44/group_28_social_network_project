package ru.skillbox.response;

import lombok.Getter;
import lombok.Setter;
import ru.skillbox.dto.AccountByIdDto;

import java.util.List;

@Getter
@Setter
public class FriendsGetByRecommendationRS {
    private String error;
    private Long timestamp;
    private List<AccountByIdDto> data;
    private String error_description;
}
