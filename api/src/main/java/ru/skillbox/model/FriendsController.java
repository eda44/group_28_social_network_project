package ru.skillbox.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skillbox.common.SearchPersonDto;
import ru.skillbox.response.FriendshipResponse;
import ru.skillbox.response.MakeFriendResponse;
import ru.skillbox.response.data.PersonDto;

import java.util.List;

@CrossOrigin
@RequestMapping("/api/v1/friends")
//@PreAuthorize("hasAuthority('developers:read')")
public interface FriendsController {

    @GetMapping
    ResponseEntity<Page<PersonDto>> getRelationships(SearchPersonDto dto);

    @PostMapping(value = "/{id}/request")
    ResponseEntity<String> sendFriendRequest(@PathVariable Long id);

    @PutMapping(value = "/{id}/approve")
    ResponseEntity<String> approveFriendRequest(@PathVariable Long id);

    @DeleteMapping(value = "/{id}")
    ResponseEntity<String> deleteFriend(@PathVariable Long id);

    @PostMapping("/subscribe/{id}")
    ResponseEntity<String> subscribe(@PathVariable Long id) ;

    @PutMapping("/block/{id}")
    MakeFriendResponse block(@PathVariable Long id) throws JsonProcessingException;

    @GetMapping("/recommendations")
    FriendshipResponse recommendations(@RequestParam(defaultValue = "0") Integer offset,
                                       @RequestParam(defaultValue = "20") Integer itemPerPage);

//    @GetMapping("/{accountId}")
//    List<Long>

    @GetMapping("/count")
    int count();
}
