package com.example.team2.dto.request;

import com.example.team2.dto.StaticRequestDTO;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
//на фронт
//данные, для указания времени охранником
public class GuardOfficerSetUpTimeRequestDTO {

    @NotNull
    private boolean inBlackList;
    @NotNull
    private StaticRequestDTO requestDTO;
    private LocalTime enterTime;            // если есть оно отображается, если нет, то нет
    private LocalTime exitTime;             // если есть оно отображается, если нет, то нет
}
