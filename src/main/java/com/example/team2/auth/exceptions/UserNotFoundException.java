package com.example.team2.auth.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super("A user with that username was not found.");
    }
}
