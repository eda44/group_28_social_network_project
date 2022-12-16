package ru.skillbox.service;

import lombok.RequiredArgsConstructor;
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

    private final PersonRepository personRepository;

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
        person.setFirstName(request.getFirstName().trim());
        person.setLastName(request.getLastName().trim());
        person.setIsEnabled(true);
        person.setIsBlocked(false);
        person.setIsApproved(true);
        person.setMessagePermission(MessagePermission.ALL);
        person.setLastOnlineTime(new Date().getTime());
        person.setSettingsNotification(setSettings(person.getId()));
        personRepository.save(person);
    }

    public Person getCurrentPerson() {
        String email = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        Person current = getPersonByEmail(email);
        current.setLastOnlineTime(new Date().getTime());
        savePerson(current);
        return current;
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

    public SettingsNotification setSettings(Long personId) {
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
