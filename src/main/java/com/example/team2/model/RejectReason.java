package com.example.team2.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public enum RejectReason {
    INCOMPLETE_DOCUMENTS(""),
    EXPIRED(""),
    OTHER(""),
    I_DONT_LIKE_YOU("");

    private final String reason;
}

