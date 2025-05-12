package com.example.team2.uiservice;

import com.example.team2.dto.request.FilterListDTO;
import com.example.team2.dto.request.RequestsTableDTO;
import com.example.team2.service.GuardRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
@RequiredArgsConstructor
public class GuardOfficerUIService {
    private final GuardRequestService guardRequestService;

    //показ главной формы с фильтрами и списком одобренных заявок
    public String getMainPage(Model model) {
        RequestsTableDTO requestsTableDTO = guardRequestService.getApprovedRequestsTableDTO();
        FilterListDTO filterListDTO = guardRequestService.getFilterLists();
        model.addAttribute("filterList", filterListDTO);
        model.addAttribute("requestsTable", requestsTableDTO);
        return "guard_request_table";
    }
}
