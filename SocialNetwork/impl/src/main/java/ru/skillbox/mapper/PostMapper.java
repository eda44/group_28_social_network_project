package ru.skillbox.mapper;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import ru.skillbox.dto.*;
import ru.skillbox.model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Mapper
public interface PostMapper {


    PostMapper INSTANCE = Mappers.getMapper( PostMapper.class );


    @Mapping(source = "person", target = "author")
    @Mapping(source = "postFiles", target = "photoUrl", qualifiedByName = "mapPhoto")
    @Mapping(source = "postLikes", target = "likes", qualifiedByName = "mapLikes")
    @Mapping(source = "postLikes", target = "myLike", qualifiedByName = "mapMyLike")
    @Mapping(source = "postCommentList", target = "comments", qualifiedByName = "mapComments")
    PostDto postToPostDto(Post post, @Context Long currentUser);
    CountryDto countryToCountryDto(Country country);
    @Mapping(target = "isOnline")
    @Mapping(target = "isBlocked")

    AccountDto personToAccountDto(Person person);



    @Mapping(source = "post", target = "postId", qualifiedByName = "getPostId")
    @Mapping(source = "commentLikes", target = "likes", qualifiedByName = "mapCommentLikes")
    @Mapping(source = "person", target = "author")
    PostCommentDto commentToPostCommentDto(PostComment postComment);

    @Mapping(target = "person")
    LikeDto postLikeToLikeDto(PostLike postLike);

    @Mapping(target = "person")
    LikeDto commentLikeToLikeDto(CommentLike commentLike);


    @Mapping(source = "country", target = "countryId", qualifiedByName = "mapCountry")
    CityDto cityToCityDto(City city);
    
    @Named("mapLikes")
    default Integer mapLikes(List<PostLike> likes) {
        if(likes==null) {
            return 0;
        }
        return likes.size();
    }

    @Named("mapPhoto")
    default String mapPhoto(List<PostFile> postFiles) {
        if(postFiles == null) {
            return null;
        }
        return postFiles.get(0).getPath();
    }

    @Named("mapMyLike")
    default Boolean mapMyLike(List<PostLike> likes, @Context Long currentUser){
        if(likes==null) {
            return null;
        }
        List<LikeDto> likeDtoList = new ArrayList<>();
        likes.forEach(l -> likeDtoList.add(new LikeDto(l.getPerson())));
        boolean isMyLike = getMyLike(likeDtoList, currentUser);
        return isMyLike;
    }

    private static boolean getMyLike(List<LikeDto> likes, Long currentUser) {
           boolean isMyLike = false;
        for(LikeDto like : likes){
            if(like.getPerson().getId() == currentUser){
                isMyLike = true;
                break;
            }
        }
        return isMyLike;
    }


    @Named("mapCountry")
    default Long mapCountry(Country country) {
        if ( country == null ) {
            return null;
        }
        return country.getId();
    }

    @Named("mapComments")
    default PostCommentDto[] findComments(List<PostComment> comments, @Context Long currentUser){
        Map<Long, PostCommentDto> postCommentDtoMap = new HashMap<>();
        List<String> subCommentList = new ArrayList<>();
        for(PostComment comment : comments){
            if(comment.getParentId() == 0L) {
                PostCommentDto postCommentDto = commentToPostCommentDto(comment);
                List<LikeDto> likeDtoList = new ArrayList<>();
                comment.getCommentLikes().forEach(l -> likeDtoList.add(new LikeDto(l.getPerson())));
                boolean isMyLike = getMyLike(likeDtoList, currentUser);
                postCommentDto.setMyLike(isMyLike);
                postCommentDtoMap.put(comment.getId(),postCommentDto);

            } else {
                PostCommentDto postCommentDto = postCommentDtoMap.get(comment.getParentId());
                String subComment = comment.getCommentText();
                subCommentList.add(subComment);
                String[] subComments = new String[subCommentList.size()];
                subCommentList.toArray(subComments);
                postCommentDto.setSubComments(subComments);
            }
        }
        return postCommentDtoMap.values().toArray(new PostCommentDto[postCommentDtoMap.size()]);
    }




    @Named("getPostId")
    default Long getPostId(Post post){
        return post.getId();
    }

    @Named("mapCommentLikes")
    default Integer mapCommentLikes(List<CommentLike> commentLikes){
        return  commentLikes.size();
    }
}