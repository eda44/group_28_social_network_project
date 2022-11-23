package ru.skillbox.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.skillbox.model.PostFile;
import ru.skillbox.repository.PostFileRepository;

@Service
public class PostFileService {

    private final PostFileRepository postFileRepository;

    @Autowired
    public PostFileService(PostFileRepository postFileRepository) {
        this.postFileRepository = postFileRepository;
    }

    public void savePostFile(PostFile postFile) {
        postFileRepository.save(postFile);
    }

    public PostFile getPostFileById(Long id) {
        return postFileRepository.findById(id).get();
    }
}
