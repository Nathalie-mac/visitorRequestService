package com.example.team2.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
//на фронт
//терминал менеджера и охранника, страница "оформление заявок", таблица
public class RequestsTableDTO {

    private List<RowRequestsDTO> registrationApplicationTable;
}
