package com.example.team2.dto.request;


import com.example.team2.dto.DepartmentDTO;
import com.example.team2.dto.VisitorDTO;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
//на фронт
// данные для выпадающих списков, необходимых для формы оформления заявок
public class AppointmentRequestRequestDTO {

    @NotNull
    private List<String> purposeVisit;
    private List<DepartmentDTO> departments;
}
