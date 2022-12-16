package ru.skillbox.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.skillbox.exception.EmailNotFoundException;
import ru.skillbox.exception.UserIsAlreadyRegisteredException;
import ru.skillbox.jwt.JwtTokenProvider;
import ru.skillbox.model.User;
import ru.skillbox.request.LoginRequest;
import ru.skillbox.request.PasswordRecoveryRequest;
import ru.skillbox.request.RegistrationRequest;
import ru.skillbox.response.LoginResponse;
import ru.skillbox.response.PasswordRecoveryResponse;
import ru.skillbox.response.RegistrationResponse;

import java.security.SecureRandom;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserService userService;
    private final EmailService emailService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    public LoginResponse login(LoginRequest request) throws UsernameNotFoundException {
        User user = userService.getUserByEmail(request.getEmail());
        if (!userService.passwordCheck(user, request.getPassword())) {
            throw new UsernameNotFoundException("User not found");
        }
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_USER");//TODO: справить
        Authentication authenticationToken = new UsernamePasswordAuthenticationToken(user.getEmail(), null, List.of(authority));
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authentication);
        String jwt = jwtTokenProvider.createToken(authentication.getName(), "ROLE_USER");
        return LoginResponse.getOkResponse(jwt);
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
        userService.saveUser(request);
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
