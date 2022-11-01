package ru.skillbox.service;

import java.util.Collections;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.skillbox.dto.AccountByIdDto;
import ru.skillbox.mapper.PersonToAccountById;
import ru.skillbox.response.account.SearchAccountRs;
import ru.skillbox.repository.PersonRepo;

@Service
@AllArgsConstructor
public class SearchPersonService {

  private PersonRepo personRepo;

  public AccountByIdDto getPersonById(long id) {
    return PersonToAccountById.personTo(personRepo.findById(id));
  }

  public List<Integer> getIdsPerson(){
    return Collections.emptyList();
  }

  public SearchAccountRs searchAccount(){
    return SearchAccountRs.builder().build();
  }



}
