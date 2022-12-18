package ru.skillbox.service;

import org.springframework.stereotype.Service;
import ru.skillbox.dto.enums.FriendshipCode;
import ru.skillbox.model.FriendshipStatus;
import ru.skillbox.repository.FriendshipStatusRepository;

@Service
public class FriendshipStatusService {
    private final FriendshipStatusRepository friendshipStatusRepository;

    public FriendshipStatusService(FriendshipStatusRepository friendshipStatusRepository) {
        this.friendshipStatusRepository = friendshipStatusRepository;
    }

    public FriendshipStatus getFriendshipStatus(FriendshipCode code) {
        return friendshipStatusRepository.findByCode(code);
    }
}
