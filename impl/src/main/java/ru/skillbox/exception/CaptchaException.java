package ru.skillbox.exception;

public class CaptchaException extends RuntimeException{
    public CaptchaException() {
        super("Капча введена неверно");
    }
}
