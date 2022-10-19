package ru.skillbox.model;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Builder;
import lombok.Data;
import ru.skillbox.dto.enums.MessagePermission;

@Entity
@Builder
@Data
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String firstName;

    private String lastName;

    private Date regDate;

    private Date birthDate;

    private String email;

    private String phone;

    private String password;

    private String photo;

    private String about;

    private String town;

    private int confirmationCode;

    private boolean isApproved;

    private MessagePermission messagePermission;

    private Date lastOnlineTime;

    private boolean isBlocking;

}

