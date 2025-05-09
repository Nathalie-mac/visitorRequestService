package com.example.team2.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RejectReason {
    BLACKLIST("черный список"),
    INVALID_DATA("недостоверные данные"),
    ATTACHMENTS("прикрепленные файлы");

    private final String reason;
}

