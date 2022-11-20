package ru.skillbox.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.skillbox.model.CommentLike;
import ru.skillbox.repository.CommentLikeRepository;

@Service
public class CommentLikeService {

    private final CommentLikeRepository commentLikeRepository;

    @Autowired
    public CommentLikeService(CommentLikeRepository commentLikeRepository) {
        this.commentLikeRepository = commentLikeRepository;
    }

    public void delete(CommentLike commentLike) {
        commentLikeRepository.delete(commentLike);
    }
}
