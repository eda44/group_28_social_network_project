package ru.skillbox.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skillbox.model.Post;
import ru.skillbox.model.User;

import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}
