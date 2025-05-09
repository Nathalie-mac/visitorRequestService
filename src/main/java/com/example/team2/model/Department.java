package com.example.team2.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "department")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Department {

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    List<DepartmentWorker> workers;
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    List<Request> requests;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "department_name", nullable = false)
    private String departmentName;

}
