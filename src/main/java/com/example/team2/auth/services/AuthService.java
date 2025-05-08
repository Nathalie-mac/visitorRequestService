package com.example.team2.auth.services;


import com.example.team2.auth.data.entity.session.Session;
import com.example.team2.auth.data.repositories.UserRepository;
import com.example.team2.auth.services.parser.AuthorizationHeaderToUsernamePasswordAuthenticationToken;
import com.example.team2.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class AuthService {

    // Ключевое пространство для сессий в Redis
    private static final String SESSION_PREFIX = "session:";
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserService userService;
    private final RedissonClient redissonClient;

    private final ObjectMapper mapper;

    public ResponseEntity<?> signUp(String authenticationHeader) {
        UsernamePasswordAuthenticationToken authenticationToken = AuthorizationHeaderToUsernamePasswordAuthenticationToken
                .getAuthenticationToken(authenticationHeader);
        userService.createUser(authenticationToken.getName(), passwordEncoder.encode(authenticationToken.getCredentials().toString()));


        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> signIn(String authenticationHeader) {
        UsernamePasswordAuthenticationToken authenticationToken = AuthorizationHeaderToUsernamePasswordAuthenticationToken
                .getAuthenticationToken(authenticationHeader);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        Session session = createSession(authenticationToken.getName());
        ResponseCookie cookie = ResponseCookie.from("JSESSIONID", session.getSessionId())
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

    private Session createSession(String username) {
        String sessionId = UUID.randomUUID().toString();
        User user = userRepository.findUserByUserLogin(username);
        Session session = new Session();
        session.setSessionId(sessionId);
        session.setUserId(user.getId());
        session.setCreatedAt(LocalDateTime.now());
        session.setExpiresAt(LocalDateTime.now().plusHours(2));
        saveSessionToRedis(session);
        return session;

    }

    @SneakyThrows
    private void saveSessionToRedis(Session session) {
        String redisKey = SESSION_PREFIX + session.getSessionId();
        RBucket<String> bucket = redissonClient.getBucket(redisKey);
        String json = mapper.writeValueAsString(session);

        bucket.set(json, 2, TimeUnit.HOURS);
    }
}
