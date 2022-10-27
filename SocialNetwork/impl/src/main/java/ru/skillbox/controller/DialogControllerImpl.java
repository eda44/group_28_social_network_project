package ru.skillbox.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skillbox.model.DialogController;
import ru.skillbox.request.DialogRequest;
import ru.skillbox.response.DialogListResponse;
import ru.skillbox.response.DialogRs;

@RestController
@RequestMapping("/api/v1/dialogs")
public class DialogControllerImpl implements DialogController {

    //Получение списка диалогов пользователя
    @GetMapping
    public ResponseEntity<DialogListResponse> allDialogs(@RequestParam(required = false) Integer offset,
                                                         @RequestParam(required = false) Integer itemPerPage){
        return ResponseEntity.ok(DialogListResponse.getDialogListResponse(offset, itemPerPage));
    }

    //Получение сообщений диалога
    @GetMapping("/messages")
    public ResponseEntity<DialogRs> getMessages(@RequestParam Long interlocutorId,
                                                @RequestParam(required = false) Integer offset,
                                                @RequestParam(required = false) Integer itemPerPage) {
        return ResponseEntity.ok(DialogRs.getMessageRs(interlocutorId, offset, itemPerPage));
    }

    //Удаление диалога по id
    @DeleteMapping("/{id}")
    public ResponseEntity<DialogRs> deleteDialog(@PathVariable Long id) {
        return ResponseEntity.ok(DialogRs.deleteDialog(id));
    }

    //Получение общего количества непрочитанных сообщений
    @GetMapping("/unreaded")
    public ResponseEntity<DialogRs> getUnreadMsg(){
        return ResponseEntity.ok(DialogRs.getUnreadMsg());
    }

    //Создание диалога
    @PostMapping
    public ResponseEntity<DialogRs> createDialog(@RequestBody DialogRequest dialogRequest) {
        return ResponseEntity.ok(DialogRs.createDialog());
    }

    //Пометить сообщение как прочитанное
    @PutMapping("/{interlocutor_id}/messages/{message_id}/read")
    public ResponseEntity<DialogRs> markAsRead(@PathVariable Long interlocutor_id,
                                               @PathVariable Long message_id) {
        return ResponseEntity.ok(DialogRs.markAsRead());
    }
}

