package ru.skillbox.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.skillbox.exception.EmailNotFoundException;
import ru.skillbox.exception.UserIsAlreadyRegisteredException;
import ru.skillbox.model.User;
import ru.skillbox.request.LoginRequest;
import ru.skillbox.request.PasswordRecoveryRequest;
import ru.skillbox.request.RegistrationRequest;
import ru.skillbox.response.LoginResponse;
import ru.skillbox.response.PasswordRecoveryResponse;
import ru.skillbox.response.RegistrationResponse;

import java.security.SecureRandom;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserService userService;
    private final EmailService emailService;

    private final PersonService personService;

    public LoginResponse login(LoginRequest request) throws UsernameNotFoundException {
        User user = userService.getUserByEmail(request.getEmail());
        if (!userService.passwordCheck(user, request.getPassword())) {
            throw new UsernameNotFoundException("User not found");
        }
        return LoginResponse.getOkResponse(personService.getPersonByEmail(request));

    }


    public PasswordRecoveryResponse passwordRecovery(PasswordRecoveryRequest request) throws EmailNotFoundException {
        String email = request.getEmail();
        if (userService.getUserByEmail(email) == null) {
            throw new EmailNotFoundException("Email not found");
        }
        String password = generateRandomPassword(8);
        userService.setNewPassword(email, password);
        emailService.passwordRecoveryMessage(email, password);
        return PasswordRecoveryResponse.getOkResponse();
    }

    public RegistrationResponse registration(RegistrationRequest request) throws UserIsAlreadyRegisteredException {
        if (!userService.saveUser(request)) {
            throw new UserIsAlreadyRegisteredException("User is already registered");
        }
        return RegistrationResponse.getOkResponse();
    }

    private String generateRandomPassword(int len) {
        final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            int randomIndex = random.nextInt(chars.length());
            sb.append(chars.charAt(randomIndex));
        }
        return sb.toString();
    }
}
