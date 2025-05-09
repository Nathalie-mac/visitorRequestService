package com.example.team2.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "department_worker")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentWorker {

    //@OneToMany(mappedBy = "department_worker", cascade = CascadeType.ALL)
    @OneToMany(mappedBy = "worker", cascade = CascadeType.ALL)
    List<Request> requests;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "worker_name", nullable = false)
    //@Check(name = "worker_name", constraints = "worker_name ~ '^[А-ЯЁ][а-яё]+\\s[А-ЯЁ]\\.[А-ЯЁ]\\.$'")
    private String workerName;
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

}
