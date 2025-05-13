package com.example.team2.auth.controller;

import com.example.team2.auth.services.AuthService;
import com.example.team2.dto.LoginDTO;
import com.example.team2.uiservice.AuthClientUIService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthClientController {


    private final AuthService authService;
    private final AuthClientUIService authClientUIService;

    // Показ формы входа
    @GetMapping("/sign-in")
    public String showSignInForm(Model model) {
        return authClientUIService.getSignInForm(model);
    }

    //Обработка данных формы входа (POST)
    @PostMapping("/sign-in")
    public String signIn(@ModelAttribute("LoginDTO") LoginDTO loginDTO, Model model, HttpServletResponse response) {
        return authClientUIService.postSignIn(model, loginDTO, response);

    }

    // Показ формы регистрации
    @GetMapping("/sign-up")
    public String showSignUpForm(Model model) {
        return authClientUIService.getSignUpForm(model);
    }

    //Обработка данных формы регистрации (POST)
    @PostMapping("/sign-up")
    public String signUp(@ModelAttribute("LoginDTO") LoginDTO loginDTO) {
//        return authService.signUpClient(authorizationHeader);
        return authClientUIService.postSignUpForm(loginDTO);
    }

    //выход (потом снесем)
    //TODO: снести после отладки фронта
    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader("Cookie") String cookieHeader) {
        return authService.logout(cookieHeader);
    }
}
