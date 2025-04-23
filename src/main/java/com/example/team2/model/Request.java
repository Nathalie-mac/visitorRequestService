package com.example.team2.model;


import jakarta.persistence.*;
import lombok.*;

import javax.xml.crypto.KeySelector;
import java.time.LocalDate;
import java.time.LocalDateTime;
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

    @Column(name = "request_date", nullable = false)
    private LocalDate requestDate;

    @Column(name = "request_time", nullable = false)
    private LocalTime requestTime;

    @Column(name = "request_start_date", nullable = false)
    private LocalDate requestStartDate;

    @Column(name = "request_end_date", nullable = false)
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

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    List<Person> persons;
}
