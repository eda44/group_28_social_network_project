package ru.skillbox.model.api.response.account;

import lombok.Builder;
import lombok.Data;
import ru.skillbox.dto.AbstractDto;

@Data
@Builder
public class DeleteAccountRs {

  private String error;

  private long timestamp;

  private AbstractDto data;

  private String errorDescription;

}
