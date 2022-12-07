package ru.skillbox.mapper;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import ru.skillbox.model.*;
import ru.skillbox.repository.PostCommentRepository;
import ru.skillbox.response.PostCommentDto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface PostCommentMapper {
    PostCommentMapper INSTANCE = Mappers.getMapper(PostCommentMapper.class);


    @Mapping(target = "time", qualifiedByName = "mapTime")
    @Mapping(source = "person", target = "authorId", qualifiedByName = "mapAuthorId")
    @Mapping(source = "id", target = "commentsCount", qualifiedByName = "mapCommentsCount")
    @Mapping(source = "commentFiles", target = "imagePath", qualifiedByName = "mapImagePath")
    @Mapping(source = "commentLikes", target = "likeAmount", qualifiedByName = "mapLikeAmount")
    @Mapping(source = "commentLikes", target = "myLike", qualifiedByName = "mapMyLike")
    @Mapping(source = "post", target = "postId", qualifiedByName = "mapPostId")
    @Mapping(source = "isDelete", target = "isDelete")
    PostCommentDto postCommentToPostCommentDto(PostComment comment, @Context long currentUserId,
                                               @Context PostCommentRepository postCommentRepository);


    @Named("mapTime")
    default String mapTime(Long time) {

        LocalDateTime localDateTime = LocalDateTime.now();

        return localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"));
    }


    @Named("mapAuthorId")
    default Long mapAuthorId(Person person) {
        return person.getId();
    }

    @Named("mapCommentsCount")
    default Integer mapCommentsCount(Long id, @Context PostCommentRepository postCommentRepository) {
        PostComment postComment = postCommentRepository.findById(id).get();
        List<PostComment> postCommentList = postComment.getPost().getPostCommentList();

        List<PostComment> filteredPostCommentList = postCommentList.stream().filter(p ->
                    p.getParentId()!=null &&p.getParentId().equals(postComment.getId())
                && p.getIsDelete().equals(false)
        ).collect(Collectors.toList());
        return filteredPostCommentList.size();

    }

    @Named("mapImagePath")
    default String mapImagePath(List<CommentFile> commentFiles) {
        if (commentFiles != null && !commentFiles.isEmpty()) {
            return commentFiles.get(0).getPath();
        }
        return null;
    }

    @Named("mapLikeAmount")
    default Integer mapLikeAmount(List<CommentLike> likes) {
        if (likes == null || likes.isEmpty()) {
            return 0;
        }
        return likes.size();
    }

    @Named("mapMyLike")
    default Boolean mapMyLike(List<CommentLike> likes, @Context Long currentUser) {
        if (likes == null) {
            return null;
        }
        for (CommentLike like : likes) {
            if (like.getPerson().getId().equals(currentUser)) {
                return true;
            }
        }
        return false;
    }

    @Named("mapPostId")
    default Long mapPostId(Post post) {
        return post.getId();
    }


}


