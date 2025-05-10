package com.example.team2.model;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "request")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "appointment_type", nullable = false)
    private AppointmentType appointmentType;

    //назначенные дата и время посещения, терминал сотрудника общего отдела
    @Column(name = "request_date")
    private LocalDate requestDate;

    @Column(name = "request_time")
    private LocalTime requestTime;

    //реальное время захода и ухода, терминал сотрудника охраны
    @Column(name = "actual_enter_time")
    private LocalTime actualEnterTime;

    @Column(name = "actual_exit_time")
    private LocalTime actualExitTime;


    //желаемые даты посещения, веб-сервис заказа пропуска
    @Column(name = "request_start_date", nullable = false)
//    @Check(name = "request_start_date",
//            constraints = "request_start_date >= (CURRENT_DATE + INTERVAL '1 day') AND" +
//            " request_start_date <= (CURRENT_DATE + INTERVAL '15 days')")
    private LocalDate requestStartDate;

    @Column(name = "request_end_date", nullable = false)
//    @Check(name = "request_end_date",
//            constraints = "request_end_date >= request_start_date AND" +
//            " request_end_date <= (request_start_date + INTERVAL '15 days')")
    private LocalDate requestEndDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "purpose", nullable = false)
    private AppointmentPurpose purpose;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StatusType status;

    @Enumerated(EnumType.STRING)
    @Column(name = "reject_reason")
    private RejectReason rejectReason;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @ManyToOne
    @JoinColumn(name = "worker_id")
    private DepartmentWorker worker;

    @OneToMany(mappedBy = "request", cascade = CascadeType.ALL)
    List<Person> persons;

    @OneToMany(mappedBy = "request", cascade = CascadeType.ALL)
    List<PassportData> passportData;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
