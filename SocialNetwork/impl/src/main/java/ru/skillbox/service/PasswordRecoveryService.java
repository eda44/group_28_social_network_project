package ru.skillbox.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.skillbox.exception.EmailNotFoundException;
import ru.skillbox.request.PasswordRecoveryRequest;
import ru.skillbox.response.PasswordRecoveryResponse;

import java.security.SecureRandom;

@Service
@RequiredArgsConstructor
public class PasswordRecoveryService {

    private final UserService userService;
    private final EmailService emailService;

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
