package com.example.team2.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Valid
//на фронт
//списки для фильтров на страницах менеджера и охранника
public class FilterListDTO {
    @NotNull
    private List<String> appointmentTypeList;

    private List<String> departmentList;

    @NotNull
    private List<String> statusList;
}
