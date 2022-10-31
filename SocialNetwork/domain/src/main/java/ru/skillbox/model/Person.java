package ru.skillbox.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.skillbox.dto.enums.MessagePermission;

import javax.persistence.*;
import java.util.List;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "persons")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "reg_date")
    private Long regDate;
    @Column(name = "birth_date")
    private Long birthDate;
    private String email;
    private String phone;
    private String password;
    private String photo;
    private String about;
    private String town;
    @Column(name = "confirmation_code")
    private int confirmationCode;
    @Column(name = "is_approved")
    private boolean isApproved;
    @Column(name = "message_permission")
    private MessagePermission messagePermission;
    @Column(name = "last_online_time")
    private Long         lastOnlineTime;
    @Column(name = "is_blocking")
    private boolean isBlocking;
    @OneToMany(mappedBy = "person")
    private List<Post> postList;
    @OneToMany(mappedBy = "person")
    private List<PostLike> postLikeList;
}

