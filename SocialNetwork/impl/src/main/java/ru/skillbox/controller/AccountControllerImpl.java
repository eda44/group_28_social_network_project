package ru.skillbox.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skillbox.dto.AbstractDto;
import ru.skillbox.exception.UserNotFoundException;
import ru.skillbox.mapper.AccountMapper;
import ru.skillbox.model.Account;
import ru.skillbox.request.account.AccountEditRq;
import ru.skillbox.request.account.AccountRecoveryRequest;
import ru.skillbox.response.account.RecoveryAccountRs;
import ru.skillbox.service.AccountService;
import ru.skillbox.service.PersonService;
import ru.skillbox.service.SearchPersonService;

@RestController
@RequestMapping("api/v1/account/")
@AllArgsConstructor
public class AccountControllerImpl implements Account {

    private AccountService accountService;

    private SearchPersonService searchService;

    private PersonService personService;


    @Override
    @PutMapping("recovery")
    public ResponseEntity<?> accountRecovery(@RequestBody AccountRecoveryRequest accountRecoveryRequest) {

        if (accountRecoveryRequest.getPassword() == null || accountRecoveryRequest.getEmail() == null) {
            return ResponseEntity.status(400).body(RecoveryAccountRs.builder()
                    .error("Неверный запрос")
                    .errorDescription("Переданы пустые параметры"));
        }
        boolean checkRecovery = accountService.recoveryAccount(accountRecoveryRequest);
        if (checkRecovery) {
            return ResponseEntity.ok(RecoveryAccountRs.builder().timestamp(System.currentTimeMillis())
                    .data(new AbstractDto("ok")));
        } else {
            return ResponseEntity.status(400).body(RecoveryAccountRs.builder()
                    .error("Неверный запрос")
                    .errorDescription("Пользователь не найден"));
        }
    }

    @Override
    @GetMapping("me")
    public ResponseEntity<?> getUser() throws JsonProcessingException {
        return ResponseEntity.ok(accountService.getCurrentPerson());
    }

    @Override
    @PutMapping("me")
    public ResponseEntity<?> editingAnAccount(@RequestBody AccountEditRq accountEditRq) {
        return ResponseEntity.ok(accountService.modifyCurrentPerson());
    }

    @Override
    @DeleteMapping("me")
    public ResponseEntity<?> removeAnAccount() {
        return ResponseEntity.ok(accountService.removeCurrentPerson());
    }

    @Override
    @PutMapping("block/{id}")
    public ResponseEntity<?> accountBlockingById(@PathVariable long id) {
        return ResponseEntity.ok(accountService.accountBlocking(id));
    }

    @Override
    @DeleteMapping("block/{id}")
    public ResponseEntity<?> accountUnblockingById(@PathVariable long id) {
        return ResponseEntity.ok(accountService.accountUnblocking(id));
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable long id) {
        try {
            return ResponseEntity.ok(AccountMapper.INSTANCE.personToAccountDto(personService.getPersonById(id)));
        } catch (UserNotFoundException e) {
            return ResponseEntity.badRequest().body("");//TODO: исправить
        }
    }

    @Override
    @GetMapping("account/search")
    public ResponseEntity<?> searchAccount(@RequestParam(name = "author") String author,
                                           @RequestParam(name = "firstName") String firstName,
                                           @RequestParam(name = "lastName") String lastName,
                                           @RequestParam(name = "ageFrom") int ageFrom,
                                           @RequestParam(name = "ageTo") int ageTo,
                                           @RequestParam(name = "city") int city,
                                           @RequestParam(name = "country") int country) {
        return ResponseEntity.ok(searchService.searchAccount());
    }

    @Override
    @GetMapping("account/ids")
    public ResponseEntity<?> getIdUsers(@RequestParam(name = "author") String author) {
        return ResponseEntity.ok(searchService.getIdsPerson());
    }

}
