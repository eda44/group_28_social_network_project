package ru.skillbox.exception;

public class UserIsAlreadyRegisteredException extends Exception{
    public UserIsAlreadyRegisteredException(String message) {
        super(message);
    }
}
