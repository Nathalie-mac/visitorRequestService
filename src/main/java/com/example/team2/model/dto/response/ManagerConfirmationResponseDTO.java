package com.example.team2.model.dto.response;

import com.example.team2.model.dto.VisitorDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
//с фронта
//данные, которые ввел менеджер, на отклонение/одобрение
public class ManagerConfirmationResponseDTO {
    private Long idRequest;
    private String status;
    private String rejectReason;
    private LocalDate visitDate;
    private LocalTime visitTime;
}
