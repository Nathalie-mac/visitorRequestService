package com.example.team2.auth.services;


import com.example.team2.auth.config.BCrypt.BCryptPasswordEncoder;
import com.example.team2.auth.data.entity.session.Session;
import com.example.team2.auth.data.entity.user.User;
import com.example.team2.auth.services.parser.AuthorizationHeaderToCredentialParser;
import com.example.team2.auth.services.parser.CookieHeaderParser;
import com.example.team2.auth.services.parser.Credential;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RBucket;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {


    public static final String COOKIE_HEADER_SESSION_ID_NAME = "CATSSESSIONID";
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserService userService;
    private final RedisSessionService redisSessionService;

    public ResponseEntity<?> signUp(String authenticationHeader) {
        Credential credential = AuthorizationHeaderToCredentialParser.parse(authenticationHeader);
        userService.createUser(credential.getUsername(), passwordEncoder  .encode(credential.getPassword()));


        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> signIn(String authenticationHeader) {
        Credential credential = AuthorizationHeaderToCredentialParser.parse(authenticationHeader);

        Session session = createSession(credential.getUsername());
        ResponseCookie cookie = ResponseCookie.from(COOKIE_HEADER_SESSION_ID_NAME, session.getSessionId())
                .httpOnly(true)                                 // Защита от доступа через JavaScript
                .path("/")                                      // Доступно для всего приложения
                .maxAge(2 * 60 * 60)               // Время жизни cookie (например, 1 час)
                .build();

        // Подготавливаем заголовки ответа
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.SET_COOKIE, cookie.toString());

        // Возвращаем ответ с заголовком Set-Cookie без тела
        return ResponseEntity.ok()
                .headers(headers)
                .build();
    }

    public ResponseEntity<?> logout(String cookieHeader) {
        String sessionId = CookieHeaderParser.getSessionIdCookie(cookieHeader, COOKIE_HEADER_SESSION_ID_NAME);
        RBucket<String> bucket = redisSessionService.getRBucket(sessionId);
        bucket.delete();
        return ResponseEntity.ok().build();
    }

    private Session createSession(String username) {
        String sessionId = UUID.randomUUID().toString();
        User user = userService.findUserByUsername(username);
        Session session = new Session();
        session.setSessionId(sessionId);
        session.setUserId(user.getId());
        session.setCreatedAt(LocalDateTime.now());
        session.setExpiresAt(LocalDateTime.now().plusHours(2));
        redisSessionService.saveSessionToRedis(session);
        return session;

    }


}
