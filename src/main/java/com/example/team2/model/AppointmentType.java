package com.example.team2.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum AppointmentType {
    INDIVIDUAL("индивидуальное"),
    PUBLIC("групповое");

    private final String type;

    public AppointmentType getEnum(String string) {
        return Arrays.stream(AppointmentType.values())
                .filter(type -> type.getType().equals(string))
                .findFirst()
                .orElse(null);
    }
}
