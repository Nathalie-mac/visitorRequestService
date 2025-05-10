package com.example.team2.uiservice.provider;

import com.example.team2.auth.services.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

public class SessionCookieProvider {
    public static void setUpSessionCookie(HttpServletResponse response, String sessionId) {
        // Создаем cookie
        Cookie authCookie = new Cookie(AuthService.COOKIE_HEADER_SESSION_ID_NAME, sessionId);
        authCookie.setHttpOnly(true);
        authCookie.setMaxAge(7 * 24 * 60 * 60); // Срок жизни cookie (например, 7 дней)

        // Добавляем cookie в ответ
        response.addCookie(authCookie);
    }
}
