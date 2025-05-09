package com.example.team2.auth.httpresponse;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

public enum HttpResponse {
    UNAUTHORIZED {
        public static final String WWW_AUTHENTICATE_VALUE = "Basic realm=\"*\", charset=\"UTF-8\"";

        @Override
        public HttpServletResponse getResponse(HttpServletResponse response) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setHeader(HttpHeaders.WWW_AUTHENTICATE, WWW_AUTHENTICATE_VALUE);
            return response;
        }
    };

    public abstract HttpServletResponse getResponse(HttpServletResponse response);
}
