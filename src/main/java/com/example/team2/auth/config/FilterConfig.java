package com.example.team2.auth.config;

import com.example.team2.auth.filter.AuthorizationHeaderExistFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity
public class FilterConfig {
    @Bean
    public FilterRegistrationBean<AuthorizationHeaderExistFilter> loggingFilter() {
        FilterRegistrationBean<AuthorizationHeaderExistFilter> registrationBean
                = new FilterRegistrationBean<>();

        registrationBean.setFilter(new AuthorizationHeaderExistFilter());
        registrationBean.addUrlPatterns("/sign-in", "/sign-up");

        return registrationBean;
    }
}
