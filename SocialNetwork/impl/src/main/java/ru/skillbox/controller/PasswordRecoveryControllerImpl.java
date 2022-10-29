package ru.skillbox.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.skillbox.exception.EmailNotFoundException;
import ru.skillbox.model.PasswordRecoveryController;
import ru.skillbox.request.PasswordRecoveryRequest;
import ru.skillbox.response.PasswordRecoveryResponse;
import ru.skillbox.service.PasswordRecoveryService;


@RestController
@RequiredArgsConstructor
public class PasswordRecoveryControllerImpl implements PasswordRecoveryController {

    private final PasswordRecoveryService passwordRecoveryService;

    @Override
    public ResponseEntity<PasswordRecoveryResponse> passwordRecovery(PasswordRecoveryRequest request) {
        try {
            return ResponseEntity.ok(passwordRecoveryService.passwordRecovery(request));
        } catch (EmailNotFoundException e) {
            return ResponseEntity.badRequest().body(PasswordRecoveryResponse.getBadResponse());
        }
    }
}
