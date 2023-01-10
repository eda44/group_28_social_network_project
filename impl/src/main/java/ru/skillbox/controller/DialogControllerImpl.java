package ru.skillbox.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skillbox.model.DialogController;
import ru.skillbox.response.DialogListResponse;
import ru.skillbox.response.DialogRs;
import ru.skillbox.response.MessageRs;
import ru.skillbox.service.DialogService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/dialogs")
public class DialogControllerImpl implements DialogController {

    private final DialogService dialogService;

    //Получение списка диалогов пользователя
    @GetMapping
    public ResponseEntity<DialogListResponse> allDialogs(@RequestParam(required = false) Integer offset,
                                                         @RequestParam(required = false) Integer itemPerPage) {
        return dialogService.getDialogs(offset, itemPerPage);
    }

    //Получение сообщений диалога
    @GetMapping("/messages")
    public ResponseEntity<DialogRs> getMessages(@RequestParam Long companionId,
                                                @RequestParam(required = false) Integer offset,
                                                @RequestParam(required = false) Integer itemPerPage) {
        return dialogService.getMessages(companionId, offset, itemPerPage);
    }

    //Пометить сообщения прочитанными
    @PutMapping("/{companionId}")
    public ResponseEntity<MessageRs> markAsRead(@PathVariable Long companionId) {
        return dialogService.markAsRead(companionId);
    }

    //Получить количество непрочитанных сообщений
    @GetMapping("/unreaded")
    public ResponseEntity<MessageRs> getUnreadMessages() {
        return dialogService.getUnreadMessage();
    }
}

