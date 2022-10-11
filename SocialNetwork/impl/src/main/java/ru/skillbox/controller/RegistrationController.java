package ru.skillbox.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skillbox.model.api.request.RegistrationRequest;
import ru.skillbox.model.api.response.RegistrationResponse;
import ru.skillbox.service.UserService;

@RestController
public class RegistrationController {
    @Autowired
    private UserService userService;
    @PostMapping("/api/v1/auth/register")
    public ResponseEntity<RegistrationResponse> registration(@RequestBody RegistrationRequest reg) {
        if(userService.saveUser(reg)){
            return ResponseEntity.ok(new RegistrationResponse());
        }
        return ResponseEntity.badRequest().body(new RegistrationResponse());
    }
}
