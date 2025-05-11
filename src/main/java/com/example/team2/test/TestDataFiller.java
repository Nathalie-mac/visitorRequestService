package com.example.team2.test;

import com.example.team2.auth.config.BCrypt.BCryptPasswordEncoder;
import com.example.team2.model.*;
import com.example.team2.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
// spring_profiles_active=test в edit configuration стратегия игнора: https://www.baeldung.com/spring-profiles
public class TestDataFiller {

   //private final RoleRepository repositoryRole;
    private final DepartmentService departmentService;
    private final DepartmentWorkerService departmentWorkerService;
    private final PersonService personService;
    private final PassportDataService passportDataService;
    private final StuffService stuffService;
    private final UserService userService;
    private final RequestService requestService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * при запуске приложения заполняет БД тестовыми данными
     */
    @EventListener(ApplicationReadyEvent.class)
    public void fillRoleEntities() {

        //Департамент
        Department department = new Department();
        department.setDepartmentName("Тестовый департамент");
        departmentService.save(department);

        //Рабочий департамента
        DepartmentWorker departmentWorker = new DepartmentWorker();
        departmentWorker.setWorkerName("Тестов Тест Тестович");
        departmentWorker.setDepartment(department);
        departmentWorkerService.save(departmentWorker);

        //Пользователь
        User user = userService.createUser("sa@gmail.com", bCryptPasswordEncoder.encode("sa"));

        //Запрос на мероприятие
        Request request = new Request();
        request.setAppointmentType(AppointmentType.INDIVIDUAL);
        request.setRequestStartDate(LocalDate.parse("2025-01-01"));
        request.setRequestEndDate(LocalDate.parse("2025-01-10"));
        request.setPurpose(AppointmentPurpose.BUSINESS);
        request.setStatus(StatusType.PENDING);
        request.setDepartment(department);
        request.setWorker(departmentWorker);
        request.setUser(user);
        requestService.save(request);

        //Пользователь
        Person person = new Person();
        person.setFirstName("Имя");
        person.setMiddleName("Отчество");
        person.setLastName("Фамилия");
        person.setPhone("+7(800)555-35-35");
        person.setEmail("test@gmail.com");
        person.setNote("Тестовая записка");
        person.setOrganization("Тестовая организация");
        person.setBirthDate(LocalDate.parse("1992-10-23"));
        person.setPassportSery("1234");
        person.setPassportNumber("567890");
        person.setPhoto(1);
        person.setBlackList(false);
        person.setRequest(request);
        personService.save(person);

        //PDF паспорта
        PassportData passportData = new PassportData();
        passportData.setPassportId(1);
        passportData.setRequest(request);
        passportDataService.save(passportData);

        //Аккаунт рабочего
        //Менеджер
        stuffService.createStuff("11", bCryptPasswordEncoder.encode("11"), StuffRoleType.MANAGER);
        //Охранник
        stuffService.createStuff("22", bCryptPasswordEncoder.encode("22"), StuffRoleType.GUARD_OFFICER);

    }
}