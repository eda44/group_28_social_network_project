package ru.skillbox.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.skillbox.config.CloudinaryConfig;
import ru.skillbox.model.FeedsInterface;
import ru.skillbox.repository.CountryRepository;
import ru.skillbox.repository.FriendsRepository;
import ru.skillbox.repository.PersonRepository;
import ru.skillbox.repository.PostRepository;
import ru.skillbox.request.FeedsRequest;
import ru.skillbox.response.CommentResponse;
import ru.skillbox.response.FeedsResponseOK;
import ru.skillbox.service.FeedsService;
import ru.skillbox.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Log4j2
@RestController
public class FeedsControllerImpl implements FeedsInterface {


    private final FeedsService feedsService;

    private PostRepository postRepository;

    private CountryRepository countryRepository;

    private FriendsRepository friendsRepository;

    private PersonRepository personRepository;

    private CloudinaryConfig config;

    private UserService userService;

    @Value("{${isTest}}")
    private String isTestString;


    @Autowired
    public FeedsControllerImpl(FeedsService feedsService, PostRepository postRepository,
                               CountryRepository countryRepository, FriendsRepository friendsRepository,
                               UserService userService,
                               CloudinaryConfig config,
                               PersonRepository personRepository) {
        this.postRepository = postRepository;
        this.userService = userService;
        this.feedsService = feedsService;
        this.countryRepository = countryRepository;
        this.friendsRepository = friendsRepository;
        this.config = config;
        this.personRepository = personRepository;
    }

    @Override
    @GetMapping("/api/v1/post")
    public ResponseEntity<FeedsResponseOK> getFeedsSearch(HttpServletRequest httpServletRequest

    )
            throws  IOException {

        FeedsRequest feedsRequest = new FeedsRequest(httpServletRequest);
        Pageable pageable = feedsRequest.getPageable();
        String text = feedsService.getText(httpServletRequest);

        return feedsService.getObjectResponseEntity(feedsRequest, isTestString.equals("{true}"));
    }

    @GetMapping("/api/v1/post/{id}/comment")
    public ResponseEntity<CommentResponse> getAllCommentsToPost(@PathVariable long id,
                                                                HttpServletRequest httpServletRequest)
            throws JsonProcessingException {
        FeedsRequest feedsRequest = new FeedsRequest(httpServletRequest);
        Pageable pageable = feedsRequest.getPageable();
        return  feedsService.getComments(id,feedsRequest,isTestString.equals("{true}"));
    }

    @GetMapping("/api/v1/post/{id}/comment/{commentId}/subcomment")
    public ResponseEntity<CommentResponse> getAllSubComments(@PathVariable long id, @PathVariable long commentId,
                                                    HttpServletRequest httpServletRequest)
            throws JsonProcessingException {
        FeedsRequest feedsRequest = new FeedsRequest(httpServletRequest);
        Pageable pageable = feedsRequest.getPageable();
        return feedsService.getSubComments(id,commentId,feedsRequest, isTestString.equals("{true}"));
    }
}
