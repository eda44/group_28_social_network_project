package ru.skillbox.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Value;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skillbox.response.PostCommentDto;
import ru.skillbox.response.PostDto;
import ru.skillbox.mapper.PostCommentMapper;
import ru.skillbox.mapper.PostMapper;
import ru.skillbox.model.Post;
import ru.skillbox.model.PostComment;
import ru.skillbox.repository.PostRepository;
import ru.skillbox.response.CommentResponse;
import ru.skillbox.response.FeedsResponseError;
import ru.skillbox.response.FeedsResponseOK;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;





@Log4j2
@Service
public class FeedsService {
    //TODO: Добавить логирование комментариев
    private PostRepository postRepository;

    private UserService userService;

       @Autowired
    public FeedsService(PostRepository postRepository,
                        UserService userService) {
        this.postRepository = postRepository;
        this.userService = userService;

    }

    @Transactional
    public ResponseEntity<FeedsResponseOK> getObjectResponseEntity(Pageable pageable, boolean isTest)
            throws JsonProcessingException {
           long currentUserId;

        if(isTest){
            currentUserId = 1;
            log.info("Test mode!");
        } else {
            currentUserId = userService.getCurrentUser().getId();
        }

        log.info("CurrentUserId={}",currentUserId);


        Page<Post> postsPage = postRepository.findAll(pageable);
        List<Post> posts = postsPage.getContent();
        log.info("Find all posts from repository");


        if(posts==null || posts.size() == 0){
            FeedsResponseError feedsResponseError = new FeedsResponseError();
            feedsResponseError.setError("No posts!");
            feedsResponseError.setErrorDescription("No posts found. Please fill your database!");
            ObjectMapper mapper = new ObjectMapper();
            log.info(mapper.writeValueAsString(feedsResponseError));
            return null;
        }

        List<PostDto> postDtoList = new ArrayList<>();

        for(Post post : posts) {
            log.info("Mapping post number " + post.getId());
            PostDto postDto = PostMapper.INSTANCE.postToPostDto(post, currentUserId);
            postDtoList.add(postDto);
        }

            FeedsResponseOK feedsResponse = new FeedsResponseOK();
            log.info("Generating feeds response!");
            feedsResponse.setContent(postDtoList);
            feedsResponse.setSize(pageable.getPageSize());
            feedsResponse.setTotalElements(postsPage.getTotalElements());
            feedsResponse.setTotalPages(postsPage.getTotalPages());
            feedsResponse.setEmpty(postsPage.isEmpty());
            feedsResponse.setSort(postsPage.getSort());
            feedsResponse.setFirst(postsPage.isFirst());
            feedsResponse.setLast(postsPage.isLast());
            feedsResponse.setNumber(postsPage.getNumber());
            feedsResponse.setNumberOfElements(postsPage.getNumberOfElements());
            feedsResponse.setPageable(pageable);
            ObjectMapper mapper = new ObjectMapper();
            log.info(mapper.writeValueAsString(feedsResponse));
            return ResponseEntity.ok(feedsResponse);
    }


    public Pageable generatePageableObjectByServlet(HttpServletRequest httpServletRequest){
        String pageString = httpServletRequest.getParameter("page");
        int page = pageString!=null?Integer.parseInt(pageString):0;
        log.info("page= " + page);
        String sizeString = httpServletRequest.getParameter("size");
        int size = sizeString!=null?Integer.parseInt(sizeString):1;
        log.info("size= " + size);
        String sort = httpServletRequest.getParameter("sort");
        if(page < 0){
            page = 0;
        }
        String[] words = sort.split(",");

        Pageable pageable;
        if(words[1].equals("desc")) {
            pageable = PageRequest.of(page, size, Sort.by(words[0]).descending());
        } else {
            pageable = PageRequest.of(page, size, Sort.by(words[0]).ascending());
        }
        return pageable;
    }

    @Transactional
    public ResponseEntity<CommentResponse> getComments(long id, Pageable pageable, boolean isTest)
            throws JsonProcessingException {
        List<PostComment> postCommentList = postRepository.findById(id).get().getPostCommentList();
        List<PostComment> noSubCommentList= postCommentList.stream()
                .filter(postComment -> postComment.getParentId()==0)
                .collect(Collectors.toList());
        Page<PostComment> pages = new PageImpl<>(noSubCommentList, pageable, noSubCommentList.size());

        CommentResponse commentResponse = getCommentResponse(pageable, pages, isTest);

        return ResponseEntity.ok(commentResponse);
    }

    private CommentResponse getCommentResponse(Pageable pageable, Page<PostComment> pages, boolean isTest)
            throws JsonProcessingException {
        log.info("Generating comment response!");
        CommentResponse commentResponse = new CommentResponse();
        commentResponse.setSize(pageable.getPageSize());
        commentResponse.setTotalElements(pages.getTotalElements());
        commentResponse.setTotalPages(pages.getTotalPages());
        commentResponse.setEmpty(pages.isEmpty());
        commentResponse.setSort(pages.getSort());
        commentResponse.setFirst(pages.isFirst());
        commentResponse.setLast(pages.isLast());
        commentResponse.setNumber(pages.getNumber());
        commentResponse.setNumberOfElements(pages.getNumberOfElements());
        commentResponse.setPageable(pageable);

        List<PostComment> comments = pages.getContent();
        List<PostCommentDto> postCommentDtoList = new ArrayList<>();
        long currentUserId;
        if(isTest){
            currentUserId = 1;
            log.info("Test mode!");
        } else {
            currentUserId = userService.getCurrentUser().getId();
        }


        for(PostComment comment : comments) {
            log.info("Mapping comment number " + comment.getId());
            PostCommentDto postCommentDtoDto = PostCommentMapper.
                    INSTANCE.postCommentToPostCommentDto(comment, currentUserId);
            postCommentDtoList.add(postCommentDtoDto);
        }
        commentResponse.setContent(postCommentDtoList);
        ObjectMapper mapper = new ObjectMapper();
        log.info(mapper.writeValueAsString(commentResponse));
        return commentResponse;
    }

    @Transactional
    public ResponseEntity<CommentResponse> getSubComments(long id, long commentId, Pageable pageable,
    boolean isTest)
            throws JsonProcessingException {

        List<PostComment> postCommentList = postRepository.findById(id).get().getPostCommentList();
        PostComment postComment = postCommentList.stream().filter(p -> p.getId().equals(commentId)).findFirst().get();

        List<PostComment> subCommentList = postComment.getPost().getPostCommentList()
                .stream().filter(p ->
                p.getParentId().equals(postComment.getId())
        ).collect(Collectors.toList());

        Page<PostComment> pages = new PageImpl<>(subCommentList, pageable, subCommentList.size());
        CommentResponse commentResponse = getCommentResponse(pageable, pages,isTest);

        return ResponseEntity.ok(commentResponse);
    }
}
