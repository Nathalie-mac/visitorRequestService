package com.example.team2.uiservice;

import com.example.team2.dto.request.FilterListDTO;
import com.example.team2.dto.request.GuardOfficerSetUpTimeRequestDTO;
import com.example.team2.dto.request.RequestsTableDTO;
import com.example.team2.dto.response.GuardOfficerSetUpTimeResponseDTO;
import com.example.team2.model.Request;
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

    public String openRequest(Long requestId, Model model) {
        GuardOfficerSetUpTimeRequestDTO guardOfficerSetUpTimeRequestDTO = guardRequestService.getGuardOfficerSetUpTime(requestId);
        GuardOfficerSetUpTimeResponseDTO guardOfficerSetUpTimeResponseDTO = new GuardOfficerSetUpTimeResponseDTO();
        guardOfficerSetUpTimeResponseDTO.setIdRequest(guardOfficerSetUpTimeRequestDTO.getRequestDTO().getIdRequest());

        model.addAttribute("guardOfficerSetUpTimeRequestDTO", guardOfficerSetUpTimeRequestDTO);
        model.addAttribute("guardOfficerSetUpTimeResponseDTO", guardOfficerSetUpTimeResponseDTO);

        if (guardOfficerSetUpTimeRequestDTO.getRequestDTO().getVisitors().size() > 1) {
            return "guard_request_many";
        } else {
            return "guard_request_one";
        }
    }

    public String setEntryExitTime(GuardOfficerSetUpTimeResponseDTO guardOfficerSetUpTimeResponseDTO) {
        guardRequestService.setGuardOfficerEnterTime(guardOfficerSetUpTimeResponseDTO);
        //guardRequestService.setGuardOfficerExitTime(guardOfficerSetUpTimeResponseDTO);
        //GuardOfficerSetUpTimeRequestDTO guardOfficerSetUpTimeRequestDTO = new GuardOfficerSetUpTimeRequestDTO();
        return "redirect:/guardofficer/main";
    }

    public void setExitTime(GuardOfficerSetUpTimeResponseDTO guardOfficerSetUpTimeResponseDTO) {
        guardRequestService.setGuardOfficerExitTime(guardOfficerSetUpTimeResponseDTO);
    }

    public void setEntryTime(GuardOfficerSetUpTimeResponseDTO guardOfficerSetUpTimeResponseDTO) {
        guardRequestService.setGuardOfficerEnterTime(guardOfficerSetUpTimeResponseDTO);
    }
}
