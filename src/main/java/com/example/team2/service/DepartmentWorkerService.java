package com.example.team2.service;

import com.example.team2.model.Department;
import com.example.team2.model.DepartmentWorker;
import com.example.team2.repository.DepartmentWorkerRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DepartmentWorkerService {
    private final DepartmentWorkerRepository departmentWorkerRepository;

    public DepartmentWorker findByWorkerNameAndDepartment(String workerName, Department department) {
        return departmentWorkerRepository.findByWorkerNameAndDepartment(workerName, department);
    }

    public DepartmentWorker findById(long id) {
        return departmentWorkerRepository.findById(id);
    }

    public DepartmentWorker save(DepartmentWorker departmentWorker) {
        return departmentWorkerRepository.save(departmentWorker);
    }
}
