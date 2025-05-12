package com.example.team2.dto;

import com.example.team2.model.email.ValidEmail;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Valid
public class VisitorDTO {
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private String middleName;

    private String phoneNumber;

    @NotNull
    @ValidEmail
    private String email;

    private String organizationName;

    @NotNull
    private String note;

    @NotNull
    private LocalDate birthDate;

    @NotNull
    @Size(min = 4,max = 4)
    private String passportSeries;

    @NotNull
    @Size(min = 6,max = 6)
    private String passportNumber;

    @NotNull
    private int photo;
}
