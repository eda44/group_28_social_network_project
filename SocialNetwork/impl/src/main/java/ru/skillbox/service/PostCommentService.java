package ru.skillbox.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.skillbox.dto.CommentDto;
import ru.skillbox.model.PostComment;
import ru.skillbox.repository.PostCommentRepository;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.stream.Collectors;

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
        postComment.setIsDelete(true);
        postComment.setTimeChanged(LocalDateTime.now()
                .toEpochSecond(ZoneOffset.systemDefault().getRules().getOffset(LocalDateTime.now())));
        postCommentRepository.saveAndFlush(postComment);
        if(postComment.getParentId()==null || postComment.getParentId().equals(0L)) {
            List<PostComment> postComments = postCommentRepository.findAll()
                    .stream().filter(p -> p.getParentId()!=null &&
                            p.getParentId().equals(postComment.getId()))
                    .collect(Collectors.toList());
            postComments.forEach(p -> {
                p.setIsDelete(true);
                p.setTimeChanged(LocalDateTime.now()
                        .toEpochSecond(ZoneOffset.systemDefault().getRules().getOffset(LocalDateTime.now())));
                postCommentRepository.saveAndFlush(p);
            });
        }
    }

    public CommentDto setPostCommentDto(List<PostComment> postComments) {
        CommentDto commentDto = new CommentDto();
        for (PostComment postComment : postComments) {
            commentDto.setCommentsCount(postComments.size());
            commentDto.setCommentText(postComment.getCommentText());
            commentDto.setAuthorId(postComment.getPerson().getId());
            commentDto.setPostId(postComment.getPost().getId());
            commentDto.setIsBlocked(postComment.getIsBlocked());
            commentDto.setIsDelete(postComment.getIsDelete());
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
        if(postComment.getParentId()!=null) {
            commentDto.setParentId(postComment.getParentId());
        } else {
            commentDto.setParentId(0L);
        }
        commentDto.setTime(postComment.getTime());
        return commentDto;
    }
}
