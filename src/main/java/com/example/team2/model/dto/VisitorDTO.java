package com.example.team2.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VisitorDTO {
    private String firstName;
    private String lastName;
    private String middleName;
    private String phoneNumber;
    private String email;
    private String organizationName;
    private String note;
    private LocalDate birthDate;
    private String passportSeries;
    private String passportNumber;
    private int photo;
}
