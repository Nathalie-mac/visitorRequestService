package com.example.team2.auth.services.parser;

import com.example.team2.auth.exceptions.DecodeCredentialsException;
import com.example.team2.auth.exceptions.InvalidBasicAuthorizationHeaderException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;

public class AuthorizationHeaderToUsernamePasswordAuthenticationToken {
    private static final String BASIC_PREFIX = "Basic ";

    public static UsernamePasswordAuthenticationToken getAuthenticationToken(String authenticationHeader) {
        try {
            if (!authenticationHeader.startsWith(BASIC_PREFIX)) {
                throw new InvalidBasicAuthorizationHeaderException();
            }

            String base64Credentials = authenticationHeader.substring(BASIC_PREFIX.length());
            String credentials = new String(
                    Base64.getDecoder().decode(base64Credentials),
                    StandardCharsets.UTF_8
            );
            List<String> parts = List.of(credentials.split(":", 2));
            if (parts.size() != 2) {
                throw new InvalidBasicAuthorizationHeaderException();
            }

            String username = parts.get(0);
            String password = parts.get(1);

            return new UsernamePasswordAuthenticationToken(username, password);
        } catch (IllegalArgumentException e) {
            throw new DecodeCredentialsException();
        }
    }
}
