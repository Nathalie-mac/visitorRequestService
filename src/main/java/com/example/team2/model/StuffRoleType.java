package com.example.team2.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StuffRoleType {
    MANAGER("менеджер"),
    GUARD_OFFICER("сотрудник охраны");

    private final String role;

}
