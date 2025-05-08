package com.example.team2.auth.controller;

import com.example.team2.auth.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {


    private final AuthService authService;


    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@RequestHeader("Authorization") String authorizationHeader) {
        return authService.signUp(authorizationHeader);
    }


    @PostMapping("/sign-in")
    public ResponseEntity<?> signIn(@RequestHeader("Authorization") String authorizationHeader) {
        return authService.signIn(authorizationHeader);
    }
}
