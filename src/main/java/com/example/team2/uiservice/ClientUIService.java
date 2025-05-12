package com.example.team2.uiservice;

import com.example.team2.auth.services.AuthService;
import com.example.team2.dto.VisitorDTO;
import com.example.team2.dto.request.AppointmentRequestRequestDTO;
import com.example.team2.dto.request.FilterListDTO;
import com.example.team2.dto.request.RequestsTableDTO;
import com.example.team2.dto.request.SubmittedRequestTableDTO;
import com.example.team2.dto.response.AppointmentRequestResponseDTO;
import com.example.team2.service.AppointmentRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientUIService {
    private final AuthService authService;
    private final AppointmentRequestService appointmentRequestService;

    public String getMainPage(Long userId, Model model) {
        SubmittedRequestTableDTO requestsTableDTO = appointmentRequestService.getSubmittedRequestTable(userId);

        model.addAttribute("submittedRequestTable", requestsTableDTO);

        return "web_request_table";
    }
    public String getTypePage() {
        return "web_visit_type";
    }

    public String getPersonalVisitPage(Model model){
        AppointmentRequestRequestDTO appointmentRequestRequestDTO = appointmentRequestService.getPurposeDepartmentLists();
        AppointmentRequestResponseDTO appointmentRequestResponseDTO = new AppointmentRequestResponseDTO();
        List<VisitorDTO> visitors = new ArrayList<>();
        visitors.add(new VisitorDTO());
        appointmentRequestResponseDTO.setVisitors(visitors);
        model.addAttribute("visitor", appointmentRequestResponseDTO.getVisitors().get(0));
        model.addAttribute("appointmentRequestData", appointmentRequestRequestDTO);
        model.addAttribute("appointmentRequest", appointmentRequestResponseDTO);
        return "web_request_one";
    }

    public String getGroupVisitPage(Model model){
        AppointmentRequestRequestDTO appointmentRequestRequestDTO = appointmentRequestService.getPurposeDepartmentLists();
        AppointmentRequestResponseDTO appointmentRequestResponseDTO = new AppointmentRequestResponseDTO();

        appointmentRequestResponseDTO.setVisitors(new ArrayList<>());
        model.addAttribute("appointmentRequestData", appointmentRequestRequestDTO);
        model.addAttribute("appointmentRequest", appointmentRequestResponseDTO);
        return "web_request_many";
    }


}
