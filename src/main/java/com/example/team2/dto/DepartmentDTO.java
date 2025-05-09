package com.example.team2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
// подразделение для выпадающего списка сотрудников
public class DepartmentDTO {
    private Long id;
    private String name;
    private Map<String, Long> departmentWorkers;
}
