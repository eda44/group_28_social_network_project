package ru.skillbox.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skillbox.exception.UserIsAlreadyRegisteredException;
import ru.skillbox.model.RegistrationController;
import ru.skillbox.request.RegistrationRequest;
import ru.skillbox.response.RegistrationResponse;
import ru.skillbox.service.RegistrationService;

@RestController
@RequiredArgsConstructor
public class RegistrationControllerImpl implements RegistrationController {
    private final RegistrationService registrationService;

    @Override
    public ResponseEntity<RegistrationResponse> registration(RegistrationRequest request) {
        try {
            return ResponseEntity.ok(registrationService.registration(request));
        } catch (UserIsAlreadyRegisteredException e) {
            return ResponseEntity.badRequest().body(RegistrationResponse.getBadResponse());
        }
    }

    @Override
    public ResponseEntity<String> captcha() {
        return ResponseEntity.ok("{\n" +
                "        \"secret\": \"aGl0YQ==\",\n" +
                "            \"image\": \"data:image/png;base64...\"\n" +
                "    }");

    }
}
