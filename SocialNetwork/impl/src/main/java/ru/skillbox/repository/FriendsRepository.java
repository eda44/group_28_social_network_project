package ru.skillbox.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skillbox.model.Friendship;

public interface FriendsRepository extends JpaRepository<Friendship, Long> {

}
