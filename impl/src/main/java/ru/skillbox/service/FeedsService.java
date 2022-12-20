package ru.skillbox.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skillbox.dto.enums.Type;
import ru.skillbox.mapper.PostCommentMapper;
import ru.skillbox.mapper.PostMapper;
import ru.skillbox.model.*;
import ru.skillbox.repository.*;
import ru.skillbox.request.FeedsRequest;
import ru.skillbox.response.*;
import ru.skillbox.specification.PostSpecification;
import ru.skillbox.specification.TagSpecification;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;
import java.util.stream.Collectors;

@Log4j2
@Service
public class FeedsService {
    private PostRepository postRepository;

    private PersonRepository personRepository;

    private PostCommentRepository postCommentRepository;

    private TagRepository tagRepository;

    private PersonService personService;

    private FriendsRepository friendsRepository;

       @Autowired
    public FeedsService(PostRepository postRepository,
                        PostCommentRepository postCommentRepository,
                        PersonService personService,
                        TagRepository tagRepository,
                        PersonRepository personRepository,
                        FriendsRepository friendsRepository) {
        this.postRepository = postRepository;
        this.personService = personService;
        this.postCommentRepository = postCommentRepository;
        this.tagRepository = tagRepository;
        this.personRepository = personRepository;
        this.friendsRepository = friendsRepository;
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


       @Transactional
    public ResponseEntity<FeedsResponseOK> getObjectResponseEntity(FeedsRequest feedsRequest,
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

           Specification<Post> postSpec = generatePostSpecification(feedsRequest);
           Page<Post> pagedPosts = postRepository.findAll(postSpec,feedsRequest.getPageable());
        log.debug("CurrentUserId={}",currentUserId);

        List<Post> posts = pagedPosts.getContent();
        log.debug("Find all posts from repository with given Criteria");
        List<PostDto> postDtoList = new ArrayList<>();
        if(posts==null ||posts.size() == 0){
            getFeedsError();
        } else {
            fillPostDtoList(currentUserId, posts, postDtoList);
        }
        FeedsResponseOK feedsResponse = getFeedsResponseOK(feedsRequest.getPageable(), pagedPosts, postDtoList);
        return ResponseEntity.ok(feedsResponse);
    }

    private Specification<Post> generatePostSpecification(FeedsRequest feedsRequest) {
        PostSpecification spec = new PostSpecification();
        return getPostSpecificationByDate(feedsRequest, spec)
                .and(spec.getPostsByIsDelete(feedsRequest.getIsDelete()))

                .and(getPostSpecificationByAccountId(feedsRequest, spec))
                .and(getPostSpecificationByFriends(feedsRequest,spec))
                .and(getPostSpecificationByText(feedsRequest, spec))
                .and(getPostSpecificationByTags(feedsRequest, spec))
                .and(getPostSpecificationByAuthor(feedsRequest, spec));
    }

    private static Specification<Post> getPostSpecificationByAccountId(FeedsRequest feedsRequest,
                                                                       PostSpecification spec) {
        Specification<Post> accountSpec = spec;
        if(feedsRequest.getAccountId()!=null) {
            accountSpec = accountSpec.and(spec.getPostsByPersonId(feedsRequest.getAccountId()));
        } else {
            accountSpec = accountSpec.and(spec.getPostsByType(Type.POSTED));
        }
        return accountSpec;
    }



    private Specification<Post> getPostSpecificationByFriends(FeedsRequest feedsRequest,
                                                                      PostSpecification spec) {
           Specification<Post> result = spec;
           if(feedsRequest.getWithFriends()!=null && feedsRequest.getWithFriends().equals(true)){
               List<Long> friendIds = getFriendIds(friendsRepository,personService);
               result = result.and(spec.getPostsByPersonIds(friendIds));
           }
           return result;
    }

//
//    private static List<Long> getFriendIds(PersonRepository personRepository) {
//           List<Long> ids = new ArrayList<>();
//           List<Person> people = personRepository.findAll();
//           for(Person person : people){
//               ids.add(person.getId());
//           }
//        return ids;
//    }

    private  List<Long> getFriendIds(FriendsRepository friendsRepository,PersonService personService) {
           long id = personService.getCurrentPerson().getId();
        Optional<List<Friendship>> setOptional = friendsRepository
                .findAllBySrcPersonIdOrDstPersonId(id,id);
        Set<Long> ids = new HashSet<>();
        ids.add(id);
        if(setOptional.isPresent()){
            List<Friendship> friendshipList = setOptional.get();
            for(Friendship friendship : friendshipList){
                ids.add(friendship.getDstPerson().getId());
                ids.add(friendship.getSrcPerson().getId());
            }
        }
        return ids.stream().collect(Collectors.toList());
    }

    private static Specification<Post> getPostSpecificationByAuthor(FeedsRequest feedsRequest, PostSpecification spec) {
        Specification<Post> authSpec = spec;
           if(feedsRequest.getAuthor()!=null) {
            String[] names = feedsRequest.getAuthor().split(" ");
            if(names.length > 1){
                authSpec = authSpec.or(spec.getPostsByTwoNames(names[0],names[1]))
                                    .or(spec.getPostsByTwoNames(names[1],names[0]));

            } else {
                authSpec = authSpec.or(spec.getPostsByFirstName(names[0]))
                                    .or(spec.getPostsByLastName(names[0]));
            }
        }
        return authSpec;
    }

    private Specification<Post> getPostSpecificationByTags(FeedsRequest feedsRequest, PostSpecification spec) {
        Specification<Post> tagsSpec = spec;
           if(feedsRequest.getTags()!=null){
            List<Tag> tags = new ArrayList<>();
            for(String tagString : feedsRequest.getTags()) {
                TagSpecification tagSpec = new TagSpecification();
                Specification<Tag> tagSpecification = tagSpec.getTagsByName(tagString);
                tags.addAll(tagRepository.findAll(tagSpecification));
            }
            if(!tags.isEmpty()){
                for(Tag tag : tags) {
                    tagsSpec = tagsSpec.or(spec.getPostsByTag(tag));
                }
            }
        }
        return tagsSpec;
    }

    private static Specification<Post> getPostSpecificationByText(FeedsRequest feedsRequest, PostSpecification spec) {
        Specification<Post> wordSpec = spec;
        if(feedsRequest.getWords()!=null){
            for(String word : feedsRequest.getWords()){
                wordSpec = wordSpec.or(spec.getPostsByTitleOrText(word));
            }

        }
        return wordSpec;
    }

    private static Specification<Post> getPostSpecificationByDate(FeedsRequest feedsRequest, PostSpecification spec) {
           Specification<Post> result = spec;
        if(feedsRequest.getDateTo()!=null){
            result = result.and(spec.getPostsByDateTo(feedsRequest.getDateTo()));
            log.debug("DateTo={}",feedsRequest.getDateTo());
        }
        if(feedsRequest.getDateFrom()!=null){
            result = result.and(spec.getPostsByDateFrom(feedsRequest.getDateFrom()));
            log.debug("DateFrom={}",feedsRequest.getDateFrom());
        }
        return result;
    }

    private void fillPostDtoList(long currentUserId, List<Post> posts, List<PostDto> postDtoList) {
        for(Post post : posts) {
            log.debug("Mapping post number " + post.getId());
            PostDto postDto = PostMapper.INSTANCE.postToPostDto(post, currentUserId);
            postDtoList.add(postDto);
        }
    }

    private static void getFeedsError() throws JsonProcessingException {
        FeedsResponseError feedsResponseError = new FeedsResponseError();
        feedsResponseError.setError("No posts!");
        feedsResponseError.setErrorDescription("No posts found. Please fill your database!");
        ObjectMapper mapper = new ObjectMapper();
        log.debug(mapper.writeValueAsString(feedsResponseError));
    }

    private static FeedsResponseOK getFeedsResponseOK(Pageable pageable, Page<Post> pagedPosts,
                                                      List<PostDto> postDtoList) throws JsonProcessingException {
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


    @Transactional
    public ResponseEntity<CommentResponse> getComments(long id, FeedsRequest feedsRequest, boolean isTest)
            throws JsonProcessingException {
        Post post = postRepository.findById(id).get();
        Page<PostComment> postCommentPage = postCommentRepository.findByPostAndIsDeleteAndParentId(
                post,false,0L,feedsRequest.getPageable());


        CommentResponse commentResponse = getCommentResponse(feedsRequest.getPageable(), id, postCommentPage, isTest);

        return ResponseEntity.ok(commentResponse);
    }

    private CommentResponse getCommentResponse(Pageable pageable, Long id, Page<PostComment> pages,
                                               boolean isTest)
            throws JsonProcessingException {
        log.debug("Generating comment response!");

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
        CommentResponse commentResponse = fillCommentResponse(pageable, pages, postCommentDtoList);
        ObjectMapper mapper = new ObjectMapper();
        log.debug(mapper.writeValueAsString(commentResponse));
        return commentResponse;
    }

    private static CommentResponse fillCommentResponse(Pageable pageable, Page<PostComment> pages,
                                                       List<PostCommentDto> postCommentDtoList) {
        CommentResponse commentResponse = new CommentResponse();
        commentResponse.setSize(pageable.getPageSize());
        commentResponse.setEmpty(pages.isEmpty());
        commentResponse.setSort(pages.getSort());
        commentResponse.setFirst(pages.isFirst());
        commentResponse.setLast(pages.isLast());
        commentResponse.setNumber(pages.getNumber());
        commentResponse.setPageable(pageable);
        commentResponse.setTotalElements(pages.getTotalElements());
        commentResponse.setNumberOfElements(pages.getNumberOfElements());
        commentResponse.setTotalPages(pages.getTotalPages());
        commentResponse.setContent(postCommentDtoList);
        return commentResponse;
    }

    @Transactional
    public ResponseEntity<CommentResponse> getSubComments(long id, long commentId, FeedsRequest feedsRequest,
    boolean isTest)
            throws JsonProcessingException {
        Pageable subCommentsPageable = PageRequest.of(0,
                postCommentRepository.findAll().size(),feedsRequest.getPageable().getSort());
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
