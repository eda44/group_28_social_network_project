package ru.skillbox.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skillbox.model.api.model.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
