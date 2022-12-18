package ru.skillbox.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.skillbox.dto.enums.FriendshipCode;
import ru.skillbox.model.FriendshipStatus;

@Repository
public interface FriendshipStatusRepository extends JpaRepository<FriendshipStatus, Integer>, JpaSpecificationExecutor<FriendshipStatus> {

    FriendshipStatus findByCode(FriendshipCode code);
}
