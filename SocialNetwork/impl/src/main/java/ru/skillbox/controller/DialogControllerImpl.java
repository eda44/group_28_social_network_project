package ru.skillbox.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skillbox.exception.UserNotFoundException;
import ru.skillbox.model.DialogController;
import ru.skillbox.model.Person;
import ru.skillbox.request.DialogRequest;
import ru.skillbox.response.DialogListResponse;
import ru.skillbox.response.DialogRs;
import ru.skillbox.response.MessageRs;
import ru.skillbox.service.DialogService;
import ru.skillbox.service.PersonService;

@RestController
@RequestMapping("/api/v1/dialogs")
public class DialogControllerImpl implements DialogController {

    private final DialogService dialogService;
    private final PersonService personService;

    @Autowired
    public DialogControllerImpl(DialogService dialogService, PersonService personService) {
        this.dialogService = dialogService;
        this.personService = personService;
    }

    //Получение списка диалогов пользователя
    @GetMapping
    public ResponseEntity<DialogListResponse> allDialogs(@RequestParam(required = false) Integer offset,
                                                         @RequestParam(required = false) Integer itemPerPage){
        return ResponseEntity.ok(dialogService.getDialog(personService.getCurrentPerson(), offset, itemPerPage));
    }

    //Получение сообщений диалога
    @GetMapping("/messages")
    public ResponseEntity<DialogRs> getMessages(@RequestParam Long companionId,
                                                @RequestParam(required = false) Integer offset,
                                                @RequestParam(required = false) Integer itemPerPage) {

        Person conversationPartner;
        try {
            conversationPartner = personService.getPersonById(companionId);
        } catch (UserNotFoundException e) {
            throw new RuntimeException(e);
        }

        return ResponseEntity.ok(dialogService.getMessageRs(personService.getCurrentPerson(),
                                                            conversationPartner,
                                                            offset, itemPerPage));
    }

    @PutMapping("/{companionId}")
    public ResponseEntity<MessageRs> createNewMessage(@PathVariable Long companionId) {
        Person conversationPartner;
        try {
            conversationPartner = personService.getPersonById(companionId);
        } catch (UserNotFoundException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok(dialogService.saveNewMessage(conversationPartner));
    }
}

