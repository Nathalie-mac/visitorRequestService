package com.example.team2.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum AppointmentPurpose {
    BUSINESS("бизнес"),
    EXCURSION("экскурсия"),
    TRAINING("обучение"),
    MAINTENANCE("ремонтные работы");

    private final String purpose;

    public AppointmentPurpose getEnum(String string) {
        return Arrays.stream(AppointmentPurpose.values())
                .filter(purpose -> purpose.getPurpose().equals(string))
                .findFirst()
                .orElse(null);
    }
}
