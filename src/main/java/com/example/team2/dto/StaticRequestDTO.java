package com.example.team2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
// для статического отображения заявки, которое нельзя редактировать
public class StaticRequestDTO {
    private Long idRequest;
    private LocalDate startApplicationPeriod;
    private LocalDate endApplicationPeriod;
    private String purposeVisit;
    private String department;
    private String workerName;
    private List<VisitorDTO> visitors;
    private List<Integer> docs;
}
