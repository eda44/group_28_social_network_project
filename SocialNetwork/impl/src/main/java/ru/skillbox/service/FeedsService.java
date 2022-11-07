package ru.skillbox.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.skillbox.controller.FeedsController;
import ru.skillbox.dto.PostDto;
import ru.skillbox.mapper.PostMapper;
import ru.skillbox.model.Post;
import ru.skillbox.repository.PostRepository;
import ru.skillbox.response.FeedsResponseError;
import ru.skillbox.response.FeedsResponseOK;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class FeedsService {



    private PostRepository postRepository;

    private AccountService accountService;

    private Logger logger = FeedsController.getLogger();

    @Autowired
    public FeedsService(PostRepository postRepository, AccountService accountService) {
        this.postRepository = postRepository;
        this.accountService = accountService;
    }

    public ResponseEntity<Object> getObjectResponseEntity(int page, int size, String[] sort, int offset, int limit) throws JsonProcessingException {
        long currentUserId = getUserId();

        logger.info("Detecting currentUserId");
        ResponseEntity<Object> feedsError = generateFeedsResponseError(currentUserId);

        if (feedsError != null) {
            ObjectMapper mapper = new ObjectMapper();
            logger.info(mapper.writeValueAsString(feedsError));
            return feedsError;
        }

        Pageable pageable = PageRequest.of(page,size, Sort.by(sort).descending());
        Page<Post> postsPage = postRepository.findAll(pageable);
        List<Post> posts = postsPage.getContent();
        logger.info("Find all posts from repository");

        if(posts==null || posts.size() == 0){
            FeedsResponseError feedsResponseError = new FeedsResponseError();
            feedsResponseError.setError("No posts!");
            feedsResponseError.setErrorDescription("No posts found. Please fill your database!");
            ObjectMapper mapper = new ObjectMapper();
            logger.info(mapper.writeValueAsString(feedsResponseError));
            return ResponseEntity.ok(feedsResponseError);
        }

        List<PostDto> postDtoList = new ArrayList<>();

        for(Post post : posts) {
            logger.info("Mapping post number " + post.getId());
            PostDto postDto = PostMapper.INSTANCE.postToPostDto(post, currentUserId);
            postDtoList.add(postDto);
        }
            FeedsResponseOK feedsResponse = new FeedsResponseOK();
            logger.info("Generating feeds response!");
            feedsResponse.setTimeStamp((new Date()).getTime());
            feedsResponse.setPage(page);
            feedsResponse.setSize(size);
            feedsResponse.setTotal(sort.length);
            feedsResponse.setData(postDtoList);
            ObjectMapper mapper = new ObjectMapper();
            logger.info(mapper.writeValueAsString(feedsResponse));
            return ResponseEntity.ok(feedsResponse);
    }

    private  ResponseEntity<Object> generateFeedsResponseError(long currentUserId) throws JsonProcessingException {
        if(currentUserId ==-1L){
            FeedsResponseError feedsResponseError = new FeedsResponseError();
            feedsResponseError.setError("Not authorized");
            feedsResponseError.setErrorDescription("Please authorize in system, then continue!");
            ObjectMapper mapper = new ObjectMapper();
            logger.info(mapper.writeValueAsString(feedsResponseError));
            return ResponseEntity.ok(feedsResponseError);
        }
        return null;
    }

    private long getUserId() {
        long currentUserId;
        try {
            currentUserId = getCurrentUserId();
        } catch (IOException|ParseException exception) {
            exception.printStackTrace();
            currentUserId = -1L;
        }
        return currentUserId;
    }

    private  Long getCurrentUserId() throws IOException, ParseException {
       String currentPerson = accountService.getCurrentPerson();

        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(currentPerson);
        Object jsonData = jsonObject.get("data");
        if(jsonData!=null) {
            JSONObject idString = (JSONObject) parser.parse(jsonData.toString());
            Object jsonGetId = idString.get("id");
            Long id = Long.parseLong(jsonGetId.toString());
            return id;
        } else {
            throw new IOException( "User is not authorized!");
        }
    }
}
