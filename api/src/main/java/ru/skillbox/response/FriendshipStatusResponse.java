package ru.skillbox.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.skillbox.dto.FriendshipStatus;

@Getter
@Setter
@AllArgsConstructor
public class FriendshipStatusResponse extends AbstractResponse{

  @JsonProperty("user_id")
  private int id;
  @JsonIgnore
  private long time;
  @JsonProperty("status")
  private FriendshipStatus code;

}
