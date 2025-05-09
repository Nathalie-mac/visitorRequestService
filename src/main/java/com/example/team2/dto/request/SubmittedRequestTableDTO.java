package com.example.team2.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
//на фронт
//веб-сервис, страница "Заявки", таблица раннее поданных заявок
public class SubmittedRequestTableDTO {

    private List<SubmittedRequestRowDTO> submittedRequestList;
}
