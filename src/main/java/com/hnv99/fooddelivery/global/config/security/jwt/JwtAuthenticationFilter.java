package com.hnv99.fooddelivery.global.config.security.jwt;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;
import static org.apache.logging.log4j.util.Strings.isNotEmpty;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends GenericFilterBean {
    private final String headerKey;

    private final Jwt jwt;

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain
    ) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest)request;
        HttpServletResponse httpServletResponse = (HttpServletResponse)response;

        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            String token = getToken(httpServletRequest);
            if (token != null) {
                try {
                    Jwt.Claims claims = verify(token);
                    log.debug("JWT verified: {}", claims);

                    String username = claims.getUsername();
                    List<GrantedAuthority> authorities = getAuthorities(claims);

                    if (isNotEmpty(username) && !authorities.isEmpty()) {
                        JwtAuthenticationToken authentication = new JwtAuthenticationToken(
                                new JwtAuthentication(token, username),
                                null,
                                authorities
                        );
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                } catch (Exception e) {
                    log.debug("JWT verification failed: {}", e.getMessage());
                }
            } else {
                log.debug("SecurityContextHolder already has authentication set up. '{}'",
                        SecurityContextHolder.getContext().getAuthentication());
            }
        }
        chain.doFilter(httpServletRequest, httpServletResponse);

    }

    private String getToken(HttpServletRequest request) {
        String token = request.getHeader(headerKey);
        if (isNotEmpty(token)) {
            log.debug("JWT authorization header found: {}", token);
            return URLDecoder.decode(token, StandardCharsets.UTF_8);
        }

        return null;
    }

    private Jwt.Claims verify(String token) {
        return jwt.verify(token);
    }

    private List<GrantedAuthority> getAuthorities(Jwt.Claims claims) {
        String[] roles = claims.getRoles();
        if (roles == null || roles.length == 0) {
            return emptyList();
        }

        return Arrays.stream(roles)
                .map(SimpleGrantedAuthority::new)
                .collect(toList());
    }
}
