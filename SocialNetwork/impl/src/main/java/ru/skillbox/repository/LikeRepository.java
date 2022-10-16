package ru.skillbox.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skillbox.model.Like;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
}
