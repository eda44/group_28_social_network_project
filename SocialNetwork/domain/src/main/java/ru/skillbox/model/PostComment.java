package ru.skillbox.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "post_comment")
@Getter
@Setter
public class PostComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long time;
    @ManyToOne
    @JoinColumn(name = "post_id", referencedColumnName = "id")
    private Post post;
//    @OneToMany
//        private PostComment parent;
    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person person;
    @Column(name = "comment_text")
    private String commentText;
    @Column(name = "is_blocked")
    private Boolean isBlocked;
}
