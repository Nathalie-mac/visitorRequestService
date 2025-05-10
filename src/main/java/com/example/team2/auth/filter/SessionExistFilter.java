package com.example.team2.auth.filter;

import com.example.team2.auth.httpresponse.HttpResponse;
import com.example.team2.auth.services.AuthService;
import com.example.team2.auth.services.RedisSessionService;
import com.example.team2.auth.services.parser.CookieHeaderParser;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import java.io.IOException;


@RequiredArgsConstructor
public class SessionExistFilter implements Filter {

    private final RedisSessionService redisSessionService;


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        if (!(request instanceof HttpServletRequest httpRequest) || !(response instanceof HttpServletResponse httpResponse)) {
            chain.doFilter(request, response);
            return;
        }


        String sessionId = CookieHeaderParser.getSessionIdCookie(httpRequest.getHeader("Cookie"), AuthService.COOKIE_HEADER_SESSION_ID_NAME);
        if (sessionId == null || sessionId.isEmpty()) {
            httpResponse = HttpResponse.UNAUTHORIZED.getResponse(httpResponse);
            return;
        }

        if (redisSessionService.isEmptySessionRBucket(sessionId)) {
            httpResponse = HttpResponse.UNAUTHORIZED.getResponse(httpResponse);
            return;
        }
        chain.doFilter(request, response);
    }
}
