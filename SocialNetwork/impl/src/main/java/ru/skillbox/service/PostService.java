package ru.skillbox.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.skillbox.model.Post;
import ru.skillbox.request.PostAddRequest;
import ru.skillbox.response.PostAddResponse;
import ru.skillbox.repository.PostRepository;

import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    //    public PostAddRequest addPostRq(String title, String[] tags, String text, String photo) {
//        PostAddRequest addRequest = new PostAddRequest();
//        addRequest.setTitle(title);
//        addRequest.setTags(tags);
//        addRequest.setPostText(text);
//        addRequest.setPhoto_url(photo);
//        return addRequest;
//    }
    public PostAddResponse postAddRs(PostAddRequest request) {
        PostAddResponse response = new PostAddResponse();
        if (response.getStatus()) {
            Post post = new Post();
            post.setPostText(request.getPostText());
            post.setTags(request.getTags());
            post.setTitle(request.getTitle());
            postRepository.save(post);
            return response;
        }
        response.setStatus(false);
        return response;
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }
}
