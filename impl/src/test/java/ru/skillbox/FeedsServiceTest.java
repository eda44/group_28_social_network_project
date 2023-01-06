package ru.skillbox;

import com.fasterxml.jackson.databind.ObjectMapper;
import junit.framework.TestCase;
import lombok.NoArgsConstructor;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.skillbox.dto.enums.Type;
import ru.skillbox.model.Person;
import ru.skillbox.model.Post;
import ru.skillbox.model.PostComment;
import ru.skillbox.repository.PersonRepository;
import ru.skillbox.repository.PostCommentRepository;
import ru.skillbox.repository.PostRepository;
import ru.skillbox.repository.TagRepository;
import ru.skillbox.request.FeedsRequest;
import ru.skillbox.response.CommentResponse;
import ru.skillbox.response.FeedsResponseOK;
import ru.skillbox.service.FeedsService;
import ru.skillbox.service.UserService;

import java.util.Date;
import java.util.stream.Collectors;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-integration.properties")
@NoArgsConstructor
public class FeedsServiceTest extends TestCase {

    @Value("{${isTest}}")
    private String isTestString;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostCommentRepository postCommentRepository;

    @Autowired
    private FeedsService feedsService;

    @Autowired
    private UserService userService;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private TagRepository tagRepository;

    @Before
    @Override
    public void setUp() {

        Person person = new Person();

        person.setFirstName("T");
        person.setLastName("L");
        person.setIsEnabled(true);

        personRepository.saveAndFlush(person);

        Post post = new Post();
        post.setTime((new Date()).getTime());
        post.setPerson(person);
        post.setIsBlocked(true);
        post.setIsDelete(false);
        post.setType(Type.POSTED);
        post.setPostText("SomeText");
        post.setPostCommentList(null);
        post.setTitle("SomeTitle");
        post.setIsBlocked(true);
        post.setIsDelete(false);

        postRepository.saveAndFlush(post);

        PostComment postComment = new PostComment();
        postComment.setParentId(0L);
        postComment.setPerson(person);
        postComment.setCommentType("POST");
        postComment.setCommentText("Text of comment");
        postComment.setPost(post);
        postComment.setIsBlocked(false);
        postComment.setIsDelete(false);

        PostComment saved = postCommentRepository.saveAndFlush(postComment);

        PostComment subComment = new PostComment();
        subComment.setParentId(saved.getId());
        subComment.setPerson(person);
        subComment.setCommentType("POST");
        subComment.setCommentText("Text of subcomment");
        subComment.setPost(post);
        subComment.setIsDelete(false);
        subComment.setIsBlocked(false);

        postCommentRepository.saveAndFlush(subComment);

    }

    @After
    @Override
    public void tearDown() {
        postCommentRepository.deleteAll();
        postCommentRepository.flush();
        postRepository.deleteAll();
        postRepository.flush();
        personRepository.deleteAll();
        personRepository.flush();
    }


    @Test
    public void testGetObjectResponseEntity() throws Exception {
            Long personId = personRepository.findAll().get(0).getId();
            Long postId = postRepository.findAll().get(0).getId();
            String timeString = AdditionalFunctions.getTimeString();
            Pageable pageable = PageRequest.of(0, 5, Sort.by("time").descending());
            FeedsRequest feedsRequest = new FeedsRequest(pageable);
            ResponseEntity<FeedsResponseOK> response = feedsService.getObjectResponseEntity(feedsRequest,
                    isTestString.equals("{true}"));
            FeedsResponseOK responseBody = AdditionalFunctions.correctContent(response.getBody(),timeString);

            JSONParser parser = new JSONParser();
            ObjectMapper mapper = new ObjectMapper();
            JSONObject jsonObjectActual = (JSONObject) parser.parse(mapper.writeValueAsString(responseBody));
            String expectedString = AdditionalFunctions.generateExpectedResponseString(personId, postId, timeString);
            JSONObject jsonObjectExpected = (JSONObject) parser.parse(expectedString);
            assertEquals(jsonObjectExpected, jsonObjectActual);
    }

    @Test
    public void testGetComments() throws Exception {
        Long personId = personRepository.findAll().get(0).getId();
        Long postId = postRepository.findAll().get(0).getId();
        Long postCommentId = postCommentRepository.findAll().stream()
                .filter(postComment -> postComment.getParentId()==0)
                .collect(Collectors.toList()).get(0).getId();
        String timeString = AdditionalFunctions.getTimeString();
        Pageable pageable = PageRequest.of(0, 5, Sort.by("time").descending());
        FeedsRequest feedsRequest = new FeedsRequest(pageable);
        ResponseEntity<CommentResponse> response = feedsService
                .getComments(postId,feedsRequest,isTestString.equals("{true}"));
        CommentResponse responseBody = AdditionalFunctions.correctCommentContent(response.getBody(),timeString);
        JSONParser parser = new JSONParser();
        ObjectMapper mapper = new ObjectMapper();
        JSONObject jsonObjectActual = (JSONObject) parser.parse(mapper.writeValueAsString(responseBody));
        String expectedString = AdditionalFunctions.generateExpectedCommentString(personId,postId,postCommentId,timeString);
        JSONObject jsonObjectExpected = (JSONObject) parser.parse(expectedString);
        assertEquals(jsonObjectExpected, jsonObjectActual);
    }

    @Test
    public void testGetSubComments() throws Exception {
        Long personId = personRepository.findAll().get(0).getId();
        Long postId = postRepository.findAll().get(0).getId();
        Long postCommentId = postCommentRepository.findAll().stream()
                .filter(postComment -> postComment.getParentId()==0)
                .collect(Collectors.toList()).get(0).getId();
        String timeString = AdditionalFunctions.getTimeString();
        Pageable pageable = PageRequest.of(0, 5, Sort.by("time").descending());
        Long subCommentId = postCommentRepository.findAll().stream()
                .filter(postComment -> postComment.getParentId()!=0)
                .collect(Collectors.toList()).get(0).getId();
        FeedsRequest feedsRequest = new FeedsRequest(pageable);
        ResponseEntity<CommentResponse> response = feedsService.getSubComments(postId,postCommentId,feedsRequest,
                isTestString.equals("{true}"));
        CommentResponse responseBody = AdditionalFunctions.correctCommentContent(response.getBody(),timeString);
        JSONParser parser = new JSONParser();
        ObjectMapper mapper = new ObjectMapper();
        JSONObject jsonObjectActual = (JSONObject) parser.parse(mapper.writeValueAsString(responseBody));

        String expectedString = AdditionalFunctions.generateExpectedSubCommentString(subCommentId,
                timeString,personId,postCommentId,postId);
        JSONObject jsonObjectExpected = (JSONObject) parser.parse(expectedString);
        assertEquals(jsonObjectExpected, jsonObjectActual);
    }
}
