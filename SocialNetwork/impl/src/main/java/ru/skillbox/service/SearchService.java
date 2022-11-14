package ru.skillbox.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.skillbox.exception.UserNotAuthorized;
import ru.skillbox.model.Person;
import ru.skillbox.repository.PersonRepository;
import ru.skillbox.response.SearchResponse;
import ru.skillbox.specification.PersonSpecification;


import java.util.*;

@Service
@RequiredArgsConstructor
public class SearchService {

    private final PersonRepository personRepository;
    private final PersonService personService;

    public SearchResponse search(String author,
                                 String firstName,
                                 String lastName,
                                 Integer ageFrom,
                                 Integer ageTo,
                                 Integer city,
                                 Integer country,
                                 int page,
                                 int size) throws UserNotAuthorized {
        //TODO: User not authorized
        if (false) {
            throw new UserNotAuthorized("User not authorized");
        }
        Page<Person> people = searchFilter(getSpecifications(author, firstName, lastName, ageFrom, ageTo, city, country), size, page);
        if (!people.isFirst() && people.stream().findAny().isEmpty()) {
            page = 0;
            people = searchFilter(getSpecifications(author, firstName, lastName, ageFrom, ageTo, city, country), size, page);
        }
        return SearchResponse.getOkResponse(people, page, size, people.getTotalElements());
    }

    private Page<Person> searchFilter(List<Specification<Person>> specifications, int size, int page) {
        return personRepository.findAll(Specification
                .where(specifications.get(0))
                .and(specifications.get(1))
                .and(specifications.get(2))
                .and(specifications.get(3))
                .and(specifications.get(4))
                .and(specifications.get(5)), PageRequest.of(page, size)
        );
    }

    private List<Specification<Person>> getSpecifications(String author, String firstName, String lastName, Integer ageFrom, Integer ageTo, Integer city, Integer country) {
        List<Specification<Person>> params = new ArrayList<>();
        params.add(PersonSpecification.getUsersByEnable());
        params.add(PersonSpecification.skipCurrentPerson(personService.getCurrentPerson().getEmail()));
        params.add(firstName != null && !firstName.equals("") ? PersonSpecification.getUsersByFirstName(firstName) : null);
        params.add(lastName != null && !lastName.equals("") ? PersonSpecification.getUsersByLastName(lastName) : null);
        params.add(ageFrom != null ? PersonSpecification.getUsersByAgeFrom(yearToTimeInMillis(ageFrom)) : null);
        params.add(ageTo != null ? PersonSpecification.getUsersByAgeTo(yearToTimeInMillis(ageTo)) : null);
        return params;
    }

    private long yearToTimeInMillis(int year) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
        calendar.set(calendar.get(Calendar.YEAR) - year,
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DATE));
        return calendar.getTimeInMillis();
    }
}
