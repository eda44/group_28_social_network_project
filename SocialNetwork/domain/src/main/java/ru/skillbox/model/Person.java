package ru.skillbox.model;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;
import ru.skillbox.dto.enums.MessagePermission;

@Entity
@Table(name = "persons")
@Getter
@Setter
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "reg_date")
    private long regDate;
    @Column(name = "birth_date")
    private long birthDate;

    private String email;

    private String phone;

    private String photo;

    private String about;

    private String town;
    @Column(name = "confirmation_code")
    private int confirmationCode;
    @Column(name = "is_approved")
    private boolean isApproved;
    @Enumerated(EnumType.STRING)
    @Column(name = "message_permission")
    private MessagePermission messagePermission;
    @Column(name = "last_online_time")
    private long lastOnlineTime;
    @Column(name = "is_blocked")
    private boolean isBlocked;
}

