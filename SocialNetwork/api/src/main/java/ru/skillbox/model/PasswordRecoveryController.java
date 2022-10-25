package ru.skillbox.model;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.skillbox.request.PasswordRecoveryRequest;
import ru.skillbox.response.PasswordRecoveryResponse;

@RequestMapping("/api/v1/auth")
public interface PasswordRecoveryController {
    @PostMapping ("/password/recovery")
    ResponseEntity<PasswordRecoveryResponse> passwordRecovery(@RequestBody PasswordRecoveryRequest request);
}
