package ru.skillbox.dto.account;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CaptchaRs {

    private String secret;
    private String image;
}
