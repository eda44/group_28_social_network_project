package ru.skillbox.model;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skillbox.request.RegistrationRequest;
import ru.skillbox.response.RegistrationResponse;

@RequestMapping("/api/v1/auth")
public interface RegistrationController {

    @PostMapping("/register")
    ResponseEntity<RegistrationResponse> registration(@RequestBody RegistrationRequest request);

    @GetMapping("/captcha")
    ResponseEntity<String> captcha();
}
