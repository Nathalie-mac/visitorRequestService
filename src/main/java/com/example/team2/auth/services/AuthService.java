package com.example.team2.auth.services;


import com.example.team2.auth.config.BCrypt.BCryptPasswordEncoder;
import com.example.team2.auth.data.entity.session.Session;
import com.example.team2.auth.services.parser.AuthorizationHeaderToCredentialParser;
import com.example.team2.auth.services.parser.CookieHeaderParser;
import com.example.team2.auth.services.parser.Credential;
import com.example.team2.dto.LoginDTO;
import com.example.team2.model.Stuff;
import com.example.team2.model.StuffRoleType;
import com.example.team2.model.User;
import com.example.team2.service.StuffService;
import com.example.team2.service.UserService;
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
    private final StuffService stuffService;
    private final RedisSessionService redisSessionService;

    private static Credential authenticationHeaderParse(String authenticationHeader) {
        return AuthorizationHeaderToCredentialParser.parse(authenticationHeader);
    }

    public boolean signUpClient(LoginDTO loginDTO) {
        userService.createUser(loginDTO.getLogin(), passwordEncoder.encode(loginDTO.getPassword()));

        return true;
    }

    //TODO: снести после отладки
    public ResponseEntity<?> signUpStuff(String authenticationHeader, StuffRoleType roleType) {
        Credential credential = authenticationHeaderParse(authenticationHeader);
        stuffService.createStuff(credential.getLogin(), passwordEncoder.encode(credential.getPassword()), roleType);
        return ResponseEntity.ok().build();
    }

    public CustomResponse signInClient(LoginDTO loginDTO) {

        Session session = createUserSession(loginDTO.getLogin());
        return new CustomResponse(true, session.getSessionId());
    }

    public CustomResponse signInStuff(LoginDTO loginDTO, StuffRoleType stuffRole) {

        Session session = createStuffSession(loginDTO.getLogin(), stuffRole);
        return new CustomResponse(true, session.getSessionId());
    }

    //TODO: снести после отладки
    public ResponseEntity<?> logout(String cookieHeader) {
        String sessionId = CookieHeaderParser.getSessionIdCookie(cookieHeader, COOKIE_HEADER_SESSION_ID_NAME);
        RBucket<String> bucket = redisSessionService.getRBucket(sessionId);
        bucket.delete();
        return ResponseEntity.ok().build();
    }

    public Long getUserIdFromCookie(String cookieHeader){
        String sessionId = CookieHeaderParser.getSessionIdCookie(cookieHeader, COOKIE_HEADER_SESSION_ID_NAME);

        return redisSessionService.getUserIdFromSession(sessionId);
    }

    private Session createUserSession(String login) {
        String sessionId = UUID.randomUUID().toString();
        User user = userService.findUserByLogin(login);
        return createSession(sessionId, user.getId());

    }

    private Session createStuffSession(String code, StuffRoleType stuffRole) {
        String sessionId = UUID.randomUUID().toString();
        Stuff stuff = stuffService.findStuffByCodeAndRole(code, stuffRole);
        return createSession(sessionId, stuff.getId());

    }

    private Session createSession(String sessionId, long id) {
        Session session = new Session();
        session.setSessionId(sessionId);
        session.setUserId(id);
        session.setCreatedAt(LocalDateTime.now());
        session.setExpiresAt(LocalDateTime.now().plusHours(2));
        redisSessionService.saveSessionToRedis(session);
        return session;
    }



}
