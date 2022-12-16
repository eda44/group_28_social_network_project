package ru.skillbox.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skillbox.dto.enums.Status;
import ru.skillbox.exception.UserNotFoundException;
import ru.skillbox.request.MessageRq;
import ru.skillbox.response.data.DialogDto;
import ru.skillbox.response.data.MessageDto;
import ru.skillbox.mapper.DialogMapper;
import ru.skillbox.model.Dialog;
import ru.skillbox.model.Message;
import ru.skillbox.model.Person;
import ru.skillbox.repository.DialogRepository;
import ru.skillbox.repository.MessageRepository;
import ru.skillbox.response.DataMessage;
import ru.skillbox.response.DialogListResponse;
import ru.skillbox.response.DialogRs;
import ru.skillbox.response.MessageRs;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@Service
public class DialogService {
    private final DialogRepository dialogRepository;
    private final MessageRepository messageRepository;
    private final DialogMapper dialogMapper;
    private final  PersonService personService;

    @Autowired
    public DialogService(DialogRepository dialogRepository, MessageRepository messageRepository,
                         DialogMapper dialogMapper, PersonService personService) {
        this.dialogRepository = dialogRepository;
        this.messageRepository = messageRepository;
        this.dialogMapper = dialogMapper;
        this.personService = personService;
    }

    @Transactional(readOnly = true)
    public ResponseEntity<DialogListResponse> getDialogs(Integer offset, Integer itemPerPage) {
        Person curPerson = personService.getCurrentPerson();
        List<Dialog> dialogList = dialogRepository.findAllDialogsForPerson(curPerson);
        List<DialogDto> dialogDtoList = new ArrayList<>();

        for (Dialog dialog : dialogList) {

            Person conversationPartner = dialog.getOwner() == curPerson
                    ? dialog.getConversationPartner() : dialog.getOwner();

            if (conversationPartner != dialog.getConversationPartner())
                dialog.setConversationPartner(conversationPartner);

            List<Message> messages = dialog.getMessages();

            /*MessageDto messageDto = !messages.isEmpty()
                    ? dialogMapper.toDto(messages.get(messages.size() - 1))
                    : MessageDto.builder().build();*/
            if (!messages.isEmpty()){
                dialog.setLastMessage(messages.get(messages.size() - 1));//TODO заглушка
            }


            dialogDtoList.add(dialogMapper.DialogToDto(dialog));
        }

        return ResponseEntity.ok(DialogListResponse.builder()
                .timestamp(System.currentTimeMillis())
                .total(10)
                .offset(offset)
                .perPage(itemPerPage)
                .currentUserId(curPerson.getId())
                .data(dialogDtoList)
                .build());
    }

    @Transactional
    public ResponseEntity<DialogRs> getMessages(Long id, Integer offset, Integer itemPerPage) {
        Person curPerson = personService.getCurrentPerson();
        Person companion;
        try {
            companion = personService.getPersonById(id);
        } catch (UserNotFoundException e) {
            throw new RuntimeException(e);
        }

        List<Dialog> dialogList = dialogRepository.findAllDialogsForPerson(curPerson);
        Dialog dialogRes = null;

        for (Dialog dialog : dialogList) {
            if (dialog.getOwner() == companion || dialog.getConversationPartner() == companion)
                dialogRes = dialog;
        }

        if (dialogRes == null) {
            dialogRes = new Dialog();
            dialogRes.setUnreadCount(0L);
            dialogRes.setOwner(curPerson);
            dialogRes.setConversationPartner(companion);

            dialogRepository.save(dialogRes);
        }


        List<Message> messageList = messageRepository.findAllByDialogId(dialogRes);
        List<MessageDto> messageDtoList = new ArrayList<>();

        messageList.forEach(message -> messageDtoList.add(dialogMapper.MessageToDto(message)));

        return ResponseEntity.ok(DialogRs.builder()
                .timestamp(System.currentTimeMillis())
                .total(10)
                .offset(offset)
                .perPage(itemPerPage)
                .data(messageDtoList)
                .build());
    }

    @Transactional
    public void saveMessage(MessageRq messageRq) {
        Message message = dialogMapper.DtoToMessage(messageRq.getData());
        message.setStatus(Status.SENT);
        Dialog dialog = dialogRepository.findDialogByOwnerAndConversationPartner(message.getAuthorId(),
                message.getRecipientId());
        message.setDialogId(dialog);
        messageRepository.save(message);
    }

    @Transactional
    public ResponseEntity<MessageRs> markAsRead(Long id) {
        Person currentPerson = personService.getCurrentPerson();

        try {
            Dialog dialog = dialogRepository.findDialogByOwnerAndConversationPartner(
                    currentPerson, personService.getPersonById(id));

            List<Message> messages = messageRepository.findAllByDialogId(dialog);
            messages.forEach(message -> {
                if (message.getRecipientId().getId().equals(currentPerson.getId())) {
                    message.setStatus(Status.READ);
                    messageRepository.save(message);
                }
            });
        }catch (UserNotFoundException e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok(MessageRs.builder()
                .timestamp(System.currentTimeMillis())
                .data(DataMessage.builder()
                        .message("Ok")
                        .build())
                .build());
    }

    @Transactional(readOnly = true)
    public ResponseEntity<MessageRs> getUnreadMessage() {

        List<Message> unreadMessages = messageRepository.findAllByRecipientIdAndStatus(
                personService.getCurrentPerson(), Status.SENT);

        return ResponseEntity.ok(MessageRs.builder()
                .timestamp(System.currentTimeMillis())
                .data(DataMessage.builder()
                        .count(unreadMessages.size())
                        .build())
                .build());
    }
}


