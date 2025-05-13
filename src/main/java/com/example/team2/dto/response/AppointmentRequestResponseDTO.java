package com.example.team2.dto.response;

import com.example.team2.dto.VisitorDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Valid
//с фронта
// данные для оформления заявки клиентом
public class AppointmentRequestResponseDTO {

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
    private List<VisitorDTO> visitors;

//    @NotNull
    private List<Integer> docs;

}
