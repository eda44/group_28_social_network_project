package ru.skillbox.model;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import ru.skillbox.request.DialogRequest;
import ru.skillbox.response.DialogListResponse;
import ru.skillbox.response.DialogRs;
import ru.skillbox.response.MessageRs;

public interface DialogController {
    ResponseEntity<DialogListResponse> allDialogs(Integer offset, Integer itemPerPage);

    ResponseEntity<DialogRs> getMessages(Long interlocutorId, Integer offset, Integer itemPerPage);

    ResponseEntity<MessageRs> createNewMessage(Long companionId);
}

