package ru.skillbox.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import ru.skillbox.dto.enums.StatusCode;
//import ru.skillbox.model.api.response.FriendshipApi;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Table(name = "friendship")
public class Friendship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    //@ManyToOne(fetch = FetchType.EAGER)
    //@JoinColumn(name = "src_person_id", nullable = false)
    //private FriendshipApi srcPersonId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "dst_person_id", nullable = false)
    private Friendship destPersonId;

    @Enumerated(EnumType.STRING)
   // @Column(name = "code", columnDefinition = "ENUM(" +
   //         " 'FRIEND' ," +
   //         " 'REQUEST_TO' ," +
   //         " 'REQUEST_FROM' ," +
   //         " 'BLOCKED' ," +
   //         " 'REJECTING' ," +
   //         " 'DECLINED' ," +
   //         " 'SUBSCRIBED' ," +
   //         " 'NONE' ," +
   //         " 'WATCHING')")
   // @NonNull
    private StatusCode code;
}