package com.example.team2.auth.services.parser;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Credential {
    @NotBlank
    String username;
    @NotBlank
    String password;
}
