package com.example.team2.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupVisitRequestDTO {
    private LocalDate startApplicationPeriod;
    private LocalDate endApplicationPeriod;
    private String purposeVisit;
    private Long department;
    private Long workerName;
    private List<VisitorDTO> visitor;
    private List<Integer> docs;
}
