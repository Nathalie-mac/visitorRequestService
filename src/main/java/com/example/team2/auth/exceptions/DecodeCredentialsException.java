package com.example.team2.auth.exceptions;

public class DecodeCredentialsException extends RuntimeException {
    public DecodeCredentialsException() {
        super("It is not possible to decode credentials with base64");
    }
}
