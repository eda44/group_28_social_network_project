package ru.skillbox.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skillbox.dto.DialogDto;
import ru.skillbox.dto.MessageDto;
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

    @Autowired
    public DialogService(DialogRepository dialogRepository, MessageRepository messageRepository,
                         DialogMapper dialogMapper) {
        this.dialogRepository = dialogRepository;
        this.messageRepository = messageRepository;
        this.dialogMapper = dialogMapper;
    }

    @Transactional(readOnly = true)
    public DialogListResponse getDialog(Person curPerson, Integer offset, Integer itemPerPage) {
        List<Dialog> dialogList = dialogRepository.findAllDialogsForPerson(curPerson);
        List<DialogDto> dialogDtoList = new ArrayList<>();

        for (Dialog dialog : dialogList) {

            Person conversationPartner = dialog.getOwner() == curPerson
                    ? dialog.getConversationPartner() : dialog.getOwner();

            if(conversationPartner != dialog.getConversationPartner())
                dialog.setConversationPartner(conversationPartner);

            log.info("CONVERSATION_PARTNER: {} {}",
                    conversationPartner.getFirstName(),
                    conversationPartner.getLastName());

            List<Message> messages = dialog.getMessages();

            MessageDto messageDto = !messages.isEmpty()
                    ? dialogMapper.toDto(messages.get(messages.size() - 1))
                    : MessageDto.builder().build();

            dialog.setLastMessage(messageDto);

            dialogDtoList.add(dialogMapper.toDto(dialog));
        }

        return DialogListResponse.builder()
                .timestamp(System.currentTimeMillis())
                .total(10)
                .offset(offset)
                .perPage(itemPerPage)
                .currentUserId(curPerson.getId())
                .data(dialogDtoList)
                .build();
    }

    @Transactional(readOnly = true)
    public DialogRs getMessageRs(Person curPerson, Person companion, Integer offset, Integer itemPerPage) {
        List<Dialog> dialogList = dialogRepository.findAllDialogsForPerson(curPerson);
        Dialog dialogRes = new Dialog();

        for(Dialog dialog : dialogList) {
            if (dialog.getOwner() == companion || dialog.getConversationPartner() == companion)
                dialogRes = dialog;
        }

        List<Message> messageList = messageRepository.findAllByDialogId(dialogRes);
        List<MessageDto> messageDtoList = new ArrayList<>();

        messageList.forEach(message -> messageDtoList.add(dialogMapper.toDto(message)));

        return DialogRs.builder()
                .timestamp(System.currentTimeMillis())
                .total(10)
                .offset(offset)
                .perPage(itemPerPage)
                .data(messageDtoList)
                .build();
    }

    @Transactional
    public MessageRs saveNewMessage(Person companion) {
        return MessageRs.builder()
                .timestamp(System.currentTimeMillis())
                .data(DataMessage.builder()
                        .message("Ok")
                        .build())
                .build();
    }

}


