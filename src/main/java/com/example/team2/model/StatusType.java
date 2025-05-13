package com.example.team2.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum StatusType {
    PENDING("на рассмотрении"),
    APPROVED("одобрена"),
    REJECTED_FOR_REASON("отклонена по причине");

    private final String statusType;

    public StatusType getEnum(String string) {
        return Arrays.stream(StatusType.values())
                .filter(statusType -> statusType.getStatusType().equals(string))
                .findFirst()
                .orElse(null);
    }
}
