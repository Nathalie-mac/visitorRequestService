package com.example.team2.utils;

import com.example.team2.model.Department;
import com.example.team2.service.DepartmentService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InitiateUtils implements CommandLineRunner {

    private final DepartmentService departmentService;

    public InitiateUtils(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @Override
    public void run(String... args) throws Exception {
        Department department1 = new Department();
        department1.setDepartmentName("dep1");

        Department department2 = new Department();
        department2.setDepartmentName("dep2");

        Department department3 = new Department();
        department3.setDepartmentName("dep3");

        departmentService.save(department1);
        departmentService.save(department2);
        departmentService.save(department3);

        List<Department> departments = departmentService.getAll();

        for (Department department : departments) {
            System.out.println(department.getDepartmentName());
        }

        List<Department> found = departmentService.findByDepartmentName("dep2");

        for (Department deps : found) {
            System.out.println(deps.getDepartmentName());
        }
    }
}
