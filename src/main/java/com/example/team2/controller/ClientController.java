package com.example.team2.controller;

import com.example.team2.dto.VisitorDTO;
import com.example.team2.dto.hotass.AppointmentRequestResponseHelpDTO;
import com.example.team2.dto.response.AppointmentRequestResponseDTO;
import com.example.team2.uiservice.ClientUIService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/client")
public class ClientController {
    private final ClientUIService clientUIService;

    // Отображение страницы с заявками (главная страница web_request_table)
    @GetMapping("/main/{userId}")
    public String showRequestsPage(@PathVariable Long userId, Model model) {
        // Здесь должна быть логика получения заявок из базы данных

        return clientUIService.getMainPage(userId, model);
    }

    // Открытие страницы с формой создания заявки (главная страница web_request_table)
    @GetMapping("/type")
    public String showRequestForm() {

        return clientUIService.getTypePage();
    }

    // Открытие страницы создания заявки для личного посещения (страница выбора типа web_visit_type)
    @GetMapping("/personal-visit")
    public String personalVisit(Model model, @RequestHeader("Cookie") String cookieHeader) {
        return clientUIService.getPersonalVisitPage(model, cookieHeader);
    }

    // Открытие страницы создания заявки для группового посещения (страница выбора типа web_visit_type)
    @GetMapping("/group-visit")
    public String groupVisit(Model model, @RequestHeader("Cookie") String cookieHeader) {
        return clientUIService.getGroupVisitPage(model, cookieHeader);
    }

    // Сохранение данных с формы в бд (страница личной заявки web_request_one и групповая web_request_many)
    @PostMapping("/personal-visit")
    public String handlePersonalVisitSubmission(@ModelAttribute("appointmentRequest") AppointmentRequestResponseHelpDTO responseDTO){
        // 1. Обработка данных формы (web_request_one)
        // 2. Обработка загруженных файлов:
        //    - photo (фото посетителя)
        //    - documents (дополнительные документы)
            // Сохранение данных и файлов
            // visitService.saveVisit(appointmentRequest, photo, documents);

        return clientUIService.createNewPersonalRequest(responseDTO);
    }
    @PostMapping("/group-visit")
    public String handleGroupVisitSubmission(@ModelAttribute("appointmentRequest") AppointmentRequestResponseDTO responseDTO){
        // 1. Обработка данных формы (web_request_one)
        // 2. Обработка загруженных файлов:
        //    - photo (фото посетителя)
        //    - documents (дополнительные документы)
        // Сохранение данных и файлов
        // visitService.saveVisit(appointmentRequest, photo, documents);

        return clientUIService.createNewGroupRequest(responseDTO);
    }

}
