package ru.skillbox.model.api.model;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import ru.skillbox.model.api.request.account.AccountEditRq;
import ru.skillbox.model.api.request.account.AccountRecoveryRequest;

public interface Account {

  /**
   * Восстоновление аккаунта пользователя
   *
   * @param accountRecoveryRequest
   * @return
   */
  ResponseEntity<?> accountRecovery(@RequestBody AccountRecoveryRequest accountRecoveryRequest);

  /**
   * Получение текущего пользователя
   *
   * @return
   */
  ResponseEntity<?> getUser();

  /**
   * Редактирование текущего аккаунта
   *
   * @param accountEditRq
   * @return
   */
  ResponseEntity<?> editingAnAccount(@RequestBody AccountEditRq accountEditRq);

  /**
   * Удаление текущего аккаунта
   *
   * @return
   */
  ResponseEntity<?> removeAnAccount();

  /**
   * Блокировка аккаунта по id
   *
   * @param id
   * @return
   */
  ResponseEntity<?> accountBlockingById(long id);

  /**
   * Разблокировка аккаунта по id
   *
   * @param id
   * @return
   */
  ResponseEntity<?> accountUnblockingById(long id);

  /**
   * Получение пользователя по id
   *
   * @param id
   * @return
   */
  ResponseEntity<?> getUserById(long id);


  /**
   * Поиск аккаунта
   *
   * @param author
   * @param firstName
   * @param lastName
   * @param ageFrom
   * @param ageTo
   * @param city
   * @param country
   * @return
   */
  ResponseEntity<?> searchAccount(String author, String firstName, String lastName, int ageFrom, int ageTo, int city, int country);

  /**
   * Получение id пользователя
   *
   * @param author
   * @return
   */
  ResponseEntity<?> getIdUsers(String author);

}
