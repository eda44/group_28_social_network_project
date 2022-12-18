package ru.skillbox.response.post;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Pageable;
import ru.skillbox.dto.Sort;

import java.util.List;

@Getter
@Setter
public class PagePostDto {
@JsonProperty("total_pages")
    private Integer totalPages;
@JsonProperty("total_elements")
    private Long totalElements;
    private Integer number;
    private Integer size;
    private List<PostResponse> content;
    private Sort sort;
    private Boolean first;
    private Boolean last;
    @JsonProperty("number_of_elements")
    private Integer numberOfElements;
    private Pageable pageable;
    private Boolean empty;
}
