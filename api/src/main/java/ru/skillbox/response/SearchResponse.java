package ru.skillbox.response;

import lombok.Data;
import org.springframework.data.domain.Page;
import ru.skillbox.common.SearchPersonDto;
import ru.skillbox.model.Person;

import java.util.List;

@Data
public class SearchResponse implements Responsable{
    private long totalElements;
    private int totalPages;
    private int numberOfElements;
    private long size;
    private long number;
    private List<SearchPersonDto.AccountDto> content;
    private boolean first;
    private boolean last;
    private boolean empty;
    private long total;
    private long page;

    public SearchResponse getOkResponse(List<SearchPersonDto.AccountDto> people,
                                               Page<Person> page) {
        SearchResponse searchResponse = new SearchResponse();
        searchResponse.setTotalElements(page.getTotalElements());
        searchResponse.setContent(people);
        searchResponse.setLast(page.isLast());
        searchResponse.setEmpty(page.isEmpty());
        searchResponse.setFirst(page.isFirst());
        searchResponse.setTotalPages(page.getTotalPages());
        searchResponse.setSize(page.getSize());
        searchResponse.setNumber(page.getNumber());
        searchResponse.setTotalElements(page.getTotalElements());
        searchResponse.setNumberOfElements(page.getNumberOfElements());
        searchResponse.setPage(page.getNumber());
        return searchResponse;
    }

    @Override
    public Responsable getResponse(String s) {
        return null;
    }
}

