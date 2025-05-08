package com.example.team2.auth.exceptions;

public class ExistingUserWithThatUserLoginException extends RuntimeException {
    public ExistingUserWithThatUserLoginException() {
        super("A user with that username already exists");
    }
}
