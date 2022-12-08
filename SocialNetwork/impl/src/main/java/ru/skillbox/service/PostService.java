package ru.skillbox.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skillbox.config.CloudinaryConfig;
import ru.skillbox.dto.enums.Type;
import ru.skillbox.model.Post;
import ru.skillbox.model.PostFile;
import ru.skillbox.model.Tag;
import ru.skillbox.repository.PostRepository;
import ru.skillbox.request.PostAddRequest;
import ru.skillbox.response.post.PostResponse;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final PostFileService postFileService;
    private final PersonService personService;
    private final CloudinaryConfig cloudinaryConfig;
    private static final Logger logger = LogManager.getLogger(PostService.class);

    @Autowired
    public PostService(PostRepository postRepository, PostFileService postFileService,
                       PersonService personService, CloudinaryConfig cloudinaryConfig) {
        this.postRepository = postRepository;
        this.postFileService = postFileService;
        this.personService = personService;
        this.cloudinaryConfig = cloudinaryConfig;
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post getPostById(long id) {
        return postRepository.findById(id).get();
    }

    public void savePost(Post post) {
        postRepository.save(post);
    }

    public List<Tag> convertStringToTag(List<String> tagsFromRequest) {
        List<Tag> tagList = new ArrayList<>();
        for (String tagString : tagsFromRequest) {
            Tag tag = new Tag();
            tag.setTag(tagString);
            tagList.add(tag);
        }
        return tagList;
    }

    public List<String> convertTagToString(List<Tag> tagList) {
        List<String> tags = new ArrayList<>();
        for (Tag tag : tagList) {
            tags.add(tag.getTag());
        }
        return tags;
    }

    public void deletePost(Post post) {
        postRepository.delete(post);
        logger.info("deleting post № " + post.getId());
    }

    public void addPost(PostAddRequest request) {
        Post post = new Post();
        post.setTitle(request.getTitle());
        post.setPostText(request.getPostText());
        post.setTags(convertStringToTag(request.getTags()));
        post.setIsBlocked(request.getIsBlocked());
        post.setPerson(personService.getCurrentPerson());
        Optional<PostFile> postFile = postFileService.getPostFileByPath(request.getImagePath());
        postFile.ifPresent(file -> post.setPostFiles(List.of(file)));

        if (request.getPublishDate() != null) {
            post.setType(Type.QUEUED);
        } else {
            post.setType(Type.POSTED);
        }
        switch (post.getType()) {
            case POSTED:
                post.setTime(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));
                break;
            case QUEUED:
                post.setTime(request.getPublishDate());
        }
        savePost(post);
        logger.info("saving post № " + post.getId());
    }

    public PostFile uploadImage(MultipartFile multipartFile) {
        logger.info("uploading file " + multipartFile.getOriginalFilename());
        return postFileService.savePostFile(cloudinaryConfig.uploadImage(multipartFile));
    }

    public PostResponse addPostResponse(String id) {
        Post post = getPostById(Long.parseLong(id));
        PostResponse response = new PostResponse();
        response.setPostText(post.getPostText());
        response.setId(post.getId());
        response.setTime(post.getTime());
        response.setTitle(post.getTitle());
        response.setIsBlocked(post.getIsBlocked());
        response.setAuthorId(post.getPerson().getId());
        response.setType(post.getType());
        response.setCommentsCount(post.getPostCommentList().size());
        response.setTags(convertTagToString(post.getTags()));
        return response;
    }

    public void updatePost(PostAddRequest request, String id) {
        Post post = getPostById(Long.parseLong(id));
        post.setTitle(request.getTitle());
        post.setTime(request.getTime());
        post.setTimeChanged((new Date()).getTime());
        post.setPostText(request.getPostText());
        post.setPerson(personService.getCurrentPerson());
        post.setIsDelete(request.getIsDelete());
        post.setIsBlocked(request.getIsBlocked());
        post.setType(request.getType());
        post.setIsBlocked(request.getIsBlocked());
        post.setTags(convertStringToTag(request.getTags()));
        post.setTime(request.getTime());
        Optional<PostFile> postFile = postFileService.getPostFileByPath(request.getImagePath());
        postFile.ifPresent(file -> post.setPostFiles(new ArrayList<>(List.of(file))));
        savePost(post);
        logger.info("updating post № " + post.getId());
    }
}
