package ru.skillbox.dao;

import ru.skillbox.models.Person;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonMapper implements RowMapper<Person> {
    @Override
    public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Person(rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("lastname"));
    }
}
