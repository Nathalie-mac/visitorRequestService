package com.example.team2.auth.services;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomResponse {
    private boolean isSuccess;
    private Long userId;
    private String cookieSessionId;
}
