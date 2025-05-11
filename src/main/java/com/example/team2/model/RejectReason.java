package com.example.team2.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum RejectReason {
    BLACKLIST("черный список"),
    INVALID_DATA("недостоверные данные"),
    ATTACHMENTS("прикрепленные файлы");

    private final String reason;

    public RejectReason getEnum(String string) {
        return Arrays.stream(RejectReason.values())
                .filter(reason -> reason.getReason().equals(string))
                .findFirst()
                .orElse(null);
    }
}

