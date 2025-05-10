package com.example.team2.auth.controller;

import com.example.team2.auth.services.AuthClientService;
import com.example.team2.model.StuffRoleType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth/manager")
@RequiredArgsConstructor
public class AuthManagerController {

    private final AuthClientService authService;

    //TODO: снести все для регистрации после полной отладки
    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@RequestHeader("Authorization") String authorizationHeader) {
        return authService.signUpStuff(authorizationHeader, StuffRoleType.MANAGER);
    }

    @PostMapping("/sign-in")
    public ResponseEntity<?> signIn(@RequestHeader("Authorization") String authorizationHeader) {
        return authService.signInStuff(authorizationHeader, StuffRoleType.MANAGER);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader("Cookie") String cookieHeader) {
        return authService.logout(cookieHeader);
    }
}
