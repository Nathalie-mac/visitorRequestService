package com.example.team2.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "department_worker")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentWorker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "worker_name")
    private String workerName;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
}
