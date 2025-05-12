package com.example.team2.exceptions.service;

public class ErrorInBDException extends RuntimeException {
    public ErrorInBDException(String message) {
        super(message);
    }
}
