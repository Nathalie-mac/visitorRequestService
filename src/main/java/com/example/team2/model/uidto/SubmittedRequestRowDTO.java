package com.example.team2.model.uidto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
//на фронт
//веб-сервис, страница "Заявки", строка для таблицы раннее поданных заявок
public class SubmittedRequestRowDTO {

    private String appointmentType;     //тип заявки
    private String department;
    private LocalDate date;
    private LocalTime time;
    private String status;
}
