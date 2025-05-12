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

        //Запрос на мероприятие
        Request request2 = new Request();
        request2.setAppointmentType(AppointmentType.PUBLIC);
        request2.setRequestStartDate(LocalDate.parse("2025-01-01"));
        request2.setRequestEndDate(LocalDate.parse("2025-01-10"));
        request2.setPurpose(AppointmentPurpose.BUSINESS);
        request2.setStatus(StatusType.PENDING);
        request2.setDepartment(department);
        request2.setWorker(departmentWorker);
        request2.setUser(user);
        requestService.save(request2);

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


        Person person2 = new Person();
        person2.setFirstName("Имя2");
        person2.setMiddleName("Отчество2");
        person2.setLastName("Фамилия2");
        person2.setPhone("+7(800)555-35-35");
        person2.setEmail("test2@gmail.com");
        person2.setNote("Тестовая записка");
        person2.setOrganization("Тестовая организация");
        person2.setBirthDate(LocalDate.parse("1992-10-23"));
        person2.setPassportSery("1224");
        person2.setPassportNumber("566890");
        person2.setPhoto(2);
        person2.setBlackList(false);
        person2.setRequest(request2);
        personService.save(person2);

        Person person3 = new Person();
        person3.setFirstName("Алексей");
        person3.setMiddleName("Сергеевич");
        person3.setLastName("Иванов");
        person3.setPhone("+7(912)345-67-89");
        person3.setEmail("alex.ivanov@gmail.com");
        person3.setNote("Клиент VIP");
        person3.setOrganization("ООО Рога и Копыта");
        person3.setBirthDate(LocalDate.parse("1985-07-15"));
        person3.setPassportSery("4501");
        person3.setPassportNumber("987654");
        person3.setPhoto(3);
        person3.setBlackList(false);
        person3.setRequest(request2);
        personService.save(person3);

        Person person4 = new Person();
        person4.setFirstName("Екатерина");
        person4.setMiddleName("Викторовна");
        person4.setLastName("Смирнова");
        person4.setPhone("+7(923)456-78-90");
        person4.setEmail("k.smirnova@mail.ru");
        person4.setNote("Постоянный клиент");
        person4.setOrganization("ИП Смирнова");
        person4.setBirthDate(LocalDate.parse("1990-03-22"));
        person4.setPassportSery("5002");
        person4.setPassportNumber("123456");
        person4.setPhoto(4);
        person4.setBlackList(false);
        person4.setRequest(request2);
        personService.save(person4);

        Person person5 = new Person();
        person5.setFirstName("Дмитрий");
        person5.setMiddleName("Анатольевич");
        person5.setLastName("Петров");
        person5.setPhone("+7(934)567-89-01");
        person5.setEmail("dmitry.petrov@yandex.ru");
        person5.setNote("Новый клиент");
        person5.setOrganization("ЗАО ТехноПром");
        person5.setBirthDate(LocalDate.parse("1978-11-30"));
        person5.setPassportSery("6003");
        person5.setPassportNumber("654321");
        person5.setPhoto(5);
        person5.setBlackList(false);
        person5.setRequest(request2);
        personService.save(person5);

        Person person6 = new Person();
        person6.setFirstName("Ольга");
        person6.setMiddleName("Игоревна");
        person6.setLastName("Кузнецова");
        person6.setPhone("+7(945)678-90-12");
        person6.setEmail("olga.kuznetsova@gmail.com");
        person6.setNote("Частый заказчик");
        person6.setOrganization("ООО СтройГарант");
        person6.setBirthDate(LocalDate.parse("1982-05-18"));
        person6.setPassportSery("7004");
        person6.setPassportNumber("789012");
        person6.setPhoto(6);
        person6.setBlackList(false);
        person6.setRequest(request2);
        personService.save(person6);

        Person person7 = new Person();
        person7.setFirstName("Артем");
        person7.setMiddleName("Валерьевич");
        person7.setLastName("Соколов");
        person7.setPhone("+7(956)789-01-23");
        person7.setEmail("artem.sokolov@mail.ru");
        person7.setNote("Корпоративный клиент");
        person7.setOrganization("АО ГазНефть");
        person7.setBirthDate(LocalDate.parse("1995-09-10"));
        person7.setPassportSery("8005");
        person7.setPassportNumber("890123");
        person7.setPhoto(7);
        person7.setBlackList(false);
        person7.setRequest(request2);
        personService.save(person7);

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