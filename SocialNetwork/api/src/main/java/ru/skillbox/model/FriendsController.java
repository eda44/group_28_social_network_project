package ru.skillbox.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skillbox.response.FriendshipResponse;
import ru.skillbox.response.MakeFriendResponse;

@CrossOrigin
@RequestMapping("/api/v1/friends")
//@PreAuthorize("hasAuthority('developers:read')")
public interface FriendsController {

    @GetMapping
    ResponseEntity<Page<FriendshipResponse>> getFriends();

    @PostMapping(value = "/{id}/request")
    MakeFriendResponse makeFriend(@PathVariable Long id) throws JsonProcessingException;

    @DeleteMapping(value = "/{id}")
    MakeFriendResponse deleteFriend(@PathVariable Long id) throws JsonProcessingException;

    @PostMapping("/subscribe/{id}")
    MakeFriendResponse subscribe(@PathVariable Long id) throws JsonProcessingException;

    @PutMapping("/block/{id}")
    MakeFriendResponse block(@PathVariable Long id) throws JsonProcessingException;

    @GetMapping("/recommendations")
    FriendshipResponse recommendations(@RequestParam(defaultValue = "0") Integer offset,
                                       @RequestParam(defaultValue = "20") Integer itemPerPage);

//    @GetMapping("/count")
//    FriendshipResponse count(@PathVariable Long id) throws JsonProcessingException;
}
