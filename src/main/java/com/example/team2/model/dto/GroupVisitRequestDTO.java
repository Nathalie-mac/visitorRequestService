package com.example.team2.model.dto;

import java.time.LocalDate;
import java.util.List;

public class GroupVisitRequestDTO {
    private LocalDate startApplicationPeriod;
    private LocalDate endApplicationPeriod;
    private String purposeVisit;
    private Long department;
    private Long workerName;
    private List<VisitorDTO> visitor;
    private List<Integer> docs;
}
