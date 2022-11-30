package ru.skillbox.model;

import lombok.Getter;
import lombok.Setter;
import ru.skillbox.dto.MessageDto;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@Entity(name = "dialogs")
public class Dialog {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    private Person owner;

    @ManyToOne()
    @JoinColumn(name = "partner_id", referencedColumnName = "id")
    private Person conversationPartner;

    @Column(name = "unread_count")
    private Long unreadCount;

    @OneToMany(mappedBy = "dialogId")
    List<Message> messages;

    @Transient
    private MessageDto lastMessage;

}
