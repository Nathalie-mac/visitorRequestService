package com.example.team2.controller;

import com.example.team2.uiservice.GuardOfficerUIService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/guardofficer")
@RequiredArgsConstructor
public class GuardOfficerController {
    private final GuardOfficerUIService guardOfficerUIService;

    // Отображение страницы заявок и фильтров для охранника (страница guard_request_table)
    @GetMapping("/main")
    public String showApprovedRequests(Model model) {
        return guardOfficerUIService.getMainPage(model);
    }

    // отображение заявок (страница "guard_request_one" и "guard_request_many")
    @GetMapping("/request")
    public  String showRequest(@RequestParam("id") Long requestId, Model model) {

        // if () {return "guard_request_one"} else {return "guard_request_many"}
        return guardOfficerUIService.openRequest(requestId, model);
    }

    // сохранение времени входа и выхода
    @PostMapping("/request")
    public String handleEntryExitTime(){

        return "redirect:/guard_request_table";
    }

}
