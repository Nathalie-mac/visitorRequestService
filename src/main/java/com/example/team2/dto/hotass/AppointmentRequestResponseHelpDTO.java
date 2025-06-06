package com.example.team2.dto.hotass;

import com.example.team2.dto.VisitorDTO;
import jakarta.validation.constraints.NotNull;
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
public class AppointmentRequestResponseHelpDTO {

    @NotNull
    private Long userId;

    @NotNull
    private LocalDate startApplicationPeriod;

    @NotNull
    private LocalDate endApplicationPeriod;

    @NotNull
    private String purposeVisit;

    @NotNull
    private Long department;

    @NotNull
    private Long workerName;

    @NotNull
    private VisitorDTO visitor;

    @NotNull
    private List<Integer> docs;

}
