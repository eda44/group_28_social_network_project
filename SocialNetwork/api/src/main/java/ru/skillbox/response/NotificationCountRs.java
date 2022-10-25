package ru.skillbox.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class NotificationCountRs {

    private long timestamp;
    private CountRs countRs;
}
