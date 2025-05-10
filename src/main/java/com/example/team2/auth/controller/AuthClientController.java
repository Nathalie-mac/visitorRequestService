package com.example.team2.auth.controller;

import com.example.team2.auth.services.AuthService;
import com.example.team2.dto.LoginDTO;
import com.example.team2.uiservice.AuthClientUIService;
import org.springframework.ui.Model;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthClientController {


    private final AuthService authService;
    private final AuthClientUIService authClientUIService;

    // Показ формы входа
    @GetMapping("/login")
    public String showLoginForm(Model model) {
        return authClientUIService.getLoginForm(model);
    }

    // 2. Обработка данных формы (POST)
    @PostMapping("/login")
    public String login(@ModelAttribute("LoginDTO") LoginDTO loginDTO) {
        return authClientUIService.postLogin(loginDTO);

    }

    @PostMapping("/sign-up")
    public String signUp(@RequestHeader("Authorization") String authorizationHeader, Model model) {
//        return authService.signUpClient(authorizationHeader);
        return authClientUIService.postSignUpForm(model);
    }

    @PostMapping("/sign-in")
    public ResponseEntity<?> signIn(@RequestHeader("Authorization") String authorizationHeader) {
//        return authService.signInClient(authorizationHeader);
        return null;
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader("Cookie") String cookieHeader) {
        return authService.logout(cookieHeader);
    }
}
