package ru.skillbox.model;

import org.springframework.http.ResponseEntity;
import ru.skillbox.request.DialogRequest;
import ru.skillbox.response.DialogListResponse;
import ru.skillbox.response.DialogRs;

public interface DialogController {
    ResponseEntity<DialogListResponse> allDialogs(Integer offset, Integer itemPerPage);

    ResponseEntity<DialogRs> getMessages(Long interlocutorId, Integer offset, Integer itemPerPage);

    ResponseEntity<DialogRs> deleteDialog(Long id);

    ResponseEntity<DialogRs> getUnreadMsg();

    ResponseEntity<DialogRs> createDialog(DialogRequest dialogRequest);

    ResponseEntity<DialogRs> markAsRead(Long interlocutorId, Long messageId);
}

