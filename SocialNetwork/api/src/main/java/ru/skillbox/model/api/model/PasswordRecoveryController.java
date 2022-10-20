package ru.skillbox.model.api.model;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.skillbox.model.api.request.PasswordRecoveryRequest;
import ru.skillbox.model.api.response.PasswordRecoveryResponse;

@RequestMapping("/api/v1/auth")
public interface PasswordRecoveryController {
    @PostMapping ("/password/recovery")
    ResponseEntity<PasswordRecoveryResponse> passwordRecovery(@RequestBody PasswordRecoveryRequest request);
}
