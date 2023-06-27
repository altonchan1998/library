package com.altonchan.gateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Mono;

import static com.altonchan.gateway.constant.KeycloakRole.ROLE_BBS;
import static com.altonchan.gateway.constant.KeycloakRole.ROLE_BMS;

@Configuration
@EnableWebFluxSecurity // web flux as Spring cloud gateway is built on spring react module
public class SecurityConfig {

    @Autowired
    private KeycloakGrantedAuthoritiesConverter keycloakGrantedAuthoritiesConverter;

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
         return http
                 .csrf().disable()
                 .authorizeExchange()
                 .pathMatchers("/library/bms/**").hasRole(ROLE_BMS)
                 .pathMatchers("/library/bbs/**").hasRole(ROLE_BBS)
                 .and()
                 .oauth2ResourceServer( // define this application as resource server
                         oauth2 -> oauth2
                                 .jwt() // define token type = jwt
                                 .jwtAuthenticationConverter(grantedAuthoritiesExtractor()) // define how to extract authorities from token
                 ).build();
    }

    @Bean
    Converter<Jwt, Mono<AbstractAuthenticationToken>> grantedAuthoritiesExtractor() {
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(keycloakGrantedAuthoritiesConverter);
        return new ReactiveJwtAuthenticationConverterAdapter(jwtAuthenticationConverter);
    }
}
