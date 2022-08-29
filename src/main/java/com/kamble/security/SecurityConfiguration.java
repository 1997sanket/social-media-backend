package com.kamble.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

/*
    This class overrides default Spring Security
 */

@Configuration
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

        // 1) All requests should be authenticated
        httpSecurity.authorizeHttpRequests(
                auth -> auth.anyRequest().authenticated()
        );

        // 2) If a request is not authenticated then don't show webpage, show a pop-up
        httpSecurity.httpBasic(Customizer.withDefaults());

        // 3) disable CSRF for POST and PUT methods
        httpSecurity.csrf().disable();

        return httpSecurity.build();
    }
}
