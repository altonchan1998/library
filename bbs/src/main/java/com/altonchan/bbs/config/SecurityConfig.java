package com.altonchan.bbs.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private KeycloakAuthenticationConvertor keycloakAuthenticationConvertor;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/actuator/**").permitAll()
                .requestMatchers("/**").authenticated()
                .and()
                .oauth2ResourceServer(
                        oauth2 -> oauth2
                                .jwt()
                                .jwtAuthenticationConverter(keycloakAuthenticationConvertor)
                ).build();
    }
}
