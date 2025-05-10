package com.example.team2.service;

import com.example.team2.dto.DepartmentDTO;
import com.example.team2.model.Department;
import com.example.team2.model.DepartmentWorker;
import com.example.team2.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public Department save(Department department) {
        return departmentRepository.save(department);
    }

    public Department findById(long id) {
        return departmentRepository.findById(id);
    }

    public List<Department> findAll() {
        return departmentRepository.findAll();
    }

    public List<DepartmentDTO> getDepartmentsDTOs() {
        List<DepartmentDTO> departmentDTOS = new ArrayList<>();
        List<Department> departments = findAll();

        for (Department department : departments) {
            DepartmentDTO departmentDTO = new DepartmentDTO();

            departmentDTO.setId(department.getId());
            departmentDTO.setName(departmentDTO.getName());

            Map<String, Long> workers = new HashMap<>();
            List<DepartmentWorker> departmentWorkers = department.getWorkers();
            for (DepartmentWorker departmentWorker : departmentWorkers) {
                workers.put(departmentWorker.getWorkerName(), departmentWorker.getId());
            }
            departmentDTO.setDepartmentWorkers(workers);

            departmentDTOS.add(departmentDTO);
        }

        return departmentDTOS;
    }

    public List<String> getDepartmentNames(){
        return departmentRepository.findDistinctDepartmentNameBy();
    }
}
