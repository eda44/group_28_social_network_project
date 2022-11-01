package ru.skillbox.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.skillbox.model.User;
import ru.skillbox.request.RegistrationRequest;
import ru.skillbox.repository.UserRepo;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepo userRepo;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final PersonService personService;

    public boolean saveUser(RegistrationRequest request) {
        User userFromDB = userRepo.findByEmail(request.getEmail());
        if (userFromDB != null) {
            return false;
        }
        registrationUser(request);
        User user = userRepo.findByEmail(request.getEmail());
        personService.registrationPerson(user, request);
        return true;
    }

    private void registrationUser(RegistrationRequest request) {
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(request.getPasswd1()));
        user.setAccountNonLocked(true);
        user.setEnabled(true);
        userRepo.save(user);
    }

    public boolean passwordCheck(User user, String password) {
        return bCryptPasswordEncoder.matches(password, user.getPassword());
    }

    @Override
    public User loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    public User getUserByEmail(String email) {
        return loadUserByUsername(email);
    }

    public void setNewPassword(String email, String password) {
        User user = userRepo.findByEmail(email);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        userRepo.save(user);
    }
}
