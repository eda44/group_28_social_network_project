package ru.skillbox.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skillbox.model.PostLike;

@Repository
public interface PostLikeRepository extends JpaRepository<PostLike, Long> {
}
