package com.example.team2.exceptions.service;

import org.aspectj.bridge.IMessage;
import org.aspectj.bridge.Message;

public class ErrorDTOPackingException extends RuntimeException {
    public ErrorDTOPackingException() {
        super("Error while packing data on service layer");
    }
}
