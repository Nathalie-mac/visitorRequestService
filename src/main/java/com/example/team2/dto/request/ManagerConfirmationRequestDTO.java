package com.example.team2.dto.request;

import com.example.team2.dto.StaticRequestDTO;
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
    private boolean inBlackList;
    private StaticRequestDTO requestDTO;
    private List<String> status;            //Отклонено|Одобрено (для выпадающего списка)
    private List<String> rejectReason;      //причины отклонения (для выпадающего списка), в нем присутствует причина "Черный список", но у менеджера он не отображается
    private LocalDate visitDate;            // если есть оно отображается, если нет, то нет
    private LocalTime visitTime;            // если есть оно отображается, если нет, то нет
}
