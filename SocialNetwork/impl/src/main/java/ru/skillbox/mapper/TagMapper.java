package ru.skillbox.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.skillbox.dto.PostTagDto;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TagMapper implements RowMapper<PostTagDto> {
    @Override
    public PostTagDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        return PostTagDto.builder()
                .id(rs.getInt("id"))
                .tag(rs.getString("tag"))
                .build();
    }
}
