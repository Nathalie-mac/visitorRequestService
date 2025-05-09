package com.example.team2.repository;

import com.example.team2.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {
    List<Department> findByDepartmentName(String departmentName);
    Department findById(long id);
}
