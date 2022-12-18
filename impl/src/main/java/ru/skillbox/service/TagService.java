package ru.skillbox.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.skillbox.dto.PostTagDto;
import ru.skillbox.model.Tag;
import ru.skillbox.repository.TagRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagService {

    private final TagRepository tagRepository;

    @Autowired
    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public void deleteTagById(long id) {
        tagRepository.deleteById(id);
    }

    public Tag findTagById(long id) {
        return tagRepository.findById(id).get();
    }

    public void saveTag(Tag tag) {
        tagRepository.save(tag);
    }

    public List<Tag> getAllTags() {
        return tagRepository.findAll();
    }

    public List<Tag> convertDtoToTag(PostTagDto[] postTagDto) {
        List<Tag> tagList = new ArrayList<>();
        for (PostTagDto tagDto : postTagDto) {
            Tag tag = new Tag();
            tag.setTag(tagDto.getTag());
            tagList.add(tag);
        }
        return tagList;
    }
}
