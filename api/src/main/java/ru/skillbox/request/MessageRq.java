package ru.skillbox.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.skillbox.response.DataMessage;
import ru.skillbox.response.data.MessageDto;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MessageRq {

    private String type;
    private Long accountId;
    private MessageDto data;
}
