package ru.skillbox.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.skillbox.dto.AccountByIdDto;
import ru.skillbox.mapper.PersonToAccountById;
import ru.skillbox.model.Person;
import ru.skillbox.model.api.response.account.SearchAccountRs;
import ru.skillbox.repository.PersonRepo;
import ru.skillbox.repository.UserRepo;

@Service
@AllArgsConstructor
public class SearchPersonService {

  private UserRepo userRepo;

  private PersonRepo personRepo;

  public AccountByIdDto getPersonById(long id) {
    Optional<Person> optionalPerson = personRepo.findById(id);
    if (optionalPerson.isPresent()) {
      return PersonToAccountById.personTo(optionalPerson.get());
    } else {
      return AccountByIdDto.builder().build();
    }
  }

  public List<Integer> getIdsPerson(){
    return Collections.emptyList();
  }

  public SearchAccountRs searchAccount(){
    return SearchAccountRs.builder().build();
  }



}
