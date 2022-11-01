package ru.skillbox.model;

import lombok.*;
import ru.skillbox.dto.enums.MessagePermission;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "people")
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
    private Long birthDate;
    private String email;
    private String phone;
    private String photo;
    private String about;
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
    @Column(name = "is_enabled")
    private boolean isEnabled;
    @OneToMany(mappedBy = "person")
    private List<Post> postList;
    @OneToMany(mappedBy = "person")
    private List<PostLike> postLikeList;
}

