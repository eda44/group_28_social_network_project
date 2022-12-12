package ru.skillbox.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skillbox.dto.PhotoDto;
import ru.skillbox.model.PostFile;
import ru.skillbox.request.PostAddRequest;
import ru.skillbox.response.post.PostResponse;
import ru.skillbox.service.PostService;

@RestController
@RequestMapping("/api/v1/post")
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public void addNewPost(@RequestBody PostAddRequest request) {
        postService.addPost(request);
    }

    @RequestMapping(value = "/storagePostPhoto", method = RequestMethod.POST,
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<PhotoDto> uploadFile(@RequestParam("file") MultipartFile file) {
        PhotoDto photoDto = new PhotoDto();
        PostFile postFile = postService.uploadImage(file);
        photoDto.setImagePath(postFile.getPath());
        return ResponseEntity.ok(photoDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePostById(@PathVariable String id) {
        postService.deletePost(postService.getPostById(Long.parseLong(id)));
        return ResponseEntity.ok("ok");
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponse> getPostById(@PathVariable String id) {
        PostResponse response = postService.addPostResponse(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> putPostById(@RequestBody PostAddRequest request, @PathVariable String id) {
        postService.updatePost(request, id);
        return ResponseEntity.ok("ok");
    }
}