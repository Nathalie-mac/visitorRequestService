package com.example.team2.controller;

import com.example.team2.dto.LoginDTO;
import com.example.team2.model.StuffRoleType;
import com.example.team2.uiservice.ManagerUIService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/manager")
@RequiredArgsConstructor
public class ManagerController {
    private final ManagerUIService managerUIService;

    // Показ главной формы входа
    @GetMapping("/main")
    public String showMainPage(Model model) {
        return managerUIService.getMainPage(model);
    }

//    //Обработка данных формы входа (POST)
//    @PostMapping("/sign-in")
//    public String signIn(@ModelAttribute("LoginDTO") LoginDTO loginDTO, Model model, HttpServletResponse response) {
//        return authStuffUIService.postSignInManager(loginDTO,model, response);
//
//    }
}
