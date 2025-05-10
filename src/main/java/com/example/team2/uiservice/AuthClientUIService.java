package com.example.team2.uiservice;

import com.example.team2.dto.LoginDTO;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
@Service
public class AuthClientUIService {

    public String getLoginForm(Model model) {
        model.addAttribute("LoginDTO", new LoginDTO());
        return "user_login";
    }


}
