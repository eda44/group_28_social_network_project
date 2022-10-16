package ru.skillbox.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime time;
//    @ManyToOne
//    @JoinColumn(name = "user_id", referencedColumnName = "id")
//    private User user;
    @OneToMany(mappedBy = "post")
//    private Tag[] tags;
    private List<Tag> tags;
    private String title;
    @Column(name = "post_text")
    private String postText;
    @Column(name = "is_blocked")
    private Boolean isBlocked;
    @OneToMany(mappedBy = "post")
    private List<Like> likes;
}
