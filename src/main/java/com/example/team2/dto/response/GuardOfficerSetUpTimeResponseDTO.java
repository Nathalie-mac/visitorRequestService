package com.example.team2.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
//с фронта
// отправка времени, будем дергать методы для времени входа и времени ухода
public class GuardOfficerSetUpTimeResponseDTO {
    private Long idRequest;
    private LocalTime time; //при одной кнопки сохранить, это дата входа, при другой, это дата выхода

}
