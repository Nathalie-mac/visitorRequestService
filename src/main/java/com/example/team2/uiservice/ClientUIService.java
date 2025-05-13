package com.example.team2.uiservice;

import com.example.team2.auth.services.AuthService;
import com.example.team2.dto.VisitorDTO;
import com.example.team2.dto.hotass.AppointmentRequestResponseHelpDTO;
import com.example.team2.dto.request.AppointmentRequestRequestDTO;
import com.example.team2.dto.request.FilterListDTO;
import com.example.team2.dto.request.RequestsTableDTO;
import com.example.team2.dto.request.SubmittedRequestTableDTO;
import com.example.team2.dto.response.AppointmentRequestResponseDTO;
import com.example.team2.dto.response.ManagerConfirmationResponseDTO;
import com.example.team2.service.AppointmentRequestService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;

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

    public String getPersonalVisitPage(Model model, String cookieHeader){
        AppointmentRequestRequestDTO appointmentRequestRequestDTO = appointmentRequestService.getPurposeDepartmentLists();
        AppointmentRequestResponseHelpDTO appointmentRequestResponseDTO = new AppointmentRequestResponseHelpDTO();



        appointmentRequestResponseDTO.setVisitor(new VisitorDTO());
        appointmentRequestResponseDTO.setUserId(authService.getUserIdFromCookie(cookieHeader));

        model.addAttribute("appointmentRequestData", appointmentRequestRequestDTO);
        model.addAttribute("appointmentRequest", appointmentRequestResponseDTO);
        // Добавляем departments как отдельный атрибут
        model.addAttribute("departments", appointmentRequestRequestDTO.getDepartments());
        return "web_request_one";
    }

    public String getGroupVisitPage(Model model, String cookieHeader){
        AppointmentRequestRequestDTO appointmentRequestRequestDTO = appointmentRequestService.getPurposeDepartmentLists();
        AppointmentRequestResponseDTO appointmentRequestResponseDTO = new AppointmentRequestResponseDTO();

        appointmentRequestResponseDTO.setUserId(authService.getUserIdFromCookie(cookieHeader));
        appointmentRequestResponseDTO.setVisitors(new ArrayList<>());

        model.addAttribute("appointmentRequestData", appointmentRequestRequestDTO);
        model.addAttribute("appointmentRequest", appointmentRequestResponseDTO);
        // Добавляем departments как отдельный атрибут
        model.addAttribute("departments", appointmentRequestRequestDTO.getDepartments());
        return "web_request_many";
    }
    public String createNewPersonalRequest(AppointmentRequestResponseHelpDTO responseDTO) {
        AppointmentRequestResponseDTO appointmentRequestResponseDTO = new AppointmentRequestResponseDTO();
        List<VisitorDTO> visitors = new ArrayList<>();
        visitors.add(responseDTO.getVisitor());
        appointmentRequestResponseDTO.setVisitors(visitors);
        appointmentRequestResponseDTO.setDepartment(responseDTO.getDepartment());
        appointmentRequestResponseDTO.setDocs(responseDTO.getDocs());
        appointmentRequestResponseDTO.setPurposeVisit(responseDTO.getPurposeVisit());
        appointmentRequestResponseDTO.setWorkerName(responseDTO.getWorkerName());
        appointmentRequestResponseDTO.setUserId(responseDTO.getUserId());
        appointmentRequestResponseDTO.setStartApplicationPeriod(responseDTO.getStartApplicationPeriod());
        appointmentRequestResponseDTO.setEndApplicationPeriod(responseDTO.getEndApplicationPeriod());
        appointmentRequestResponseDTO.setDocs(new ArrayList<>());
        appointmentRequestService.createIndividualAppointment(appointmentRequestResponseDTO);
        return "redirect:/client/main/" + responseDTO.getUserId();
    }
    public String createNewGroupRequest(AppointmentRequestResponseDTO responseDTO) {

        appointmentRequestService.createPublicAppointment(responseDTO);
        return "redirect:/client/main/" + responseDTO.getUserId();
    }


}
