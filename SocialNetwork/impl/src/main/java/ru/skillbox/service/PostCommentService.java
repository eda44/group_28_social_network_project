package ru.skillbox.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.skillbox.model.PostComment;
import ru.skillbox.repository.PostCommentRepository;

@Service
public class PostCommentService {
    private final PostCommentRepository postCommentRepository;


    @Autowired
    public PostCommentService(PostCommentRepository postCommentRepository) {
        this.postCommentRepository = postCommentRepository;
    }

    public PostComment getPostCommentById(long id) {
        return postCommentRepository.findById(id).get();
    }

    public void savePostComment(PostComment postComment) {
        postCommentRepository.save(postComment);
    }

    public void deletePostComment(PostComment postComment) {
        postCommentRepository.delete(postComment);
    }
}
