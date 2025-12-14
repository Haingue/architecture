package com.imt.intes.configuration.security;

import org.springframework.security.core.GrantedAuthority;

//@Enum
public enum Role implements GrantedAuthority {

    ROLE_USER,
    ROLE_MANAGER,
    ROLE_ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
