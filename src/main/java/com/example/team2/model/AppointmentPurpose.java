package com.example.team2.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AppointmentPurpose {
    BUSINESS("бизнес"),
    EXCURSION("экскурсия"),
    TRAINING("обучение"),
    MAINTENANCE("ремонтные работы");

    private final String purpose;
}
