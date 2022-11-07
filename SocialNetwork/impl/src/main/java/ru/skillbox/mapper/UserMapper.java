package ru.skillbox.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.skillbox.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new User(rs.getLong("id"),
                rs.getString("email"),
                rs.getString("first_name"),
                rs.getString("last_name"),
                rs.getLong("reg_date")
        );
    }
}
