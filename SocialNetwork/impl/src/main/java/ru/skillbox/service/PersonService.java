package ru.skillbox.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.skillbox.dto.enums.MessagePermission;
import ru.skillbox.exception.UserNotFoundException;
import ru.skillbox.model.Person;
import ru.skillbox.model.SettingsNotification;
import ru.skillbox.model.User;
import ru.skillbox.repository.PersonRepository;

import ru.skillbox.repository.SettingNotificationRepository;
import ru.skillbox.request.RegistrationRequest;

import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonService {

    @Autowired
    private final PersonRepository personRepository;

    @Autowired
    private final SettingNotificationRepository repositorySettings;


    public Person getPersonByEmail(String email) {
        Optional<Person> person = personRepository.findByEmail(email);
        if (person.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        return person.get();
    }

    public void registrationPerson(User user, RegistrationRequest request) {
        Person person = new Person();
        person.setId(user.getId());
        person.setEmail(user.getEmail());
        person.setRegDate(new Date().getTime());
        person.setFirstName(request.getFirstName());
        person.setLastName(request.getLastName());
        person.setIsEnabled(true);
        person.setIsBlocked(false);
        person.setIsApproved(true);
        person.setMessagePermission(MessagePermission.ALL);
        person.setLastOnlineTime(new Date().getTime());
        person.setSettingsNotification(myMetod(person.getId()));
        personRepository.save(person);
    }

    public Person getCurrentPerson() {
        String email = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        return getPersonByEmail(email);
    }

    public void savePerson(Person person) {
        personRepository.save(person);
    }

    public Person getPersonById(long id) throws UserNotFoundException {
        Optional<Person> person = personRepository.findById(id);
        if (person.isEmpty()) {
            throw new UserNotFoundException("User not found");
        }
        return person.get();
    }

    public Person setPerson() {
        Person person = new Person();
        person.setPhoto("my_image.png");
        person.setPhone("9999999999");
        person.setMessagePermission(MessagePermission.ALL);
        person.setEmail("email");
        person.setAbout("about");
        person.setLastName("last");
        person.setIsBlocked(false);
        person.setIsApproved(true);
        person.setFirstName("first");
        person.setConfirmationCode("1111");
        person.setRegDate(48485151L);
        person.setBirthDate(55454615L);
        return person;
    }

    public SettingsNotification myMetod(Long personId) {
        SettingsNotification setting = new SettingsNotification();
        setting.setId(personId);
        setting.setFriendRequest(true);
        setting.setFriendBirthday(true);
        setting.setPostComment(true);
        setting.setCommentComment(true);
        setting.setMessage(true);
        setting.setPost(true);
        setting.setSendPhoneMessage(true);
        setting.setSendEmailMessage(true);
        repositorySettings.save(setting);
        return setting;
    }
}
