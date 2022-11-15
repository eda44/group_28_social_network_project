package ru.skillbox.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.skillbox.dto.enums.LikeType;
import ru.skillbox.model.Post;
import ru.skillbox.model.PostComment;
import ru.skillbox.model.PostLike;
import ru.skillbox.repository.PostLikeRepository;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostLikeService {

    private final PostLikeRepository postLikeRepository;
    private final PostService postService;
    private final PersonService personService;
    private final PostCommentService postCommentService;

    @Autowired
    public PostLikeService(PostLikeRepository postLikeRepository, PostService postService,
                           PersonService personService, PostCommentService postCommentService) {
        this.postLikeRepository = postLikeRepository;
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

    public void deleteLike(PostLike postLike) {
        postLikeRepository.delete(postLike);
    }

    public void saveLike(PostLike postLike) {
        postLikeRepository.save(postLike);
    }

    public List<PostLike> getAllLikes() {
        return postLikeRepository.findAll();
    }

    public PostLike setPostLike() {
        PostLike postLike = new PostLike();
        postLike.setTime(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));
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
}