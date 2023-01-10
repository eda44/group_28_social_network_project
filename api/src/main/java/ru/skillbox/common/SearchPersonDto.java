package ru.skillbox.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import ru.skillbox.dto.enums.MessagePermission;
import ru.skillbox.dto.enums.StatusCode;
import ru.skillbox.response.Responsable;

import java.time.ZonedDateTime;
import java.util.List;

@Data
public class SearchPersonDto {
    private List<Long> ids;
    private String firstName;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime birthDateFrom;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime birthDateTo;
    private String city;
    private String country;
    private Integer ageTo;
    private Integer ageFrom;
    private StatusCode statusCode;

    @Getter
    @Setter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class AccountDto implements Responsable {
        private Long id;
        private String email;
        private String phone;
        private String photo;
        private String about;
        private String city;
        private String country;
        private String firstName;
        private String lastName;
        private Long regDate;
        private Long birthDate;
        private MessagePermission messagePermission;
        private Long lastOnlineTime;
        private Boolean isOnline;
        private Boolean isBlocked;
        private Boolean isDeleted;
        private StatusCode statusCode;

        @Override
        public Responsable getResponse(String message) {
            return null;
        }
    }
}
