package ru.skillbox.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long time;
    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person person;
    @OneToMany(mappedBy = "post")
    private List<Tag> tags;
    private String title;
    @Column(name = "post_text")
    private String postText;
    @Column(name = "is_blocked")
    private Boolean isBlocked;
    @OneToMany(mappedBy = "post")
    private List<PostLike> postLikes;
    @OneToMany(mappedBy = "post")
    private List<PostComment> postComments;
    @OneToMany(mappedBy = "post")
    private List<PostFile> postFiles;
}
