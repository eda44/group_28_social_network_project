package ru.skillbox.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import ru.skillbox.model.Post;
import ru.skillbox.model.PostComment;
import ru.skillbox.repository.PostCommentRepository;
import ru.skillbox.request.CommentAddRequest;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Service
public class PostCommentService {
    private final PostCommentRepository postCommentRepository;
    private final PostService postService;
    private final PersonService personService;
    private static final Logger logger = LogManager.getLogger(PostCommentService.class);

    @Autowired
    public PostCommentService(PostCommentRepository postCommentRepository, PostService postService, PersonService personService) {
        this.postCommentRepository = postCommentRepository;
        this.postService = postService;
        this.personService = personService;
    }

    public PostComment getPostCommentById(long id) {
        return postCommentRepository.findById(id).get();
    }

    public void addComment(String id, CommentAddRequest request) {
        Post post = postService.getPostById(Long.parseLong(id));
        logger.info("getting post by id " + id);
        PostComment postComment = new PostComment();
        postComment.setCommentText(request.getCommentText());
        postComment.setPerson(personService.getCurrentPerson());
        postComment.setPost(post);
        postComment.setTime(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));
        postComment.setIsBlocked(false);
        if (request.getParentId() != null) {
            postComment.setParentId(request.getParentId());
        } else {
            postComment.setParentId(0L);
        }
        postCommentRepository.save(postComment);
        logger.info("saving comment № " + postComment.getId());
    }

    public void deleteComment(String id, String commentId) {
        Post post = postService.getPostById(Long.parseLong(id));
        PostComment postComment = getPostCommentById(Long.parseLong(commentId));
        if (post.getPostCommentList().contains(postComment)) {
            postCommentRepository.delete(postComment);
            logger.info("deleting comment № " + postComment.getId());
            post.getPostCommentList().remove(postComment);
            postService.savePost(post);

        }
    }

    public void updateComment(@RequestBody CommentAddRequest request,
                              @PathVariable String id, @PathVariable String commentId) {
        Post post = postService.getPostById(Long.parseLong(id));
        PostComment postComment = getPostCommentById(Long.parseLong(commentId));
        if (!post.getPostCommentList().isEmpty()) {
            postComment.setCommentText(request.getCommentText());
            postComment.setPerson(personService.getCurrentPerson());
            postComment.setPost(post);
            postComment.setTime(request.getTime());
            postComment.setTimeChanged((new Date().getTime()));
            postComment.setIsBlocked(request.getIsBlocked());
            postCommentRepository.save(postComment);
            logger.info("updating comment № " + postComment.getId());
        }
    }
}
