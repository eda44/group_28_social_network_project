package ru.skillbox.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.skillbox.dto.enums.LikeType;
import ru.skillbox.model.*;
import ru.skillbox.repository.CommentLikeRepository;
import ru.skillbox.repository.PostLikeRepository;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class LikeService {

    private final PostLikeRepository postLikeRepository;
    private final CommentLikeRepository commentLikeRepository;
    private final PostService postService;
    private final PersonService personService;
    private final PostCommentService postCommentService;
    private static final Logger logger = LogManager.getLogger(LikeService.class);

    @Autowired
    public LikeService(PostLikeRepository postLikeRepository, CommentLikeRepository commentLikeRepository, PostService postService,
                       PersonService personService, PostCommentService postCommentService) {
        this.postLikeRepository = postLikeRepository;
        this.commentLikeRepository = commentLikeRepository;
        this.postService = postService;
        this.personService = personService;
        this.postCommentService = postCommentService;
    }

    public void addPostLike(String id) {
        Post post = postService.getPostById(Long.parseLong(id));
        PostLike postLike = new PostLike();
        postLike.setTime(new Date().getTime());
        postLike.setPost(post == null ? new Post() : post);
        postLike.setPerson(personService.getCurrentPerson() == null ? new Person() : personService.getCurrentPerson());
        Objects.requireNonNull(post).setPostLikes(List.of(postLike));
        postLikeRepository.save(postLike);
        logger.info("saving postLike");
    }

    public void addCommentLike(String id, String commentId) {
        Post post = postService.getPostById(Long.parseLong(id));
        PostComment postComment = postCommentService.getPostCommentById(Long.parseLong(commentId));
        CommentLike commentLike = new CommentLike();
        if (post.getPostCommentList().contains(postComment) && !isLiked(Long.valueOf(commentId), LikeType.COMMENT)) {
            commentLike.setComment(postComment);
            commentLike.setPerson(personService.getCurrentPerson() == null ?
                    new Person() : personService.getCurrentPerson());
            commentLike.setTime(new Date().getTime());
            commentLikeRepository.save(commentLike);
            postService.savePost(post);
            logger.info("saving commentLike");
        }
    }

    public void deletePostLike(String id) {
        Post post = postService.getPostById(Long.parseLong(id));
        List<PostLike> postLikes = post.getPostLikes();
        if (!postLikes.isEmpty() && isLiked(Long.valueOf(id), LikeType.POST)) {
            for (PostLike postLike : postLikes) {
                postLikeRepository.delete(postLike);
                postLikeRepository.flush();
                logger.info("deleting postLike");
            }
        }
    }

    public void deleteCommentLike(String id, String commentId) {
        Post post = postService.getPostById(Long.parseLong(id));
        PostComment comment = postCommentService.getPostCommentById(Long.parseLong(commentId));
        logger.info(post.getPostCommentList().size());
        if (post.getPostCommentList().contains(comment) && isLiked(Long.valueOf(commentId), LikeType.COMMENT)) {
            for (CommentLike commentLike : comment.getCommentLikes()) {
                commentLikeRepository.delete(commentLike);
                commentLikeRepository.flush();
                logger.info("deleting commentLike");
            }
        }
    }

    public Boolean isLiked(Long id, LikeType type) {
        switch (type) {
            case POST: {
                Post post = postService.getPostById(id);
                for (PostLike postLike : post.getPostLikes()) {
                    return postLikeRepository.findAll().contains(postLike);
                }
                break;
            }
            case COMMENT: {
                PostComment postComment = postCommentService.getPostCommentById(id);
                for (CommentLike commentLike : postComment.getCommentLikes()) {
                    return commentLikeRepository.findAll().contains(commentLike);
                }
            }
        }
        return false;
    }
}