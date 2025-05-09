package com.example.team2.advice;

import lombok.Data;

import java.time.LocalDateTime;

//оболочка ответа на исключения
@Data
public class CustomErrorResponse {
    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String message;
    private String path;

}
