package com.example.team2.service;

import com.example.team2.model.Department;
import com.example.team2.model.DepartmentWorker;
import com.example.team2.repository.DepartmentWorkerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentWorkerService {
    private final DepartmentWorkerRepository departmentWorkerRepository;

    public DepartmentWorkerService(DepartmentWorkerRepository departmentWorkerRepository) {
        this.departmentWorkerRepository = departmentWorkerRepository;
    }

    public DepartmentWorker findByWorkerNameAndDepartment(String workerName, Department department) {
        return departmentWorkerRepository.findByWorkerNameAndDepartment(workerName, department);
    }
}
