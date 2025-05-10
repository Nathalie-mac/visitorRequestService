package com.example.team2.service;

import com.example.team2.dto.request.SignUpRequestRequestDTO;
import com.example.team2.model.*;
import com.example.team2.dto.response.SignUpRequestResponseDTO;
import com.example.team2.dto.VisitorDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AppointmentRequestService { //TODO: допилить set для user в RequestService
    private final PersonService personService;
    private final RequestService requestService;
    private final PassportDataService passportDataService;
    private final DepartmentService departmentService;


    public Request createIndividualAppointment(SignUpRequestResponseDTO signUpRequestResponseDTO) {
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

    public Request createPublicAppointment(SignUpRequestResponseDTO signUpRequestResponseDTO) {
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

    public SignUpRequestRequestDTO getPurposeDepartmentLists() { //TODO: менее конченное название
        SignUpRequestRequestDTO signUpRequestRequestDTO = new SignUpRequestRequestDTO();

        List<String> appointmentPurpose = Arrays.stream(AppointmentPurpose.values()).map(AppointmentPurpose::getPurpose).toList();
        signUpRequestRequestDTO.setPurposeVisit(appointmentPurpose);

        signUpRequestRequestDTO.setDepartments(departmentService.getDepartmentsDTOs());

        return  signUpRequestRequestDTO;
    }
}
