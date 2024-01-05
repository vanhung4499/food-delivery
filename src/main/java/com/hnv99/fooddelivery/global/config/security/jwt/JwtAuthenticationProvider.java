package com.hnv99.fooddelivery.global.config.security.jwt;

import com.hnv99.fooddelivery.global.error.exception.ErrorCode;
import com.hnv99.fooddelivery.global.error.exception.InvalidValueException;
import com.hnv99.fooddelivery.user.domain.User;
import com.hnv99.fooddelivery.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;


@RequiredArgsConstructor
public class JwtAuthenticationProvider implements AuthenticationProvider {

    private final Jwt jwt;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public boolean supports(Class<?> authentication) {
        return (JwtAuthenticationToken.class.isAssignableFrom(authentication));
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        JwtAuthenticationToken jwtAuthentication = (JwtAuthenticationToken)authentication;

        return processUserAuthentication(
                String.valueOf(jwtAuthentication.getPrincipal()), jwtAuthentication.getCredentials());
    }

    private Authentication processUserAuthentication(
            String principal,
            String credentials
    ) {
        try {
            User user = login(principal, credentials);
            List<GrantedAuthority> authorities = user.getAuthorities();
            String token = getToken(user.getEmail(), authorities);
            JwtAuthenticationToken authentication = new JwtAuthenticationToken(
                    new JwtAuthentication(token, user.getEmail()),
                    null,
                    authorities
            );
            authentication.setDetails(user);
            return authentication;
        } catch (DataAccessException e) {
            throw new AuthenticationServiceException(e.getMessage(), e);
        }
    }

    private User login(String principal, String credentials) {
        User user = userRepository.findByEmail(principal)
                .orElseThrow(() -> new InvalidValueException(ErrorCode.USER_LOGIN_FAIL) { });
        user.checkPassword(passwordEncoder, credentials);
        return user;
    }

    private String getToken(
            String username,
            List<GrantedAuthority> authorities
    ) {
        String[] roles = authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .toArray(String[]::new);
        return jwt.sign(Jwt.Claims.of(username, roles));
    }
}
