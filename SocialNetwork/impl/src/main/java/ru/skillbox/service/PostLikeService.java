package ru.skillbox.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import ru.skillbox.dto.enums.LikeType;
import ru.skillbox.model.CommentLike;
import ru.skillbox.model.Post;
import ru.skillbox.model.PostComment;
import ru.skillbox.model.PostLike;
import ru.skillbox.repository.CommentLikeRepository;
import ru.skillbox.repository.PostLikeRepository;
import ru.skillbox.repository.PostRepository;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostLikeService {

    private final PostLikeRepository postLikeRepository;

    private final PostRepository postRepository;

    private final CommentLikeRepository commentLikeRepository;
    private final PostService postService;
    private final PersonService personService;
    private final PostCommentService postCommentService;

    @Autowired
    public PostLikeService(PostLikeRepository postLikeRepository, PostService postService,
                           PersonService personService, PostCommentService postCommentService,
                           CommentLikeRepository commentLikeRepository,
                           PostRepository postRepository) {
        this.postLikeRepository = postLikeRepository;
        this.postService = postService;
        this.personService = personService;
        this.postCommentService = postCommentService;
        this.commentLikeRepository = commentLikeRepository;
        this.postRepository = postRepository;
    }



    public PostLike getLikeByPostIdAndPersonId(Long postId, Long personId)
    {
        return postLikeRepository.findByPostIdAndPersonId(postId,personId).get();
    }

    public PostLike getLikeByCommentId(long id) {
        return postLikeRepository.findById(id).get();
    }

    public void deleteLike(PostLike postLike) {
        postLikeRepository.delete(postLike);
        postLikeRepository.flush();
    }

    public void saveLike(PostLike postLike) {
        postLikeRepository.saveAndFlush(postLike);
    }

    public List<PostLike> getAllLikes() {
        return postLikeRepository.findAll();
    }

    public PostLike setPostLike() {
        PostLike postLike = new PostLike();
        postLike.setTime(LocalDateTime.now()
                .toEpochSecond(ZoneOffset.systemDefault().getRules().getOffset(LocalDateTime.now())));
        saveLike(postLike);
        return postLike;
    }

    public Boolean isLiked(Long id, LikeType type) {
        switch (type) {
            case POST: {
                Post post = postService.getPostById(id);
                for (PostLike postLike : post.getPostLikes()) {
                    if (postLike.getPerson().equals(post.getPerson())) {
                        return true;
                    }
                }
                break;
            }
            case COMMENT: {
                PostComment postComment = postCommentService.getPostCommentById(id);
                for (PostLike postLike : postComment.getPost().getPostLikes()) {
                    if (postLike.getPerson().equals(postComment.getPerson())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public List<Long> getUsersByItemId(Long id, LikeType type) {
        List<Long> personIdList = new ArrayList<>();
        switch (type) {
            case POST: {
                Post post = postService.getPostById(id);
                for (PostLike postLike : post.getPostLikes()) {
                    personIdList.add(postLike.getPerson().getId());
                }
                return personIdList;
            }
            case COMMENT: {
            }

        }
        return new ArrayList<>();
    }

    public void saveCommentLike(CommentLike commentLike) {
        commentLikeRepository.saveAndFlush(commentLike);
    }

    public void deleteCommentLike(CommentLike commentLike) {
        commentLikeRepository.delete(commentLike);
        commentLikeRepository.flush();
    }

    public CommentLike getLikeByCommentIdAndPersonId(Long commentId, Long personId) {
        return commentLikeRepository.findByCommentIdAndPersonId(commentId,personId).get();
    }
}