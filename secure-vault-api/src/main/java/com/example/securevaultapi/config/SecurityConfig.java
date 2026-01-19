package com.example.securevaultapi.config;

import com.example.securevaultapi.security.RateLimitFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final RateLimitFilter rateLimitFilter;

    public SecurityConfig(RateLimitFilter rateLimitFilter) {
        this.rateLimitFilter = rateLimitFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Desactivamos CSRF porque es una API REST
                .addFilterBefore(rateLimitFilter, UsernamePasswordAuthenticationFilter.class) // Anti-Fuerza Bruta primero
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**").permitAll() // Login público
                        .anyRequest().permitAll() // POR AHORA: Permitimos todo para facilitar tu prueba en Postman
                );
        return http.build();
    }
}
