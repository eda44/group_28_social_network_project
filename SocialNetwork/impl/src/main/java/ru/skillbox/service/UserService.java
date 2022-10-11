package ru.skillbox.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.skillbox.model.api.model.User;
import ru.skillbox.model.api.request.LoginRequest;
import ru.skillbox.model.api.request.RegistrationRequest;
import ru.skillbox.repository.UserRepo;

import java.util.Date;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public boolean saveUser(RegistrationRequest request) {
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

    public boolean passwordCheck(LoginRequest request) {
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

    public User getUserByEmail(String email){
        return userRepo.findByEmail(email);
    }
}
