package ru.skillbox.response;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;
import ru.skillbox.dto.AccountDto;
import ru.skillbox.dto.Pageable;
import ru.skillbox.model.Person;

import java.util.List;

@Getter
@Builder
public class SearchResponse {
    private long totalElements;
    private int totalPages;
    private int numberOfElements;
    private long size;
    private long number;
    private List<AccountDto> content;
    private Pageable pageable;
    private boolean first;
    private boolean last;
    private boolean empty;
    private long total;
    private long page;

    public static SearchResponse getOkResponse(List<AccountDto> people,
                                               Page<Person> page) {
        return SearchResponse.builder()
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .first(page.isFirst())
                .last(page.isLast())
                .empty(page.isEmpty())
                .size(page.getSize())
                .number(page.getNumber())
                .content(people)
                .total(page.getTotalElements())
                .numberOfElements(page.getNumberOfElements())
                .page(page.getNumber())
                .build();
    }
}

