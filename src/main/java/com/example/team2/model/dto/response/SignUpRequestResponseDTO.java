package com.example.team2.model.dto.response;

import com.example.team2.model.dto.VisitorDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
//с фронта
// данные для оформления заявки клиентом
public class SignUpRequestResponseDTO {

    private LocalDate startApplicationPeriod;
    private LocalDate endApplicationPeriod;
    private String purposeVisit;
    private Long department;
    private Long workerName;
    private List<VisitorDTO> visitors;
    private List<Integer> docs;

}
