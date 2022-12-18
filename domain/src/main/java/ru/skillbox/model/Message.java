package ru.skillbox.model;

import lombok.Getter;
import lombok.Setter;
import ru.skillbox.dto.enums.Status;

import javax.persistence.*;

@Setter
@Getter
@Entity(name = "messages")
public class Message {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long time;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "message_text")
    private String messageText;

    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private Person authorId;

    @ManyToOne
    @JoinColumn(name = "recipient_id", referencedColumnName = "id")
    private Person recipientId;

    @ManyToOne
    @JoinColumn(name = "dialog_id", referencedColumnName = "id")
    private Dialog dialogId;

}
