package com.example.team2.auth.services.parser;

import java.net.HttpCookie;
import java.util.List;

public class CookieHeaderParser {

    public static String getSessionIdCookie(String cookieHeader, String cookieSessionIdName) {
        List<HttpCookie> cookies = HttpCookie.parse(cookieHeader);
        return cookies.stream()
                .filter(cookie -> cookie.getName().equals(cookieSessionIdName))
                .map(HttpCookie::getValue)
                .findFirst().orElse(null);
    }
}
