package com.altonchan.gateway.config;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class KeycloakGrantedAuthoritiesConverter implements Converter<Jwt, Collection<GrantedAuthority>>{

    private static final String REALM_ACCESS = "realm_acess";
    private static final String ROLES = "roles";
    private static final String PREFIX_ROLE = "ROLE_";

    @Override
    public Collection<GrantedAuthority> convert(Jwt jwt) {
        Map<String, List<String>> realmAccess = (Map<String, List<String>>) jwt.getClaims().get("realm_access");
        return getAuthorityList(realmAccess);
    }

    private List<GrantedAuthority> getAuthorityList(Map<String, List<String>> realmAccess) {
        if (CollectionUtils.isEmpty(realmAccess)) return new ArrayList<>();
        return ((List<String>) realmAccess.get(ROLES)).stream()
                .map(roleName -> PREFIX_ROLE + roleName.toUpperCase())
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}
