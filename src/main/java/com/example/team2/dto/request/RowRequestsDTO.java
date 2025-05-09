package com.example.team2.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
//на фронт
//терминал менеджера и охранника, страница "оформление заявок", строка для таблицы
public class RowRequestsDTO {

    private Long idRequest;  //при нажатии кнопки "открыть заявку", отправляем id в метод открытия "Проверка заявки"
    private String appointmentType;
    private List<String> userNames; //ФИО пользователей
    private String department;
    private LocalDate date;
    private LocalTime time;
    private String status;
}
