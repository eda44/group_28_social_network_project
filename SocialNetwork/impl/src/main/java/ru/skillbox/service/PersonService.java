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


    public void registrationPerson(User user) {
        Person person = new Person();
        person.setId(user.getId());
        person.setEmail(user.getEmail());
        person.setRegDate(user.getRegDate());
        person.setFirstName(user.getFirstName());
        person.setLastName(user.getLastName());
//        person.setBlocked(false);
        person.setMessagePermission(MessagePermission.ALL);
        person.setLastOnlineTime(new Date().getTime());
        personRepo.save(person);
    }

    public void savePerson(Person person) {
        personRepo.save(person);
    }

    public Person getPersonById(long id) {
        return personRepo.findById(id).get();
    }

    public Person setPerson() {
        Person person = new Person();
        person.setPhoto("my_image.png");
        person.setPassword("pass");
        person.setTown("town");
        person.setPhone("9999999999");
        person.setMessagePermission(MessagePermission.ALL);
        person.setEmail("email");
        person.setAbout("about");
        person.setLastName("last");
        person.setBlocking(false);
        person.setApproved(true);
        person.setFirstName("first");
        person.setConfirmationCode(1111);
        person.setRegDate(48485151L);
        person.setBirthDate(55454615L);

        return person;
    }
}
