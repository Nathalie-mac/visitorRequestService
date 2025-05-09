package com.example.team2.uiservice;

import com.example.team2.model.uidto.LoginUIDTO;
import org.springframework.ui.Model;

public class AuthClientUIService {

    public String getLoginForm(Model model) {
        model.addAttribute("UserLogin", new LoginUIDTO());
        return "user_login";
    }


}
