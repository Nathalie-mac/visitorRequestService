package com.example.team2.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IndividualVisitRequestDTO {
    private LocalDate startApplicationPeriod;
    private LocalDate endApplicationPeriod;
    private String purposeVisit;
    private String department;
    private String workerName;
    private String visitorFirstName;
    private String visitorLastName;
    private String visitorMiddleName;
    private String visitorPhoneNumber;
    private String visitorEmail;
    private String visitorOrganizationName;
    private String visitorNote;
    private LocalDate visitorBirthDate;
    private String visitorPassportSeries;
    private String visitorPassportNumber;
    private int visitorPhoto;
    private List<Integer> visitorDocs;

}
