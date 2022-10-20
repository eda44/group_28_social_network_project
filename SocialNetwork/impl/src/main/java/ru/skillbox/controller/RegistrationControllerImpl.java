package ru.skillbox.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skillbox.exception.UserIsAlreadyRegisteredException;
import ru.skillbox.model.api.model.RegistrationController;
import ru.skillbox.model.api.request.RegistrationRequest;
import ru.skillbox.model.api.response.RegistrationResponse;
import ru.skillbox.service.UserService;

@RestController
@RequiredArgsConstructor
public class RegistrationControllerImpl implements RegistrationController {
    private final UserService userService;

    @Override
    public ResponseEntity<RegistrationResponse> registration(RegistrationRequest request) {
        try {
            return ResponseEntity.ok(userService.registration(request));
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
