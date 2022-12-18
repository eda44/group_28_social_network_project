package ru.skillbox.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ru.skillbox.response.data.DialogDto;

import java.util.List;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class DialogListResponse {

    private String error;
    private Long timestamp;
    private Integer total;
    private Integer offset;
    private List<DialogDto> data;
    private String errorDescription;
    private Integer perPage;
    private Long currentUserId;
}

