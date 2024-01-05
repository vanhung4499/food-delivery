package com.hnv99.fooddelivery.global.config.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.Getter;
import lombok.ToString;

import java.util.Date;

public final class Jwt {

    private final String issuer;

    private final String clientSecret;

    private final int expiration;

    private final Algorithm algorithm;

    private final JWTVerifier jwtVerifier;

    public Jwt(
            String issuer,
            String clientSecret,
            int expiration
    ) {
        this.issuer = issuer;
        this.clientSecret = clientSecret;
        this.expiration = expiration;
        this.algorithm = Algorithm.HMAC256(clientSecret);
        this.jwtVerifier = JWT.require(algorithm)
                .withIssuer(issuer)
                .build();
    }

    public String sign(Claims claims) {
        Date now = new Date();
        JWTCreator.Builder builder = JWT.create()
                .withIssuer(issuer)
                .withIssuedAt(now)
                .withExpiresAt(new Date(now.getTime() + expiration))
                .withClaim("username", claims.getUsername())
                .withArrayClaim("roles", claims.getRoles());
        return builder.sign(algorithm);
    }

    public Claims verify(String token) {
        DecodedJWT decodedJWT = jwtVerifier.verify(token);
        return new Claims(decodedJWT);
    }

    @Getter
    @ToString
    public static class Claims {
        private String username;

        private String[] roles;

        private Date issuedAt;

        private Date expiresAt;

        private Claims(DecodedJWT decodedJWT) {
            Claim username = decodedJWT.getClaim("username");
            if (!username.isNull()) {
                this.username = username.asString();
            }
            Claim roles = decodedJWT.getClaim("roles");
            if (!roles.isNull()) {
                this.roles = roles.asArray(String.class);
            }
            this.issuedAt = decodedJWT.getIssuedAt();
            this.expiresAt = decodedJWT.getExpiresAt();
        }

        private Claims(String username, String[] roles) {
            this.username = username;
            this.roles = roles;
        }

        public static Claims of(String username, String[] roles) {
            return new Claims(username, roles);
        }
    }
}

