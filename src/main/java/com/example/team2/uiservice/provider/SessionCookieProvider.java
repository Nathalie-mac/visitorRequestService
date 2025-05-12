package com.example.team2.uiservice.provider;

import com.example.team2.auth.services.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;

public class SessionCookieProvider {
    public static void setUpSessionCookie(HttpServletResponse response, String sessionId) {
        ResponseCookie cookie = ResponseCookie.from(AuthService.COOKIE_HEADER_SESSION_ID_NAME, sessionId)
                .httpOnly(true)
                .secure(false) // В development можно false, в production — true
                .path("/")
                .maxAge(7 * 24 * 60 * 60)
                .sameSite("Lax") // Или "None" + Secure=true для кросс-сайта
                .build();
        response.addHeader("Set-Cookie", cookie.toString());
    }
}
