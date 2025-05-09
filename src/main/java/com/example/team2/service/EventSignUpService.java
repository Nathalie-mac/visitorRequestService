package com.example.team2.service;

import com.example.team2.model.*;
import com.example.team2.model.dto.GroupVisitRequestDTO;
import com.example.team2.model.dto.IndividualVisitRequestDTO;
import com.example.team2.model.dto.VisitorDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventSignUpService {
    private final DepartmentService departmentService;
    private final DepartmentWorkerService departmentWorkerService;
    private final PersonService personService;
    private final RequestService requestService;

    public void individualSingUp(IndividualVisitRequestDTO individualVisitRequestDTO) { //TODO: Решить что тут возвращается (Скорее всего Request)
        Request request = new Request();
        request.setAppointmentType(AppointmentType.INDIVIDUAL);

        //Информация для пропуска
        request.setRequestStartDate(individualVisitRequestDTO.getStartApplicationPeriod());
        request.setRequestStartDate(individualVisitRequestDTO.getEndApplicationPeriod());
        request.setPurpose(AppointmentPurpose.valueOf(individualVisitRequestDTO.getPurposeVisit()));

        //Принимающая сторона
        Department department = departmentService.findById(individualVisitRequestDTO.getDepartment());
        request.setDepartment(department);
        DepartmentWorker departmentWorker = departmentWorkerService.findById(individualVisitRequestDTO.getWorkerName());
        request.setWorker(departmentWorker);

        //Информация о посетителе
        VisitorDTO visitorDTO = individualVisitRequestDTO.getVisitor();
        Person person = personService.createPerson(visitorDTO);

        List<Person> persons = new ArrayList<>();
        persons.add(person);
        request.setPersons(persons);

        //PDF паспорта
        //request.

        requestService.save(request);
    }

    public void groupSingUp(GroupVisitRequestDTO groupVisitRequestDTO) { //TODO: Решить что тут возвращается (Скорее всего Request)
        Request request = new Request();
        request.setAppointmentType(AppointmentType.PUBLIC);

        //Информация для пропуска
        request.setRequestStartDate(groupVisitRequestDTO.getStartApplicationPeriod());
        request.setRequestStartDate(groupVisitRequestDTO.getEndApplicationPeriod());
        request.setPurpose(AppointmentPurpose.valueOf(groupVisitRequestDTO.getPurposeVisit()));

        //Принимающая сторона
        Department department = departmentService.findById(groupVisitRequestDTO.getDepartment());
        request.setDepartment(department);
        DepartmentWorker departmentWorker = departmentWorkerService.findById(groupVisitRequestDTO.getWorkerName());
        request.setWorker(departmentWorker);

        //Информация о посетителе
        List<VisitorDTO> visitorsDTO = groupVisitRequestDTO.getVisitor();
        List<Person> persons = new ArrayList<>();
        for (VisitorDTO visitorDTO : visitorsDTO) {
            Person person = personService.createPerson(visitorDTO);
            persons.add(person);
        }

        request.setPersons(persons);
    }
}
