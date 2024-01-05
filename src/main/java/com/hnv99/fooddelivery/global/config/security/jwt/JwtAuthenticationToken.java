package com.hnv99.fooddelivery.global.config.security.jwt;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JwtAuthenticationToken extends AbstractAuthenticationToken {
    private final Object principal;

    private String credentials;

    public JwtAuthenticationToken(
            String principal,
            String credentials
    ) {
        super(null);
        super.setAuthenticated(false);
        this.principal = principal;
        this.credentials = credentials;
    }

    JwtAuthenticationToken(
            Object principal,
            String credentials,
            Collection<? extends GrantedAuthority> authorities
    ) {
        super(authorities);
        super.setAuthenticated(true);
        this.principal = principal;
        this.credentials = credentials;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }

    @Override
    public String getCredentials() {
        return credentials;
    }

    @Override
    public void setAuthenticated(boolean authenticated) {
        if (authenticated) {
            throw new IllegalArgumentException(
                    "Authentication of this token cannot be accepted. Use constructor instead."
            );
        }
        super.setAuthenticated(false);
    }

    @Override
    public void eraseCredentials() {
        super.eraseCredentials();
        credentials = null;
    }
}
