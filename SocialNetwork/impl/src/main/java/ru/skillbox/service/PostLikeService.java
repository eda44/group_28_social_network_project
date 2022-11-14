package ru.skillbox.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.skillbox.dto.enums.LikeType;
import ru.skillbox.model.Post;
import ru.skillbox.model.PostComment;
import ru.skillbox.model.PostLike;
import ru.skillbox.repository.PersonRepository;
import ru.skillbox.repository.PostCommentRepository;
import ru.skillbox.repository.PostLikeRepository;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostLikeService {

    private final PostLikeRepository postLikeRepository;
    private final PostService postService;
    private final PersonRepository personRepository;
    private final PostCommentRepository postCommentRepository;

    @Autowired
    public PostLikeService(PostLikeRepository postLikeRepository, PostService postService, PersonRepository personRepository, PostCommentRepository postCommentRepository) {
        this.postLikeRepository = postLikeRepository;
        this.postService = postService;
        this.personRepository = personRepository;
        this.postCommentRepository = postCommentRepository;
    }

    public void deleteLike(long id) {
        postLikeRepository.deleteById(id);
    }

    public Post getLikeByPostId(long id) {
        return postLikeRepository.findById(id).get().getPost();
    }

    public void getLikeByCommentId(long id) {
        postLikeRepository.findById(id);
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
                PostComment postComment = postCommentRepository.findById(id).get();
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