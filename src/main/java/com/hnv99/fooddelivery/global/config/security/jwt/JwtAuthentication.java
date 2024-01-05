package com.hnv99.fooddelivery.global.config.security.jwt;

import lombok.Getter;

@Getter
public class JwtAuthentication {
    private final String token;
    private final String username;

    JwtAuthentication(String token, String username) {
        validationToken(token);
        validationUsername(username);
        this.token = token;
        this.username = username;
    }

    private void validationToken(String token) {
        if (token == null || token.isEmpty()) {
            throw new IllegalArgumentException("Token must not be empty");
        }
    }

    private void validationUsername(String username) {
        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("Username must not be empty");
        }
    }
}
