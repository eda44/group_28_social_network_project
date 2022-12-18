package ru.skillbox.service;

import java.util.Collections;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.skillbox.dto.AccountByIdDto;
import ru.skillbox.exception.UserNotFoundException;
import ru.skillbox.mapper.PersonToAccountById;
import ru.skillbox.response.account.SearchAccountRs;

@Service
@AllArgsConstructor
public class SearchPersonService {

  private PersonService personService;

  public AccountByIdDto getPersonById(long id) throws UserNotFoundException {
    return PersonToAccountById.personTo(personService.getPersonById(id));
  }

  public List<Integer> getIdsPerson(){
    return Collections.emptyList();
  }

  public SearchAccountRs searchAccount(){
    return SearchAccountRs.builder().build();
  }



}
