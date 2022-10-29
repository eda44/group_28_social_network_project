package ru.skillbox.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.skillbox.exception.UserIsAlreadyRegisteredException;
import ru.skillbox.request.RegistrationRequest;
import ru.skillbox.response.RegistrationResponse;

@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final UserService userService;

    public RegistrationResponse registration(RegistrationRequest request) throws UserIsAlreadyRegisteredException {
        if (!userService.saveUser(request)) {
            throw new UserIsAlreadyRegisteredException("User is already registered");
        }
        return RegistrationResponse.getOkResponse();
    }
}
