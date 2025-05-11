package com.example.team2.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum StuffRoleType {
    MANAGER("менеджер"),
    GUARD_OFFICER("сотрудник охраны");

    private final String role;

    public StuffRoleType getEnum(String string) {
        return Arrays.stream(StuffRoleType.values())
                .filter(role -> role.getRole().equals(string))
                .findFirst()
                .orElse(null);
    }
}
