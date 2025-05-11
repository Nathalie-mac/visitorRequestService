package com.example.team2.dto.request;

import com.example.team2.dto.StaticRequestDTO;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
//на фронт
//данные, для одобрения/отклонения заявок менеджером
public class ManagerConfirmationRequestDTO {
    @NotNull
    private boolean inBlackList;
    private StaticRequestDTO requestDTO;
    @NotNull
    private List<String> statusList; //Отклонено|Одобрено (для выпадающего списка)
    @NotNull
    private String status;
    @NotNull
    private List<String> rejectReasonList; //причины отклонения (для выпадающего списка), в нем присутствует причина "Черный список", но у менеджера он не отображается
    @NotNull
    private String rejectReason;
    private LocalDate visitDate;            // если есть оно отображается, если нет, то нет
    private LocalTime visitTime;            // если есть оно отображается, если нет, то нет
}
