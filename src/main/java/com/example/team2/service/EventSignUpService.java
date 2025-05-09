package com.example.team2.service;

import com.example.team2.model.*;
import com.example.team2.model.dto.GroupVisitRequestDTO;
import com.example.team2.model.dto.IndividualVisitRequestDTO;
import com.example.team2.model.dto.VisitorDTO;
import com.example.team2.repository.RequestRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventSignUpService {
    private final DepartmentService departmentService;
    private final DepartmentWorkerService departmentWorkerService;
    private final PersonService personService;
    private final RequestService requestService;

    EventSignUpService(DepartmentService departmentService,
                       DepartmentWorkerService departmentWorkerService,
                       PersonService personService,
                       RequestService requestService) {
        this.departmentService = departmentService;
        this.departmentWorkerService = departmentWorkerService;
        this.personService = personService;
        this.requestService = requestService;
    }

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
        Person person = new Person();
        VisitorDTO visitorDTO = individualVisitRequestDTO.getVisitor();
        person.setLastName(visitorDTO.getLastName());
        person.setFirstName(visitorDTO.getFirstName());
        person.setMiddleName(visitorDTO.getMiddleName());
        person.setPhone(visitorDTO.getPhoneNumber());
        person.setEmail(visitorDTO.getEmail());
        person.setOrganization(visitorDTO.getOrganizationName());
        person.setNote(visitorDTO.getNote());
        person.setBirthDate(visitorDTO.getBirthDate());
        person.setPassportSery(visitorDTO.getPassportSeries());
        person.setPassportNumber(visitorDTO.getPassportNumber());

        //person.setPassportPdf(visitorDTO); //TODO: перенести PDF паспорта из

        personService.save(person);
        List<Person> persons = new ArrayList<>();
        persons.add(person);
        request.setPersons(persons);

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
            Person person = new Person();
            person.setLastName(visitorDTO.getLastName());
            person.setFirstName(visitorDTO.getFirstName());
            person.setMiddleName(visitorDTO.getMiddleName());
            person.setPhone(visitorDTO.getPhoneNumber());
            person.setEmail(visitorDTO.getEmail());
            person.setOrganization(visitorDTO.getOrganizationName());
            person.setNote(visitorDTO.getNote());
            person.setBirthDate(visitorDTO.getBirthDate());
            person.setPassportSery(visitorDTO.getPassportSeries());
            person.setPassportNumber(visitorDTO.getPassportNumber());
            personService.save(person);

            persons.add(person);
        }

        request.setPersons(persons);
    }
}
