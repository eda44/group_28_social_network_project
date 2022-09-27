package ru.skillbox.dto.account;

import lombok.Getter;

@Getter
public enum StatusCode {
    FRIEND,
    REQUEST_TO,
    REQUEST_FROM,
    BLOCKED,
    REJECTING,
    DECLINED,
    SUBSCRIBED,
    NONE,
    WATCHING
}
