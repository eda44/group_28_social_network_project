package ru.skillbox.service;

import java.util.Optional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.skillbox.dto.AccountDto;
import ru.skillbox.mapper.AccountMapper;
import ru.skillbox.model.User;
import ru.skillbox.repository.PersonRepository;
import ru.skillbox.request.account.AccountEditRq;
import ru.skillbox.request.account.AccountRecoveryRequest;
import ru.skillbox.response.account.BlockingAccountRs;
import ru.skillbox.response.account.DeleteAccountRs;
import ru.skillbox.repository.UserRepository;

@Service
@AllArgsConstructor
public class AccountService {

  private UserRepository userRepo;

  private PersonRepository personRepo;

  private PersonService personService;

  public boolean recoveryAccount(AccountRecoveryRequest accountRecoveryRequest) {
    Optional<User> userOptional = userRepo.findByEmailAndPassword(accountRecoveryRequest.getEmail(),
        accountRecoveryRequest.getPassword());
    return userOptional.isPresent();
  }

  public BlockingAccountRs accountBlocking(long id) {
    return BlockingAccountRs.builder().build();
  }

  public BlockingAccountRs accountUnblocking(long id) {
    return BlockingAccountRs.builder().build();
  }

  public String getCurrentPerson() throws JsonProcessingException {
    AccountDto accountDto = AccountMapper.INSTANCE.personToAccountDto(personService.getCurrentPerson());
    ObjectMapper mapper = new ObjectMapper();
    return mapper.writeValueAsString(accountDto);
  }

  public AccountEditRq modifyCurrentPerson() {
    return AccountEditRq.builder().build();
  }

  public DeleteAccountRs removeCurrentPerson() {
    return DeleteAccountRs.builder().build();
  }

}
