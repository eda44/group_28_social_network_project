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
    private static Logger logger = LogManager.getLogger(LikeService.class);


    @Autowired
    public LikeService(PostLikeRepository postLikeRepository, CommentLikeRepository commentLikeRepository, PostService postService,
                       PersonService personService, PostCommentService postCommentService) {
        this.postLikeRepository = postLikeRepository;
        this.commentLikeRepository = commentLikeRepository;
        this.postService = postService;
        this.personService = personService;
        this.postCommentService = postCommentService;
    }

    public void deleteLike(long id) {
        postLikeRepository.deleteById(id);
    }


    public PostLike getLikeByCommentId(long id) {
        return postLikeRepository.findById(id).get();
    }

    public void saveLike(PostLike postLike) {
        postLikeRepository.save(postLike);
    }

    public List<PostLike> getAllLikes() {
        return postLikeRepository.findAll();
    }

    public void setPostLike(String id) {
        Post post = postService.getPostById(Long.parseLong(id));
        PostLike postLike = new PostLike();
        postLike.setTime(new Date().getTime());
        postLike.setPost(post == null ? new Post() : post);
        postLike.setPerson(personService.getCurrentPerson() == null ? new Person() : personService.getCurrentPerson());
        Objects.requireNonNull(post).setPostLikes(List.of(postLike));
        postLikeRepository.save(postLike);
        logger.info("saving postLike");
    }

    public void setCommentLike(String id, String commentId) {
        Post post = postService.getPostById(Long.parseLong(id));
        PostComment postComment = postCommentService.getPostCommentById(Long.parseLong(commentId));
        CommentLike commentLike = new CommentLike();
        if (post.getPostCommentList().contains(postComment)) {
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
        if (!postLikes.isEmpty() || isLiked(Long.valueOf(id), LikeType.POST)) {
            postLikeRepository.delete(postLikes.get(0));
            postLikeRepository.flush();
            logger.info("deleting post like");
            postLikes.remove(0);
        }
    }

    public void deleteCommentLike(String id, String commentId) {
        Post post = postService.getPostById(Long.parseLong(id));
        PostComment comment = postCommentService.getPostCommentById(Long.parseLong(commentId));
        if (post.getPostCommentList().contains(comment) ||
                isLiked(Long.valueOf(commentId), LikeType.COMMENT)) {
            commentLikeRepository.delete(comment.getCommentLikes().get(0));
            logger.info("deleting comment like");
        }
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
}