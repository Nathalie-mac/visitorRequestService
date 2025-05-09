package com.example.team2.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AppointmentPurpose {
    VISITING_GRANDMA("Посещение бабушки"),
    BUSINESS("бизнес"),
    EXCURSION("экскурсия");

    private final String purpose;
}
