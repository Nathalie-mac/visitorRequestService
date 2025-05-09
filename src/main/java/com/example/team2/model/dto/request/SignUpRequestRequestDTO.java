package com.example.team2.model.dto.request;


import com.example.team2.model.dto.DepartmentDTO;
import com.example.team2.model.dto.VisitorDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
//на фронт
// данные для выпадающих списков, необходимых для формы оформления заявок
public class SignUpRequestRequestDTO {
    private List<String> purposeVisit;
    private List<DepartmentDTO> departments;
    private List<VisitorDTO> visitors;
    private List<Integer> docs;
}
