package com.example.team2.dto.response;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Valid
//с фронта
// отправка времени, будем дергать методы для времени входа и времени ухода
public class GuardOfficerSetUpTimeResponseDTO {
    @NotNull
    private Long idRequest;
    @NotNull
    private LocalTime time; //при одной кнопки сохранить, это дата входа, при другой, это дата выхода

}
