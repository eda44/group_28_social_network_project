package ru.skillbox.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.skillbox.dto.Friendship;
import ru.skillbox.dto.FriendshipStatus;
import ru.skillbox.repository.FriendsRepository;

@Service
@AllArgsConstructor
public class FriendsService {
    private final FriendsRepository friendsRepository;
}
