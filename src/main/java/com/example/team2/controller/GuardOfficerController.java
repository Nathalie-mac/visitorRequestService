package com.example.team2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/guardOfficer")
public class GuardOfficerController {

    @GetMapping("/requests")
    public String showApprovedRequests() {
        // Заполняем фильтры
        // Заполняем таблицу заявок

        return "guard_request_table";
    }



}
