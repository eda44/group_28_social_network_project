package ru.skillbox.exception;

public class UserNotAuthorized extends Exception{
    public UserNotAuthorized(String message) {
        super(message);
    }
}