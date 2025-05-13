package com.example.team2.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Valid
// подразделение для выпадающего списка сотрудников
public class DepartmentDTO {
    @NotNull
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private Map<String, Long> departmentWorkers;
}
