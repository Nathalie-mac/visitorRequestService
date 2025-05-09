package com.example.team2.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
//на фронт
//списки для фильтров на страницах менеджера и охранника
public class FilterListDTO {

    private List<String> appointmentTypeList;
    private List<String> departmentList;
    private List<String> statusList;
}
