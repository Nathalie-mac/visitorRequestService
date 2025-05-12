package com.example.team2.auth.exceptions.auth;

public class InvalidCookieException extends RuntimeException{
    public InvalidCookieException() {
        super("Invalid Cookie");
    }
}