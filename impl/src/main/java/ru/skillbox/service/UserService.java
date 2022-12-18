package ru.skillbox.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.skillbox.exception.UserIsAlreadyRegisteredException;
import ru.skillbox.model.User;
import ru.skillbox.request.RegistrationRequest;
import ru.skillbox.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final PersonService personService;

    public void saveUser(RegistrationRequest request) throws UserIsAlreadyRegisteredException {
        User userFromDB = userRepository.findByEmail(request.getEmail());
        if (userFromDB != null) {
            throw new UserIsAlreadyRegisteredException("User is already registered");
        }
        registrationUser(request);
        User user = userRepository.findByEmail(request.getEmail());
        personService.registrationPerson(user, request);
    }

    private void registrationUser(RegistrationRequest request) {
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(request.getPassword1()));
        user.setAccountNonLocked(true);
        user.setEnabled(true);
        userRepository.save(user);
    }

    public boolean passwordCheck(User user, String password) {
        return bCryptPasswordEncoder.matches(password, user.getPassword());
    }

    @Override
    public User loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    public User getUserByEmail(String email) {
        return loadUserByUsername(email);
    }

    public void setNewPassword(String email, String password) {
        User user = userRepository.findByEmail(email);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        userRepository.save(user);
    }

    public User getCurrentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        return loadUserByUsername(email);
    }
}
