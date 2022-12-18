package ru.skillbox.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.skillbox.model.FriendsController;
import ru.skillbox.model.User;
import ru.skillbox.response.FriendshipResponse;
import ru.skillbox.response.MakeFriendResponse;
import ru.skillbox.service.FriendshipService;
import ru.skillbox.service.UserService;

import java.util.ArrayList;

@AllArgsConstructor
@RestController
@Slf4j
public class FriendsControllerImpl implements FriendsController {
    private FriendshipService friendshipService;
    private final UserService userService;

//    @Override
//    public FriendshipResponse getFriends(String name, Integer offset, Integer itemPerPage) {
//        String currentUser = userService.getCurrentUser().getEmail();
//        return friendshipService.searchFriends(currentUser, name, offset, itemPerPage);
//    }

    @Override
    public ResponseEntity<Page<FriendshipResponse>> getFriends() {
        ArrayList<FriendshipResponse> list = new ArrayList<>();
//        list.add(MyTest.builder()
//                .id(2L)
//                .photo("some photo url")
//                .statusCode("FRIEND")
//                .firstName("Second")
//                .lastName("Last2")
//                .city("City")
//                .country("Country")
//                .birthDate(1671180995432L)
//                .isOnline(true)
//                .build()
//        );
//        list.add(MyTest.builder()
//                .id(3L)
//                .photo("some photo url")
//                .statusCode("FRIEND")
//                .firstName("3")
//                .lastName("Last3")
//                .city("City")
//                .country("Country")
//                .birthDate(1671180995432L)
//                .isOnline(true)
//                .build()
//        );
//
//        list.add(MyTest.builder()
//                .id(4L)
//                .photo("some photo url")
//                .statusCode("FRIEND")
//                .firstName("4")
//                .lastName("Last4")
//                .city("City")
//                .country("Country")
//                .birthDate(1671180995432L)
//                .isOnline(true)
//                .build()
//        );

        Page<FriendshipResponse> page = new PageImpl<>(list);
        return ResponseEntity.ok(page);
    }

    @Override
    public MakeFriendResponse makeFriend(Long id) throws JsonProcessingException {

        User currentUser = userService.getCurrentUser();
        return friendshipService.makeFriend(currentUser.getId(), id);
    }

    @Override
    public MakeFriendResponse deleteFriend(Long id) throws JsonProcessingException {
        User currentUser = userService.getCurrentUser();
        return friendshipService.deleteFriend(currentUser, id);
    }

    @Override
    public MakeFriendResponse subscribe(@PathVariable Long id) throws JsonProcessingException {
        User currentUser = userService.getCurrentUser();
        return friendshipService.subscribe(currentUser, id);
    }

    @Override
    public MakeFriendResponse block(Long id) throws JsonProcessingException {
        User currentUser = userService.getCurrentUser();
        return friendshipService.blockFriend(currentUser, id);
    }

    @Override
    public FriendshipResponse recommendations(Integer offset, Integer itemPerPage) {
//        User currentUser = userService.getCurrentUser();
//        return friendshipService.searchRecommendations(currentUser, offset, itemPerPage);
        return null;
    }
}
