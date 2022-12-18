package ru.skillbox.handler;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;
import ru.skillbox.model.Person;
import ru.skillbox.repository.DialogRepository;
import ru.skillbox.repository.PersonRepository;
import ru.skillbox.request.MessageRq;
import ru.skillbox.service.DialogService;
import ru.skillbox.service.PersonService;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

@Log4j2
@Component
public class MessageWebSocketHandler extends AbstractWebSocketHandler {

    private final DialogService dialogService;
    private final DialogRepository dialogRepository;
    private final PersonService personService;
    private final PersonRepository personRepository;
    List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();
    Map<String, WebSocketSession> sessionMap = new ConcurrentHashMap<>();

    public MessageWebSocketHandler(DialogService dialogService, DialogRepository dialogRepository, PersonService personService, PersonRepository personRepository) {
        this.dialogService = dialogService;
        this.dialogRepository = dialogRepository;
        this.personService = personService;
        this.personRepository = personRepository;
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        MessageRq messageRq = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .readValue(payload, MessageRq.class);
        //log.info("CURRENT SESSION: {}", session.getId());

        Optional<Person> recipient = personRepository.findById(messageRq.getData().getRecipientId());

        for (Map.Entry<String, WebSocketSession> entry : sessionMap.entrySet()) {
            if(entry.getKey().equals(recipient.get().getEmail())) {
                entry.getValue().sendMessage(new TextMessage(payload));
            }
        }

        dialogService.saveMessage(messageRq);
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {

        sessionMap.put(session.getPrincipal().getName(), session);
        log.info("==SESSION_MAP CONTAINS EMAILS START SESSION==");
        sessionMap.forEach((k,v) -> {
            log.info("EMAIL: {}", k);
        });
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessionMap.remove(session.getPrincipal().getName());
        log.info("==SESSION_MAP CONTAINS EMAILS CLOSE SESSION==");
        sessionMap.forEach((k,v) -> {
            log.info("EMAIL: {}", k);
        });
    }
}
