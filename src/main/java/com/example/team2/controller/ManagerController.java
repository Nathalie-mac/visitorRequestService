package com.example.team2.controller;

import com.example.team2.uiservice.ManagerUIService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    //Показ формы обработки заявки
    @GetMapping("/request")
    public String showConfirmationRequestPage(@RequestParam("id") Long requestId, Model model) {
        return managerUIService.openRequest(requestId,model);
    }

//    //Обработка данных формы заявки (POST)
//    @PostMapping("/request")
//    public String showConfirmationRequestPage(@RequestParam("id") Long requestId, Model model) {
//        return managerUIService.openRequest(requestId,model);
//    }

}
