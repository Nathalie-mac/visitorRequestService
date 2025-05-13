package com.example.team2.exceptions.data;

public class ExistingUserWithThatUsernameException extends RuntimeException {
    public ExistingUserWithThatUsernameException() {
        super("A user with that username already exists");
    }
}
