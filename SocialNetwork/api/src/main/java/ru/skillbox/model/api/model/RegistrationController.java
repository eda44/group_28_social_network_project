package ru.skillbox.model.api.model;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skillbox.model.api.request.RegistrationRequest;
import ru.skillbox.model.api.response.RegistrationResponse;

@RequestMapping("/api/v1/auth")
public interface RegistrationController {

    @PostMapping("/register")
    ResponseEntity<RegistrationResponse> registration(@RequestBody RegistrationRequest request);

    @GetMapping("/captcha")
    ResponseEntity<String> captcha();
}
