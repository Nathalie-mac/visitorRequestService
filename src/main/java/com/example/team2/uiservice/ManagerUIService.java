package com.example.team2.uiservice;

import com.example.team2.dto.request.FilterListDTO;
import com.example.team2.dto.request.ManagerConfirmationRequestDTO;
import com.example.team2.dto.request.RequestsTableDTO;
import com.example.team2.dto.response.ManagerConfirmationResponseDTO;
import com.example.team2.service.GuardRequestService;
import com.example.team2.service.ManagerConfirmationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
@RequiredArgsConstructor
public class ManagerUIService {
    private final ManagerConfirmationService managerConfirmationService;
    private final GuardRequestService guardRequestService;


    // Показ формы входа
    public String getMainPage(Model model) {
        RequestsTableDTO requestsTableDTO = managerConfirmationService.getRequestsTableDTO();
        FilterListDTO filterList = guardRequestService.getFilterLists();
        model.addAttribute("requests", requestsTableDTO.getRegistrationApplicationTable());
        model.addAttribute("filterList", filterList);
        return "manager_confirmation_request";
    }

    //Обработка данных формы входа (POST)
    public String openRequest(Long requestId, Model model) {
        ManagerConfirmationRequestDTO managerRequestDTO = managerConfirmationService.getManagerConfirmation(requestId);
        ManagerConfirmationResponseDTO responseDTO = new ManagerConfirmationResponseDTO();
        responseDTO.setIdRequest(managerRequestDTO.getRequestDTO().getIdRequest());

        // Добавляем атрибуты в модель
        model.addAttribute("managerConfirmation", managerRequestDTO);
        model.addAttribute("responseDTO", responseDTO);

        if (managerRequestDTO.getRequestDTO().getVisitors().size() > 1) {
            return "manager_check_many";
        } else {
            return "manager_check_one";
        }
    }

    //Обработка данных формы входа (POST)
    public String confirmationRequest(ManagerConfirmationResponseDTO responseDTO) {
        managerConfirmationService.requestCheck(responseDTO);
        return "redirect:/manager/main";
    }

}
