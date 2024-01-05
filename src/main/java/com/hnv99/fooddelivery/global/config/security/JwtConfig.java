package com.hnv99.fooddelivery.global.config.security;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "jwt")
public record JwtConfig(
        String header,
        String issuer,
        String clientSecret,
        int expiration
)
{ }