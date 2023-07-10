package com.altonchan.gateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;


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
                 .pathMatchers("/actuator/**").permitAll()
                 .anyExchange().authenticated()
//                 .pathMatchers("/library/bms/**").hasRole(ROLE_BMS)
//                 .pathMatchers("/library/bbs/**").hasRole(ROLE_BBS)
                 .and()
                 .oauth2Login(Customizer.withDefaults())
//                 .oauth2ResourceServer( // define this application as resource server
//                         oauth2 -> oauth2
//                                 .jwt() // define token type = jwt
//                                 .jwtAuthenticationConverter(grantedAuthoritiesExtractor()) // define how to extract authorities from token
//                 )
                 .build();
    }
// Comment out as gateway changed to client from resource server
//    @Bean
//    Converter<Jwt, Mono<AbstractAuthenticationToken>> grantedAuthoritiesExtractor() {
//        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
//        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(keycloakGrantedAuthoritiesConverter);
//        return new ReactiveJwtAuthenticationConverterAdapter(jwtAuthenticationConverter);
//    }
}
