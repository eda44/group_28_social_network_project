package ru.skillbox.model;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.skillbox.request.LoginRequest;
import ru.skillbox.request.PasswordRecoveryRequest;
import ru.skillbox.request.RegistrationRequest;
import ru.skillbox.response.LoginResponse;
import ru.skillbox.response.PasswordRecoveryResponse;
import ru.skillbox.response.RegistrationResponse;

@RequestMapping("/api/v1/auth")
public interface AuthController {
    @PostMapping("/login")
    ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request);

    @PostMapping("/logout")
    String logout();

    @PostMapping("/register")
    ResponseEntity<RegistrationResponse> registration(@RequestBody RegistrationRequest request);

    @GetMapping("/captcha")
    ResponseEntity<String> captcha();

    @PostMapping("/password/recovery")
    ResponseEntity<PasswordRecoveryResponse> passwordRecovery(@RequestBody PasswordRecoveryRequest request);
}
