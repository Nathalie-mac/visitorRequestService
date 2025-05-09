package com.example.team2.auth.exceptions.auth;

public class InvalidBasicAuthorizationHeaderException extends RuntimeException {
    public InvalidBasicAuthorizationHeaderException() {
        super("Invalid Basic Authorization Header");
    }
}
