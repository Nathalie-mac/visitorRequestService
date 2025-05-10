package com.example.team2.dto.response;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
//с фронта
//данные, которые ввел менеджер, на отклонение/одобрение
public class ManagerConfirmationResponseDTO {
    @NotNull
    private Long idRequest;
    @NotNull
    private String status;

    private String rejectReason;

    private LocalDate visitDate; //TODO: изменить наименования для соответствия Entity
    private LocalTime visitTime;
}
