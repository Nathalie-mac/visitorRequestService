package com.example.team2.service;

import com.example.team2.model.*;
import com.example.team2.dto.response.AppointmentRequestResponseDTO;
import com.example.team2.dto.VisitorDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventSignUpService {
    private final PersonService personService;
    private final RequestService requestService;
    private final PassportDataService passportDataService;

    public void individualSingUp(AppointmentRequestResponseDTO appointmentRequestResponseDTO) { //TODO: Решить что тут возвращается (Скорее всего Request)
        Request request = requestService.createRequest(appointmentRequestResponseDTO);

        //Информация о посетителе
        List<VisitorDTO> visitorsDTO = appointmentRequestResponseDTO.getVisitors(); //TODO: изменить создание пользователей через LIST
        Person person = personService.createPerson(visitorsDTO.get(0), request);

        //PDF паспорта
        List<Integer> pDFsId = appointmentRequestResponseDTO.getDocs();
        for (Integer pDFId : pDFsId) {
            //PassportData passportData = passportDataService.createPassportData(pDFId, request);
            passportDataService.createPassportData(pDFId, request);
        }
    }

    public void groupSingUp(AppointmentRequestResponseDTO appointmentRequestResponseDTO) { //TODO: Решить что тут возвращается (Скорее всего Request)
        Request request = requestService.createRequest(appointmentRequestResponseDTO);

        //Информация о посетителе
        List<VisitorDTO> visitorsDTO = appointmentRequestResponseDTO.getVisitors();
        for (VisitorDTO visitorDTO : visitorsDTO) {
            //Person person = personService.createPerson(visitorDTO, request);
            personService.createPerson(visitorDTO, request);
        }

        //PDF паспортов
        List<Integer> pDFsId = appointmentRequestResponseDTO.getDocs();
        for (Integer pDFId : pDFsId) {
            //PassportData passportData = passportDataService.createPassportData(pDFId, request);
            passportDataService.createPassportData(pDFId, request);
        }
    }
}
