package com.example.team2.uiservice;

import com.example.team2.auth.services.AuthService;
import com.example.team2.dto.LoginDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
@RequiredArgsConstructor
public class AuthClientUIService {
    private final AuthService authService;

    public String getLoginForm(Model model) {
        model.addAttribute("LoginDTO", new LoginDTO());
        return "user_login";
    }
    public String getSignUpForm( Model model) {
//        TODO: перенаправление на форму авторизации, сгенерировать и отослать форму аворизации на контроллер
        LoginDTO fillLoginDTO = new LoginDTO(); //заглушка
        authService.signUpClient(fillLoginDTO);
        return "login";
    }


}
