package com.example.team2.service;

import com.example.team2.dto.request.AppointmentRequestRequestDTO;
import com.example.team2.dto.request.SubmittedRequestRowDTO;
import com.example.team2.dto.request.SubmittedRequestTableDTO;
import com.example.team2.model.*;
import com.example.team2.dto.response.AppointmentRequestResponseDTO;
import com.example.team2.dto.VisitorDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentRequestService {
    private final PersonService personService;
    private final RequestService requestService;
    private final PassportDataService passportDataService;
    private final DepartmentService departmentService;
    private final UserService userService;


    public Request createIndividualAppointment(AppointmentRequestResponseDTO signUpRequestResponseDTO) {
        Request request = requestService.createRequest(signUpRequestResponseDTO, AppointmentType.INDIVIDUAL);

        //Информация о посетителе
        List<VisitorDTO> visitorsDTO = signUpRequestResponseDTO.getVisitors();
        Person person = personService.createPerson(visitorsDTO.get(0), request);

        //PDF паспорта
        List<Integer> pDFsId = signUpRequestResponseDTO.getDocs();
        for (Integer pDFId : pDFsId) {
            //PassportData passportData = passportDataService.createPassportData(pDFId, request);
            passportDataService.createPassportData(pDFId, request);
        }

        return request;
    }

    public Request createPublicAppointment(AppointmentRequestResponseDTO signUpRequestResponseDTO) {
        Request request = requestService.createRequest(signUpRequestResponseDTO, AppointmentType.PUBLIC);

        //Информация о посетителе
        List<VisitorDTO> visitorsDTO = signUpRequestResponseDTO.getVisitors();
        for (VisitorDTO visitorDTO : visitorsDTO) {
            //Person person = personService.createPerson(visitorDTO, request);
            personService.createPerson(visitorDTO, request);
        }

        //PDF паспортов
        List<Integer> pDFsId = signUpRequestResponseDTO.getDocs();
        for (Integer pDFId : pDFsId) {
            //PassportData passportData = passportDataService.createPassportData(pDFId, request);
            passportDataService.createPassportData(pDFId, request);
        }

        return request;
    }

    public AppointmentRequestRequestDTO getPurposeDepartmentLists() {
        AppointmentRequestRequestDTO signUpRequestRequestDTO = new AppointmentRequestRequestDTO();

        List<String> appointmentPurpose = Arrays.stream(AppointmentPurpose.values()).map(AppointmentPurpose::getPurpose).toList();
        signUpRequestRequestDTO.setPurposeVisit(appointmentPurpose);

        signUpRequestRequestDTO.setDepartments(departmentService.getDepartmentsDTOs());

        return  signUpRequestRequestDTO;
    }

    public SubmittedRequestTableDTO getSubmittedRequestTable(long userId) {
        SubmittedRequestTableDTO submittedRequestTableDTO = new SubmittedRequestTableDTO();
        User user = userService.findUserById(userId);
        List<Request> requests = requestService.findByUser(user);

        List<SubmittedRequestRowDTO> submittedRequestRowDTOs = new ArrayList<>();
        for (Request request : requests) {
            SubmittedRequestRowDTO submittedRequestRowDTO = new SubmittedRequestRowDTO();

            submittedRequestRowDTO.setAppointmentType(request.getAppointmentType().getType());
            submittedRequestRowDTO.setDepartment(request.getDepartment().getDepartmentName());
            submittedRequestRowDTO.setStatus(request.getStatus().getStatusType());

            submittedRequestRowDTO.setTime(request.getRequestTime());
            submittedRequestRowDTO.setDate(request.getRequestDate());

            submittedRequestRowDTOs.add(submittedRequestRowDTO);
        }

        submittedRequestTableDTO.setSubmittedRequestList(submittedRequestRowDTOs);

        return submittedRequestTableDTO;
    }
}
