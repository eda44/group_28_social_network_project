package ru.skillbox.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.skillbox.mappers.PersonMapper;
import ru.skillbox.model.Person;

import java.util.List;

@Component
public class PersonRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> getAllUsers() {
        return jdbcTemplate.query("SELECT * FROM People", new PersonMapper());
    }
}

