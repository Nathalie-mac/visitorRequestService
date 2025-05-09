package com.example.team2.repository;

import com.example.team2.model.Department;
import com.example.team2.model.DepartmentWorker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentWorkerRepository extends JpaRepository<DepartmentWorker, Integer> {
    DepartmentWorker findByWorkerNameAndDepartment(String workerName, Department department);
    DepartmentWorker findById(long id);
}