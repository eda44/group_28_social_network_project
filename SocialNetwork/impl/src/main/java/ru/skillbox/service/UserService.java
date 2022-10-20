package ru.skillbox.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.skillbox.exception.EmailNotFoundException;
import ru.skillbox.exception.InvalidCredentialsException;
import ru.skillbox.exception.UserIsAlreadyRegisteredException;
import ru.skillbox.model.User;
import ru.skillbox.model.api.request.LoginRequest;
import ru.skillbox.model.api.request.PasswordRecoveryRequest;
import ru.skillbox.model.api.request.RegistrationRequest;
import ru.skillbox.model.api.response.LoginResponse;
import ru.skillbox.model.api.response.PasswordRecoveryResponse;
import ru.skillbox.model.api.response.RegistrationResponse;
import ru.skillbox.repository.UserRepo;

import java.security.SecureRandom;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepo userRepo;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final EmailService emailService;

    public LoginResponse login(LoginRequest request) throws InvalidCredentialsException {
        if (!passwordCheck(request)) {
            throw new InvalidCredentialsException("Invalid credentials");
        }
        User user = loadUserByUsername(request.getEmail());
        return LoginResponse.getOkResponse(user);
    }

    public RegistrationResponse registration(RegistrationRequest request) throws UserIsAlreadyRegisteredException {
        if (!saveUser(request)) {
            throw new UserIsAlreadyRegisteredException("User is already registered");
        }
        return RegistrationResponse.getOkResponse();
    }

    public PasswordRecoveryResponse passwordRecovery(PasswordRecoveryRequest request) throws EmailNotFoundException {
        String email = request.getEmail();
        if (getUserByEmail(email) == null) {
            throw new EmailNotFoundException("Email not found");
        }
        String password = generateRandomPassword(8);
        setNewPassword(email, password);
        emailService.passwordRecoveryMessage(email, password);
        return PasswordRecoveryResponse.getOkResponse();
    }

    private boolean saveUser(RegistrationRequest request) {
        User userFromDB = userRepo.findByEmail(request.getEmail());
        if (userFromDB != null) {
            return false;
        }
        User newUser = new User();
        newUser.setEmail(request.getEmail());
        newUser.setPassword(bCryptPasswordEncoder.encode(request.getPasswd1()));
        newUser.setFirstName(request.getFirstName());
        newUser.setLastName(request.getLastName());
        newUser.setRegDate(new Date().getTime());
        userRepo.save(newUser);
        return true;
    }

    private boolean passwordCheck(LoginRequest request) {
        User userFromDB = getUserByEmail(request.getEmail());
        return userFromDB != null && bCryptPasswordEncoder.matches(request.getPassword(), userFromDB.getPassword());
    }

    @Override
    public User loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    private User getUserByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    private void setNewPassword(String email, String password) {
        User user = userRepo.findByEmail(email);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        userRepo.save(user);
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
