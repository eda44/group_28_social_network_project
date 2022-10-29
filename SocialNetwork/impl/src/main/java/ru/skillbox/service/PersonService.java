package ru.skillbox.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.skillbox.dto.enums.MessagePermission;
import ru.skillbox.model.Person;
import ru.skillbox.model.User;
import ru.skillbox.repository.PersonRepo;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepo personRepo;


    public void registrationPerson(User user){
        Person person = new Person();
        person.setId(user.getId());
        person.setEmail(user.getEmail());
        person.setRegDate(user.getRegDate());
        person.setFirstName(user.getFirstName());
        person.setLastName(user.getLastName());
        person.setBlocked(false);
        person.setMessagePermission(MessagePermission.ALL);
        person.setLastOnlineTime(new Date().getTime());
        personRepo.save(person);
    }
}
