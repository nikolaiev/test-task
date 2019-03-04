package com.example.member.model;

import org.springframework.security.core.GrantedAuthority;

public enum Authority implements GrantedAuthority {
    ROLE_USER;

    @Override
    public String getAuthority() {
        return this.name();
    }
}