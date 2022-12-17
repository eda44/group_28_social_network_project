package ru.skillbox.model;

import lombok.*;
import ru.skillbox.dto.enums.FriendshipCode;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Table(name = "friendship_status")

public class FriendshipStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "time")
    private Long time;
    @Column(name = "name")
    private String name;
    @Column(columnDefinition = "enum('REQUEST', 'FRIEND', 'BLOCKED', 'DECLINED', 'SUBSCRIBED')")
    @Enumerated(EnumType.STRING)
    private FriendshipCode code;
}