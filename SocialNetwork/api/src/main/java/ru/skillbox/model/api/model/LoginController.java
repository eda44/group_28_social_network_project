package ru.skillbox.model.api.model;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.skillbox.model.api.request.LoginRequest;
import ru.skillbox.model.api.response.LoginResponse;

@RequestMapping("/api/v1/auth")
public interface LoginController {

    @PostMapping("/login")
    ResponseEntity<String> login(@RequestBody LoginRequest request);

    @PostMapping("/logout")
    String logout();
}
