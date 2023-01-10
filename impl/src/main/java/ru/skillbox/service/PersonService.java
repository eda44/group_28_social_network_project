package ru.skillbox.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.skillbox.common.SearchPersonDto;
import ru.skillbox.enums.MessagePermission;
import ru.skillbox.exception.NotAuthorizedException;
import ru.skillbox.exception.UserNotFoundException;
import ru.skillbox.mapper.AccountMapper;
import ru.skillbox.model.Person;
import ru.skillbox.model.Settings;
import ru.skillbox.model.User;
import ru.skillbox.repository.PersonRepository;
import ru.skillbox.repository.SettingRepository;
import ru.skillbox.request.RegistrationRequest;
import ru.skillbox.request.account.AccountEditRq;

import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonService {
    private static final String USER_NOT_FOUND = "неверные учётные данные";
    private final GeoService geoService;
    private final PersonRepository personRepository;
    private final SettingRepository repositorySettings;



    public Person getPersonByEmail(String email) {
        Optional<Person> person = personRepository.findByEmail(email);
        if (person.isEmpty()) {
            throw new UsernameNotFoundException(USER_NOT_FOUND);
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
        person.setPhoto("http://res.cloudinary.com/diie0ma4r/image/upload/v1671641550/are7meiyau34dg1d7nto.png");
        person.setIsEnabled(true);
        person.setIsBlocked(false);
        person.setIsApproved(true);
        person.setMessagePermission(MessagePermission.ALL);
        person.setLastOnlineTime(new Date().getTime());
        person.setSettings(setSettings(person.getId()));
        personRepository.save(person);
    }

    public SearchPersonDto.AccountDto editing(AccountEditRq accountEditRq){
        Person person = getCurrentPerson();
        person.setFirstName(accountEditRq.getFirstName());
        person.setLastName(accountEditRq.getLastName());
        person.setPhone(accountEditRq.getPhone());
        person.setAbout(accountEditRq.getAbout());
        if (!accountEditRq.getCity().equals("none")) {
            person.setCity(geoService.getCityByTitle(accountEditRq.getCity()));
        }
        person.setBirthDate(accountEditRq.getBirthDate().getTime());
        person.setCountry(geoService.getCountryByTitle(accountEditRq.getCountry()));
        savePerson(person);
        return AccountMapper.INSTANCE.personToAccountDto(person);
    }

    public void deletePerson(Person person){
        person.setIsEnabled(false);
        savePerson(person);
    }
    public void isBlock(Long id, boolean isBlock){
        Person person = getPersonById(id);
        person.setIsBlocked(isBlock);
    }

    public Person getCurrentPerson() {
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (email.equals("anonymousUser")){
            throw new NotAuthorizedException();
        }
        Person current = getPersonByEmail(email);
        current.setLastOnlineTime(new Date().getTime());
        savePerson(current);
        return current;
    }

    public long getCurrentUserId(boolean isTest) {
        long currentUserId;
        if (isTest) {
            currentUserId = personRepository.findAll().get(0).getId();
        } else {
            currentUserId = getCurrentPerson().getId();
        }
        return currentUserId;
    }

    public void savePerson(Person person) {
        personRepository.save(person);
    }

    public Person getPersonById(long id) throws UserNotFoundException {
        Optional<Person> person = personRepository.findById(id);
        if (person.isEmpty()) {
            throw new UsernameNotFoundException(USER_NOT_FOUND);
        }
        return person.get();
    }

    public Settings setSettings(Long personId) {
        Settings setting = new Settings();
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
