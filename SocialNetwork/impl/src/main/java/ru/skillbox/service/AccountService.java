package ru.skillbox.service;

import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.skillbox.model.User;
import ru.skillbox.request.account.AccountEditRq;
import ru.skillbox.request.account.AccountRecoveryRequest;
import ru.skillbox.response.account.BlockingAccountRs;
import ru.skillbox.response.account.DeleteAccountRs;
import ru.skillbox.repository.PersonRepo;
import ru.skillbox.repository.UserRepo;

@Service
@AllArgsConstructor
public class AccountService {

  private UserRepo userRepo;

  private PersonRepo personRepo;

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

  public String getCurrentPerson() {
    return "{\n" +
        "  \"error\": \"Неверный запрос\",\n" +
        "  \"timestamp\": 1644234125000,\n" +
        "  \"data\": {\n" +
        "    \"id\": 1,\n" +
        "    \"email\": \"dsiegertsz0@fc2.com\",\n" +
        "    \"phone\": \"+7 645 943 5082\",\n" +
        "    \"photo\": \"data:image/png;base64...\",\n" +
        "    \"about\": \"Maecenas tristique...\",\n" +
        "    \"city\": {\n" +
        "      \"id\": 0,\n" +
        "      \"title\": \"string\",\n" +
        "      \"country_id\": 0\n" +
        "    },\n" +
        "    \"country\": {\n" +
        "      \"id\": 0,\n" +
        "      \"title\": \"string\",\n" +
        "      \"cities\": [\n" +
        "        {\n" +
        "          \"id\": 0,\n" +
        "          \"title\": \"string\",\n" +
        "          \"country_id\": 0\n" +
        "        }\n" +
        "      ]\n" +
        "    },\n" +
        "    \"token\": \"string\",\n" +
        "    \"first_name\": \"Davida\",\n" +
        "    \"last_name\": \"Siegertsz\",\n" +
        "    \"reg_date\": 1618070680000,\n" +
        "    \"birth_date\": 702565308000,\n" +
        "    \"message_permission\": \"ALL\",\n" +
        "    \"last_online_time\": 1644234125000,\n" +
        "    \"is_online\": true,\n" +
        "    \"is_blocked\": false,\n" +
        "    \"is_deleted\": false\n" +
        "  },\n" +
        "  \"error_description\": \"asd\"\n" +
        "}";
  }

  public AccountEditRq modifyCurrentPerson() {
    return AccountEditRq.builder().build();
  }

  public DeleteAccountRs removeCurrentPerson() {
    return DeleteAccountRs.builder().build();
  }

}
