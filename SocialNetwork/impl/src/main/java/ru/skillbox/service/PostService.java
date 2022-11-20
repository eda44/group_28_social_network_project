package ru.skillbox.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skillbox.dto.enums.Type;
import ru.skillbox.exception.UserNotFoundException;
import ru.skillbox.model.Post;
import ru.skillbox.model.PostFile;
import ru.skillbox.model.Tag;
import ru.skillbox.repository.PostRepository;
import ru.skillbox.request.PostAddRequest;
import ru.skillbox.response.post.PostResponse;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class PostService {


    private final PostRepository postRepository;
    private final PostFileService postFileService;
    private final PersonService personService;

    @Autowired
    public PostService(PostRepository postRepository, PostFileService postFileService, PersonService personService) {
        this.postRepository = postRepository;
        this.postFileService = postFileService;
        this.personService = personService;
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
    }


    public void setPost(PostAddRequest request) {
        Post post = new Post();

        post.setTitle(request.getTitle());
        post.setPostText(request.getPostText());
        post.setTags(convertStringToTag(request.getTags()));
        post.setIsBlocked(request.getIsBlocked());
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
    }

    public PostResponse setPostResponse(String id) {
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

    public void uploadImage(MultipartFile file) {
        Cloudinary cloudinary = PostService.getCloudinary();
        Map upload = null;
        try {
            upload = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        PostFile postFile = new PostFile();
        String url = (String) upload.get("url");
        postFile.setPath(url);
        postFile.setName(file.getOriginalFilename());
        postFileService.savePostFile(postFile);
    }

    public static Cloudinary getCloudinary() {
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "diie0ma4r",
                "api_key", "952226954255419",
                "api_secret", "0fbUzyBNOPBcfhDyik77QetyjrQ",
                "secure", true));
    }

    public static Map getParams() {
        return ObjectUtils.asMap(
                "use_filename", true,
                "unique_filename", false,
                "overwrite", true
        );
    }

    public PostFile setPostFile(Post post, String name, String path) {
        PostFile postFile = new PostFile();
        postFile.setPost(post);
        postFile.setName(name);
        postFile.setPath(path);
        return postFile;
    }

    public void updatePost(PostAddRequest request, String id) {
        Post post = getPostById(Long.parseLong(id));
        post.setTitle(request.getTitle());
        post.setPostText(request.getPostText());
        try {
            post.setPerson(personService.getPersonById(request.getAuthorId()));
        } catch (UserNotFoundException e) {
            throw new RuntimeException(e);
        }
        post.setType(request.getType());
        post.setIsBlocked(request.getIsBlocked());
        post.setTags(convertStringToTag(request.getTags()));
        post.setTime(request.getTime());
        post.setPostFiles(List.of(setPostFile(post, request.getTitle(), request.getImagePath())));
        savePost(post);
    }
}
