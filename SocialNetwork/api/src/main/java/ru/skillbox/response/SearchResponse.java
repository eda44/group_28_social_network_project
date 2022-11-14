package ru.skillbox.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;
import ru.skillbox.dto.AccountDto;
import ru.skillbox.model.Person;

import java.util.Date;
import java.util.List;

@Getter
@Builder
public class SearchResponse {
    private String error;
    private Long timestamp;
    private long total;
    private int page;
    private int size;
    private List<AccountDto> data;
    @JsonProperty("error_description")
    private String errorDescription;

    public static SearchResponse getOkResponse(Page<Person> people, int page, int size, long total){
        return SearchResponse.builder()
                .timestamp(new Date().getTime())
                .total(total)
                .size(size)
                .page(page)
                .data(AccountDto.getCorrectListRsSearch(people))
                .build();
    }

    public static SearchResponse getBadResponse(){
        return SearchResponse.builder()
                .error("Неверный запрос")
                .timestamp(new Date().getTime())
                .errorDescription("Неверный код авторизации")
                .build();
    }
}

