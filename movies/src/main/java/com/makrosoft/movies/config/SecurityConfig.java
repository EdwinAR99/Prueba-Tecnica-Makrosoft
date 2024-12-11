package com.makrosoft.movies.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Nueva sintaxis con expresiones lambda
        http
            .authorizeHttpRequests(authz -> authz
                .anyRequest().permitAll() // Permite el acceso a todas las rutas
            )
            .csrf(csrf -> csrf.disable()); // Deshabilitar CSRF si no es necesario

        return http.build();
    }
}
