package ru.skillbox.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.skillbox.dto.enums.MessagePermission;
import ru.skillbox.model.Person;
import ru.skillbox.model.User;
import ru.skillbox.repository.PersonRepo;
import ru.skillbox.request.LoginRequest;
import ru.skillbox.request.RegistrationRequest;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepo personRepo;

    public Person getPersonByEmail(LoginRequest request) {
        return personRepo.findByEmail(request.getEmail());
    }

    public void registrationPerson(User user, RegistrationRequest request) {
        Person person = new Person();
        person.setId(user.getId());
        person.setEmail(user.getEmail());
        person.setRegDate(new Date().getTime());
        person.setFirstName(request.getFirstName());
        person.setLastName(request.getLastName());
        person.setBlocked(false);
        person.setEnabled(true);
        person.setMessagePermission(MessagePermission.ALL);
        person.setLastOnlineTime(new Date().getTime());
        personRepo.save(person);
    }

    public void savePerson(Person person) {
        personRepo.save(person);
    }

    public Person getPersonById(long id) {
        return personRepo.findById(id);
    }

    public Person setPerson() {
        Person person = new Person();
        person.setPhoto("my_image.png");
        person.setPhone("9999999999");
        person.setMessagePermission(MessagePermission.ALL);
        person.setEmail("email");
        person.setAbout("about");
        person.setLastName("last");
        person.setBlocked(false);
        person.setApproved(true);
        person.setFirstName("first");
        person.setConfirmationCode(1111);
        person.setRegDate(48485151L);
        person.setBirthDate(55454615L);

        return person;
    }
}
