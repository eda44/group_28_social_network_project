package ru.skillbox.model;

import org.json.simple.parser.ParseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.sql.SQLException;

public interface FeedsInterface {
    @GetMapping("/api/v1/feeds")
    public ResponseEntity<Object> getFeedsSearch(@RequestParam(name = "page", defaultValue = "0")  int page,
                                                 @RequestParam(name = "size", defaultValue = "1") int size,
                                                 @RequestParam(name = "sort", defaultValue = "time") String[] sort,
                                                 @RequestParam(name = "offset", defaultValue = "0") int offset,
                                                 @RequestParam(name = "limit", defaultValue = "20") int limit) throws SQLException, IOException, ParseException;
}
