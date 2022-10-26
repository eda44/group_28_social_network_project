package ru.skillbox.response;

import lombok.Getter;
import lombok.Setter;
import ru.skillbox.dto.AccountAndStatusByIdDto;

import java.util.List;

@Getter
@Setter
public class FriendsGetAllRs {
    private String error;
    private Long timestamp;
    private List<AccountAndStatusByIdDto> data;
    private String error_description;
}