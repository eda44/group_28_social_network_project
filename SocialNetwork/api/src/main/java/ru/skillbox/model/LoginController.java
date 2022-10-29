package ru.skillbox.model;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.skillbox.request.LoginRequest;
import ru.skillbox.response.LoginResponse;

@RequestMapping("/api/v1/auth")
public interface LoginController {

    @PostMapping("/login")
    ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request);

    @PostMapping("/logout")
    String logout();
}
