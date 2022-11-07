package ru.skillbox.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "post_comment", schema = "db_social")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@Data
public class PostComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id",referencedColumnName = "id", insertable = false,updatable = false)
    private Post post;

    @Column(name = "parent_id")
    private Long parentId;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id",referencedColumnName = "id", insertable = false,updatable = false)
    private Person person;

    @Column(name = "comment_text")
    private String commentText;

    @Column(name = "is_blocked")
    private Boolean isBlocked;

    private Long time;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id")
    private List<CommentLike> commentLikes;
}
