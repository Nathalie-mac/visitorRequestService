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
    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    List<Person> persons;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "type")
    //enumerated
    private AppointmentType appointmentType;
    @Column(name = "request_department")
    private LocalDate requestDate;
    @Column(name = "request_department")
    private LocalTime requestTime;
    @Enumerated(EnumType.STRING)
    @Column(name = "request_department", nullable = false)
    private StatusType status;
    @Enumerated(EnumType.STRING)
    @Column(name = "request_department")
    private RejectReason rejectReason;
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
    @ManyToOne
    @JoinColumn(name = "worker_id")
    private DepartmentWorker worker;
}
