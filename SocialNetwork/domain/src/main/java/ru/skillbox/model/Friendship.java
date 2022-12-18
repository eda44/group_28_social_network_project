package ru.skillbox.model;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import ru.skillbox.dto.enums.FriendshipCode;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "friendship")
public class Friendship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private FriendshipCode statusCode;

    @Column(name = "previous_status")
    private String previousStatus;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "src_person_id")
    private Person srcPerson;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "dst_person_id")
    private Person dstPerson;
}
