package com.example.team2.auth.controller;

import com.example.team2.auth.services.AuthService;
import com.example.team2.dto.LoginDTO;
import com.example.team2.model.StuffRoleType;
import com.example.team2.uiservice.AuthStuffUIService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth/manager")
@RequiredArgsConstructor
public class AuthManagerController {

    private final AuthService authService;
    private final AuthStuffUIService authStuffUIService;

    //TODO: снести все для регистрации после полной отладки
    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@RequestHeader("Authorization") String authorizationHeader) {
        return authService.signUpStuff(authorizationHeader, StuffRoleType.MANAGER);
    }


    // Показ формы входа
    @GetMapping("/sign-in")
    public String showSignInForm(Model model) {
        return authStuffUIService.getSignInStuffForm(model, StuffRoleType.MANAGER);
    }

    //Обработка данных формы входа (POST)
    @PostMapping("/sign-in")
    public String signIn(@ModelAttribute("LoginDTO") LoginDTO loginDTO, Model model,  HttpServletResponse response) {
        return authStuffUIService.postSignInManager(loginDTO,model, response);

    }

    //выход (потом снесем)
    //TODO: снести после отладки фронта
    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader("Cookie") String cookieHeader) {
        return authService.logout(cookieHeader);
    }
}
