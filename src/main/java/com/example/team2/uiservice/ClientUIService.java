package com.example.team2.uiservice;

import com.example.team2.auth.services.AuthService;
import com.example.team2.dto.request.FilterListDTO;
import com.example.team2.dto.request.RequestsTableDTO;
import com.example.team2.dto.request.SubmittedRequestTableDTO;
import com.example.team2.service.AppointmentRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

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


}
