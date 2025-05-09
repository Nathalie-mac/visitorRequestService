package com.example.team2.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusType {
    PENDING("на рассмотрении"),
    APPROVED("одобрена"),
    REJECTED_FOR_REASON("отклонена по причине");

    private final String statusType;
}
