package ru.skillbox.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skillbox.dto.enums.Type;
import ru.skillbox.mapper.PostCommentMapper;
import ru.skillbox.mapper.PostMapper;
import ru.skillbox.model.Post;
import ru.skillbox.model.PostComment;
import ru.skillbox.repository.PostCommentRepository;
import ru.skillbox.repository.PostRepository;
import ru.skillbox.response.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Log4j2
@Service
public class FeedsService {
    private PostRepository postRepository;

    private PostCommentRepository postCommentRepository;

    private PersonService personService;

       @Autowired
    public FeedsService(PostRepository postRepository,
                        PostCommentRepository postCommentRepository,
                        PersonService personService) {
        this.postRepository = postRepository;
        this.personService = personService;
        this.postCommentRepository = postCommentRepository;

    }

    public void updatePostType(){
       List<Post> posts = postRepository.findAll();
       for(Post post : posts){
           if(post.getType().equals(Type.QUEUED) && post.getTime() <= LocalDateTime.now()
                   .toEpochSecond(ZoneOffset.systemDefault().getRules().getOffset(LocalDateTime.now()))) {
               post.setType(Type.POSTED);
               postRepository.saveAndFlush(post);
           }
       }
    }


    public Page findByText(Pageable pageable,String text, boolean isTest){
        Pageable newPageable = PageRequest.of(0,postRepository.findAll().size(),pageable.getSort());
        Page<Post> pagedPosts = postRepository.findByIsDeleteAndType(false,Type.POSTED,newPageable);

        List<Post> posts = pagedPosts.getContent()
                .stream().filter(p -> near(p,text))
                .collect(Collectors.toList());
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), posts.size());
        Page<Post> page = null;
        if(start <= end ) {
            page = new PageImpl<>(posts.subList(start, end), pageable, posts.size());
        } else  {
            page = new PageImpl<>(posts.subList(
                    Math.max(start - pageable.getPageSize(),0),
                    end), pageable, posts.size());
        }
        if(posts.isEmpty()) {
            page = new PageImpl<>(posts, pageable, 0);
        }
        return page;
    }


    @Transactional
    public ResponseEntity<FeedsResponseOK> getObjectResponseEntity(Pageable pageable, String text,
                                                                   boolean isTest)
            throws JsonProcessingException {
        long currentUserId;
        if(isTest){
            currentUserId = 1;
            log.debug("Test mode!");
        } else {
            currentUserId = personService.getCurrentPerson().getId();
        }
        updatePostType();
        List<Post> posts;
        Page<Post> pagedPosts;
        log.debug("CurrentUserId={}",currentUserId);

        if(text==null) {
            pagedPosts = postRepository.findByIsDeleteAndType(false,Type.POSTED,pageable);
        } else {
            pagedPosts = findByText(pageable,text,false);
        }
        posts = pagedPosts.getContent();
        log.debug("Find all posts from repository");
        List<PostDto> postDtoList = new ArrayList<>();
        if(posts==null ||posts.size() == 0){
            getFeedsError();
        } else {
            fillPostDtoList(text, currentUserId, posts, postDtoList);
        }
        FeedsResponseOK feedsResponse = getFeedsResponseOK(pageable, pagedPosts, postDtoList);
        return ResponseEntity.ok(feedsResponse);
    }

    private void fillPostDtoList(String text, long currentUserId, List<Post> posts, List<PostDto> postDtoList) {
        for(Post post : posts) {
            if(text ==null || near(post, text)) {
                    log.debug("Mapping post number " + post.getId());
                    PostDto postDto = PostMapper.INSTANCE.postToPostDto(post, currentUserId);
                    postDtoList.add(postDto);
            }
        }
    }

    private static void getFeedsError() throws JsonProcessingException {
        FeedsResponseError feedsResponseError = new FeedsResponseError();
        feedsResponseError.setError("No posts!");
        feedsResponseError.setErrorDescription("No posts found. Please fill your database!");
        ObjectMapper mapper = new ObjectMapper();
        log.debug(mapper.writeValueAsString(feedsResponseError));
    }

    private static FeedsResponseOK getFeedsResponseOK(Pageable pageable, Page<Post> pagedPosts, List<PostDto> postDtoList) throws JsonProcessingException {
        FeedsResponseOK feedsResponse = new FeedsResponseOK();
        log.debug("Generating feeds response!");
        feedsResponse.setContent(postDtoList);
        feedsResponse.setSize(pageable.getPageSize());
        feedsResponse.setTotalElements(pagedPosts.getTotalElements());
        feedsResponse.setTotalPages(pagedPosts.getTotalPages());
        feedsResponse.setEmpty(pagedPosts.isEmpty());
        feedsResponse.setSort(pagedPosts.getSort());
        feedsResponse.setFirst(pagedPosts.isFirst());
        feedsResponse.setLast(pagedPosts.isLast());
        feedsResponse.setNumber(pagedPosts.getNumber());
        feedsResponse.setNumberOfElements(pagedPosts.getNumberOfElements());
        feedsResponse.setPageable(pageable);
        ObjectMapper mapper = new ObjectMapper();
        log.debug(mapper.writeValueAsString(feedsResponse));
        return feedsResponse;
    }

    private boolean near(Post post, String text) {
           String textOnlyBooks = text.toLowerCase(Locale.ROOT).replaceAll("[^А-яA-z\s]+","");
           String textWithoutDoubleSpaces = textOnlyBooks.replaceAll("[\s]{2,}","\s");
           log.debug("Only Books in String: " + textOnlyBooks);
           String[] textWords = textWithoutDoubleSpaces.trim().split("\s");
           log.debug("Words number: {}",textWords.length);
           for(String word : textWords){
               if(post.getTitle().toLowerCase(Locale.ROOT).contains(word) ||
               post.getPostText().toLowerCase(Locale.ROOT).contains(word)) {
                   return  true;
               }
           }
           return false;
    }


    public Pageable generatePageableObjectByServlet(HttpServletRequest httpServletRequest){
        String pageString = httpServletRequest.getParameter("page");
        int page = pageString!=null?Integer.parseInt(pageString):0;
        log.debug("page= " + page);
        String sizeString = httpServletRequest.getParameter("size");
        int size = sizeString!=null?Integer.parseInt(sizeString):1;
        log.debug("size= " + size);
        String sort = httpServletRequest.getParameter("sort");
        if(page < 0){
            page = 0;
        }
        Pageable pageable;
        if(sort!=null){
            String[] words = sort.split(",");
            if(words.length >= 2 && words[1].equals("desc")) {
                pageable = PageRequest.of(page, size, Sort.by(
                        Sort.Order.desc(words[0])
                        ));
            } else {
                pageable = PageRequest.of(page, size, Sort.by(
                        Sort.Order.asc(words[0])
                        ));
            }
        }
        else {
            pageable = PageRequest.of(page, size, Sort.by(
                    Sort.Order.asc("time")
            ));
        }
        return pageable;
    }

    @Transactional
    public ResponseEntity<CommentResponse> getComments(long id, Pageable pageable, boolean isTest)
            throws JsonProcessingException {
        Post post = postRepository.findById(id).get();
        Page<PostComment> postCommentPage = postCommentRepository.findByPostAndIsDeleteAndParentId(
                post,false,0L,pageable);


        CommentResponse commentResponse = getCommentResponse(pageable, id, postCommentPage, isTest);

        return ResponseEntity.ok(commentResponse);
    }

    private CommentResponse getCommentResponse(Pageable pageable, Long id, Page<PostComment> pages,
                                               boolean isTest)
            throws JsonProcessingException {
        log.debug("Generating comment response!");
        CommentResponse commentResponse = new CommentResponse();
        commentResponse.setSize(pageable.getPageSize());
        commentResponse.setEmpty(pages.isEmpty());
        commentResponse.setSort(pages.getSort());
        commentResponse.setFirst(pages.isFirst());
        commentResponse.setLast(pages.isLast());
        commentResponse.setNumber(pages.getNumber());
        commentResponse.setPageable(pageable);
        List<PostComment> comments =  pages.getContent();

        List<PostCommentDto> postCommentDtoList = new ArrayList<>();
        long currentUserId;
        if(isTest){
            currentUserId = 1;
            log.debug("Test mode!");
        } else {
            currentUserId = personService.getCurrentPerson().getId();
        }


        for(PostComment comment : comments) {
            log.debug("Mapping comment number " + comment.getId());
            PostCommentDto postCommentDtoDto = PostCommentMapper.
                    INSTANCE.postCommentToPostCommentDto(comment, currentUserId,postCommentRepository);
            postCommentDtoList.add(postCommentDtoDto);
        }


        commentResponse.setTotalElements(pages.getTotalElements());
        commentResponse.setNumberOfElements(pages.getNumberOfElements());
        commentResponse.setTotalPages(pages.getTotalPages());
        commentResponse.setContent(postCommentDtoList);

        ObjectMapper mapper = new ObjectMapper();
        log.debug(mapper.writeValueAsString(commentResponse));
        return commentResponse;
    }

    @Transactional
    public ResponseEntity<CommentResponse> getSubComments(long id, long commentId, Pageable pageable,
    boolean isTest)
            throws JsonProcessingException {
        Pageable subCommentsPageable = PageRequest.of(0,
                postCommentRepository.findAll().size(),pageable.getSort());
        Post post = postRepository.findById(id).get();
        Page<PostComment> postCommentPage = postCommentRepository.findByPostAndIsDeleteAndParentId(
                post,false,commentId,subCommentsPageable);

        CommentResponse commentResponse = getCommentResponse(subCommentsPageable, id, postCommentPage, isTest);

        return ResponseEntity.ok(commentResponse);
    }

    public String getText(HttpServletRequest httpServletRequest) {
         return  httpServletRequest.getParameter("text");
    }
}
