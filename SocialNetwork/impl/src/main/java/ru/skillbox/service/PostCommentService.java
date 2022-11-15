package ru.skillbox.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.skillbox.dto.CommentDto;
import ru.skillbox.model.Post;
import ru.skillbox.model.PostComment;
import ru.skillbox.repository.PostCommentRepository;

import java.util.List;

@Service
public class PostCommentService {
    private final PostCommentRepository postCommentRepository;
    private final PostService postService;
    private final PersonService personService;

    @Autowired
    public PostCommentService(PostCommentRepository postCommentRepository, PostService postService, PersonService personService) {
        this.postCommentRepository = postCommentRepository;
        this.postService = postService;
        this.personService = personService;
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

    public CommentDto setPostCommentDto(List<PostComment> postComments) {
        CommentDto commentDto = new CommentDto();
        for (PostComment postComment : postComments) {
            commentDto.setCommentsCount(postComments.size());
            commentDto.setCommentText(postComment.getCommentText());
            commentDto.setAuthorId(postComment.getPerson().getId());
            commentDto.setPostId(postComment.getPost().getId());
            commentDto.setIsBlocked(postComment.getIsBlocked());
            commentDto.setLikeAmount(postComment.getCommentLikes().size());
            commentDto.setParentId(postComment.getParentId());
            commentDto.setTime(postComment.getTime());
        }
        return commentDto;
    }

    public CommentDto setPostCommentDto(PostComment postComment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setCommentText(postComment.getCommentText());
        commentDto.setAuthorId(postComment.getPerson().getId());
        commentDto.setPostId(postComment.getPost().getId());
        commentDto.setIsBlocked(postComment.getIsBlocked());
        commentDto.setLikeAmount(postComment.getCommentLikes().size());
        commentDto.setParentId(postComment.getParentId());
        commentDto.setTime(postComment.getTime());
        return commentDto;
    }
}
