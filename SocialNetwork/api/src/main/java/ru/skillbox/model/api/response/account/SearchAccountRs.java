package ru.skillbox.model.api.response.account;

import lombok.Builder;
import lombok.Data;
import ru.skillbox.dto.AccountAndStatusByIdDto;

@Data
@Builder
public class SearchAccountRs {

  private String error;

  private long timestamp;

  private int total;

  private int page;

  private int size;

  private AccountAndStatusByIdDto data;

  private String errorDescription;

}
