package com.example.team2.auth.controller;

import com.example.team2.dto.LoginDTO;
import com.example.team2.uiservice.AuthClientUIService;
import org.springframework.ui.Model;
import com.example.team2.auth.services.AuthService;
import com.example.team2.auth.services.AuthClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
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

//    @GetMapping("/login")
//    public String showLoginForm(@RequestParam(required = false) String error, Model model) {
//        if (error != null) {
//            model.addAttribute("error", "Неверные учетные данные");
//        }
//        model.addAttribute("loginRequest", new UserLogin());
//        return "login";
//    }

    // 2. Обработка данных формы (POST)
    @PostMapping("/submit")
    public String submitForm(@ModelAttribute("user") LoginDTO user) {
        System.out.println("Имя: " + user.getLogin());
     //   System.out.println("Email: " + user.getEmail());
        return "result"; // Перенаправление на страницу результата
    }

    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@RequestHeader("Authorization") String authorizationHeader) {
        return authService.signUpClient(authorizationHeader);
    }

    @PostMapping("/sign-in")
    public ResponseEntity<?> signIn(@RequestHeader("Authorization") String authorizationHeader) {
        return authService.signInClient(authorizationHeader);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader("Cookie") String cookieHeader) {
        return authService.logout(cookieHeader);
    }
}
