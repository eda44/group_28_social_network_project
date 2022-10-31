package ru.skillbox.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.skillbox.model.Post;
import ru.skillbox.model.PostFile;
import ru.skillbox.repository.PostRepository;

import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
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

    public PostFile setPostFile(Post post, String name, String path) {
        PostFile postFile = new PostFile();
        postFile.setPost(post);
        postFile.setName(name);
        postFile.setPath(path);
        return postFile;
    }

    public void deletePost(Post post) {
        postRepository.delete(post);
    }
}
