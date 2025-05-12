package com.example.team2.uiservice;

import com.example.team2.auth.services.AuthService;
import com.example.team2.auth.services.CustomResponse;
import com.example.team2.auth.services.parser.CookieHeaderParser;
import com.example.team2.dto.LoginDTO;
import com.example.team2.uiservice.provider.SessionCookieProvider;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    //TODO: доделать проверку на соответствие пароля
    //Обработка данных формы входа (POST)
    public String postSignIn(Model model, LoginDTO loginDTO, HttpServletResponse response) {
        CustomResponse authResponse = authService.signInClient(loginDTO);

        if (authResponse.isSuccess()) {

            SessionCookieProvider.setUpSessionCookie(response, authResponse.getCookieSessionId());


            return "web_request_table";
        } else {
            model.addAttribute("errorMessage", "Упс! Вы ввели неправильный пароль. Попробуйте еще раз");
            model.addAttribute("status", HttpStatus.UNAUTHORIZED.value());
            return "error_login";
        }
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
