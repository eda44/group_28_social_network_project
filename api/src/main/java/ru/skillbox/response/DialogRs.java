package ru.skillbox.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ru.skillbox.response.data.MessageDto;

import java.util.List;

@Setter
@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DialogRs {

    private String error;
    private Long timestamp;
    private Integer total;
    private Integer offset;
    private List<MessageDto> data;
    private String errorDescription;
    private Integer perPage;
    private Integer currentUserId;

}
