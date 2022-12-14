package ru.skillbox.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.skillbox.dto.enums.LikeType;
import ru.skillbox.model.*;
import ru.skillbox.repository.CommentLikeRepository;
import ru.skillbox.repository.PostLikeRepository;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
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

    public PostLike getLikeByPostIdAndPersonId(Long postId, Long personId) {
        return postLikeRepository.findByPostIdAndPersonId(postId, personId).get();
    }

    public CommentLike getLikeByCommentIdAndPersonId(Long commentId, Long personId) {
        return commentLikeRepository.findByCommentIdAndPersonId(commentId, personId).get();
    }

    public void addPostLike(String id) {
        Post post = postService.getPostById(Long.parseLong(id));
        if(isLiked(post.getId(),LikeType.POST)){
            PostLike postLike = getLikeByPostIdAndPersonId(post.getId(),personService.getCurrentPerson().getId());
            postLike.setIsDelete(!postLike.getIsDelete());
            postLikeRepository.saveAndFlush(postLike);
            logger.info("saving postLike");
        } else {
            PostLike postLike = new PostLike();
            postLike.setTime(LocalDateTime.now()
                    .toEpochSecond(ZoneOffset.systemDefault().getRules().getOffset(LocalDateTime.now())));
            postLike.setPost(post == null ? new Post() : post);
            postLike.setPerson(personService.getCurrentPerson() == null ? new Person() :
                    personService.getCurrentPerson());
            Objects.requireNonNull(post).setPostLikes(List.of(postLike));
            postLike.setIsDelete(false);
            postLikeRepository.saveAndFlush(postLike);
            logger.info("saving postLike");
        }
    }

    public void addCommentLike(String id, String commentId) {
        Post post = postService.getPostById(Long.parseLong(id));
        PostComment postComment = postCommentService.getPostCommentById(Long.parseLong(commentId));
        if(isLiked(postComment.getId(),LikeType.COMMENT)){
            CommentLike commentLike = getLikeByCommentIdAndPersonId(postComment.getId(),
                personService.getCurrentPerson().getId());
            commentLike.setIsDelete(!commentLike.getIsDelete());
            commentLikeRepository.saveAndFlush(commentLike);
            logger.info("saving commentLike");
        } else {
            CommentLike commentLike = new CommentLike();
            commentLike.setComment(postComment);
            commentLike.setPerson(personService.getCurrentPerson() == null ?
                    new Person() : personService.getCurrentPerson());
            commentLike.setTime(LocalDateTime.now()
                    .toEpochSecond(ZoneOffset.systemDefault().getRules().getOffset(LocalDateTime.now())));
            commentLike.setIsDelete(false);
            commentLikeRepository.saveAndFlush(commentLike);
            postService.savePost(post);
            logger.info("saving commentLike");
        }
    }

    public void deletePostLike(String id) {
        Post post = postService.getPostById(Long.parseLong(id));
        List<PostLike> postLikes = post.getPostLikes();
        if (!postLikes.isEmpty() && isLiked(Long.valueOf(id), LikeType.POST)) {
            for (PostLike postLike : postLikes) {
                postLike.setIsDelete(!postLike.getIsDelete());
                postLikeRepository.saveAndFlush(postLike);
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
                commentLike.setIsDelete(!commentLike.getIsDelete());
                commentLikeRepository.saveAndFlush(commentLike);
                logger.info("deleting commentLike");
            }
        }
    }

    public Boolean isLiked(Long id, LikeType type) {
        switch (type) {
            case POST : {
                Post post = postService.getPostById(id);
                for (PostLike postLike : post.getPostLikes()) {
                    return postLikeRepository.findAll().contains(postLike);
                }
                break;
            }
            case COMMENT : {
                PostComment postComment = postCommentService.getPostCommentById(id);
                for (CommentLike commentLike : postComment.getCommentLikes()) {
                    return commentLikeRepository.findAll().contains(commentLike);
                }
                break;
            }
        }
        return false;
    }
}