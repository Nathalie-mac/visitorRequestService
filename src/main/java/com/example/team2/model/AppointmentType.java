package com.example.team2.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AppointmentType {
    INDIVIDUAL("индивидуальное"),
    PUBLIC("групповое");

    private final String type;
}
