package com.example.team2.auth.config;

import com.example.team2.auth.config.BCrypt.BCryptPasswordEncoder;
//import com.example.team2.auth.filter.AuthorizationHeaderExistFilter;
import com.example.team2.auth.filter.SessionExistFilter;
import com.example.team2.auth.services.RedisSessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    private final RedisSessionService redisSessionService;


    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SessionExistFilter sessionExistFilter() {
        return new SessionExistFilter(redisSessionService);
    }

    @Bean
    public FilterRegistrationBean<SessionExistFilter> sessionExistFilterRegistration() {
        FilterRegistrationBean<SessionExistFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(sessionExistFilter());
        // описано в фильтрах

        registrationBean.addUrlPatterns("/auth/logout", "/manager/*", "/client/*", "/guardofficer/*");
        registrationBean.setOrder(1);
        return registrationBean;
    }


}