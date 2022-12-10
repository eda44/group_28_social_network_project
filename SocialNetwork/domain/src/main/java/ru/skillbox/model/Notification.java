package ru.skillbox.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "notification")
public class Notification {

    /**
     * id оповещения
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * тип оповещения
     */
    //@Column(name = "type_id")
    //private NotificationType typeId;

    /**
     * время отправки оповещения
     */
    @Column(name = "sent_time")
    private Date sentTime;

    /**
     * кому отправлено оповещение
     */
    // @Column(name = "person_id")
    // private Person personId;

    /**
     * идентификатор сущности,
     * относительно которой отправлено оповещение
     * (комментарий, друг, пост или сообщение)?
     */
    @Column(name = "entity_id")
    private Integer entityId;

    /**
     * куда отправлено оповещение (конкретный e-mail или телефон)
     */
    private String contact;

}