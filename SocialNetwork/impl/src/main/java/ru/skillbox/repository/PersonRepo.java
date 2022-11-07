package ru.skillbox.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.skillbox.model.Person;

import java.util.Optional;

@Repository
public interface PersonRepo extends JpaRepository<Person, Long>, JpaSpecificationExecutor<Person> {

    Person findById(long id);

    Person findByEmail(String email);

}
