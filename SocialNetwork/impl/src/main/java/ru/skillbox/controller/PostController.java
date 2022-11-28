package ru.skillbox.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skillbox.dto.PhotoDto;
import ru.skillbox.dto.PostSearchDto;
import ru.skillbox.exception.UserNotFoundException;
import ru.skillbox.model.PostFile;
import ru.skillbox.request.PostAddRequest;
import ru.skillbox.response.post.PagePostDto;
import ru.skillbox.response.post.PostResponse;
import ru.skillbox.service.PostService;

@RestController
@RequestMapping("/api/v1/post")
public class PostController {

    private final PostService postService;

    private static final Logger logger = LogManager.getLogger(PostController.class);

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public void addNewPost(@RequestBody PostAddRequest request) throws UserNotFoundException {
        postService.setPost(request);
        logger.info("saving post, post text = " + request.getPostText());
    }

    @RequestMapping(value = "/storagePostPhoto", method = RequestMethod.POST,
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<PhotoDto> uploadFile(@RequestParam("file") MultipartFile file) {
        PhotoDto photoDto = new PhotoDto();
        PostFile postFile = postService.uploadImage(file);
        photoDto.setImagePath(postFile.getPath());
        logger.info("file uploaded");
        return ResponseEntity.ok(photoDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePostById(@PathVariable String id) {
        postService.deletePost(postService.getPostById(Long.parseLong(id)));
        logger.info("deleting post");
        return ResponseEntity.ok("ok");
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponse> getPostById(@PathVariable String id) {
        logger.info("getting post by id");
        PostResponse response = postService.setPostResponse(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> putPostById(@RequestBody PostAddRequest request, @PathVariable String id) {
        postService.updatePost(request, id);
        logger.info("updating post");
        return ResponseEntity.ok("ok");
    }


    //@GetMapping
    public ResponseEntity<PagePostDto> getPostsAll(@RequestParam PostSearchDto searchDto,
                                                   @RequestParam(name = "page", defaultValue = "0") int page,
                                                   @RequestParam(name = "size", defaultValue = "1") int size,
                                                   @RequestParam(name = "sort", defaultValue = "time") String[] sort) {


        return postService.getPostsAll(searchDto, page, size, sort);

    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

//    @GetMapping("/api/v1/post/wall")
//    public ResponseEntity<WallResponse> getPostWall() {
//        WallResponse response = new WallResponse();
//
//        response.setPage(1);
//        response.setTotal(1);
//        response.setSize(20);
//        response.setTimestamp(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));
//        return ResponseEntity.ok(response);
//    }
//
//    @PostMapping("/api/v1/post/{id}/report")
//    public ResponseEntity<PostComplaintResponse> addNewComplaint(@PathVariable String id) {
//        Post post = postService.getPostById(Long.parseLong(id));
//        PostComplaintResponse response = new PostComplaintResponse();
//        response.setErrorDescription("error");
//        response.setTimestamp(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));
//        ComplaintResponse complaintResponse = new ComplaintResponse();
//        complaintResponse.setMessage("message");
//        response.setData(List.of(complaintResponse));
//        return ResponseEntity.ok(response);
//    }
//
//
//    @PutMapping("/api/v1/post/{id}/recover")
//    public ResponseEntity<PostResponse> recoveryPostById(@PathVariable String id) {
//        PostResponse response = new PostResponse();
//        Post post = postService.getPostById(Long.parseLong(id));
//        postService.savePost(post);
//        return ResponseEntity.ok(response);
//    }

}