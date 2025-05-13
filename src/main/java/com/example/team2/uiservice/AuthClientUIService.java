package com.example.team2.uiservice;

import com.example.team2.auth.services.AuthService;
import com.example.team2.auth.services.CustomResponse;
import com.example.team2.dto.LoginDTO;
import com.example.team2.uiservice.provider.SessionCookieProvider;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
@RequiredArgsConstructor
public class AuthClientUIService {
    private final AuthService authService;

    // Показ формы входа
    public String getSignInForm(Model model) {
        model.addAttribute("LoginDTO", new LoginDTO());
        return "user_login";
    }

    //Обработка данных формы входа (POST)
    public String postSignIn(Model model, LoginDTO loginDTO, HttpServletResponse response) {
        CustomResponse authResponse = authService.signInClient(loginDTO);


        SessionCookieProvider.setUpSessionCookie(response, authResponse.getCookieSessionId());

        return "redirect:/client/main/" + authResponse.getUserId();

    }


    // Показ формы регистрации
    public String getSignUpForm(Model model) {
        //TODO: показ формы регистрации
        return "user_registration";
    }

    //Обработка данных формы регистрации (POST)
    public String postSignUpForm(LoginDTO loginDTO) {
//        TODO: перенаправление на форму авторизации, сгенерировать и отослать форму аворизации на контроллер
        authService.signUpClient(loginDTO);
        return "user_login";
    }


}
