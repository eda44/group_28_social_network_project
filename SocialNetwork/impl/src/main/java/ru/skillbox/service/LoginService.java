package ru.skillbox.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.skillbox.exception.InvalidCredentialsException;
import ru.skillbox.model.User;
import ru.skillbox.request.LoginRequest;
import ru.skillbox.response.LoginResponse;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserService userService;

    public LoginResponse login(LoginRequest request) throws InvalidCredentialsException {
        if (!userService.passwordCheck(request)) {
            throw new InvalidCredentialsException("Invalid credentials");
        }
        User user = userService.loadUserByUsername(request.getEmail());
        return LoginResponse.getOkResponse(user);
    }
}
