package ru.skillbox.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.skillbox.model.LoginController;
import ru.skillbox.request.LoginRequest;
import ru.skillbox.service.UserService;

@RestController
@RequiredArgsConstructor
public class LoginControllerImpl implements LoginController {

    private final UserService userService;

    @Override
    public ResponseEntity<String> login(LoginRequest request) {
       /* try {
            return ResponseEntity.ok(userService.login(request));
        }catch (InvalidCredentialsException e){
            return ResponseEntity.badRequest().body(LoginResponse.getBadResponse());
        }*/

      return ResponseEntity.ok("{\n" +
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
                "}");
    }

    @Override
    public String logout() {
        return "logout";
    }
}
