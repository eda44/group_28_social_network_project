package ru.skillbox.model;

import org.json.simple.parser.ParseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import ru.skillbox.response.FeedsResponseOK;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;

public interface FeedsInterface {
    @GetMapping("/api/v1/post")
    public ResponseEntity<FeedsResponseOK> getFeedsSearch(
            HttpServletRequest httpServletRequest

    )
            throws SQLException, IOException, ParseException;


}
