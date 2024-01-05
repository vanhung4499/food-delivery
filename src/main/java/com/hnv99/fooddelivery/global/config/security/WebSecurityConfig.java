package com.hnv99.fooddelivery.global.config.security;

import com.hnv99.fooddelivery.global.config.security.jwt.Jwt;
import com.hnv99.fooddelivery.global.config.security.jwt.JwtAuthenticationFilter;
import com.hnv99.fooddelivery.global.config.security.jwt.JwtAuthenticationProvider;
import com.hnv99.fooddelivery.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;


@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {
    private final JwtConfig jwtConfig;

    @Bean
    public Jwt jwt() {
        return new Jwt(jwtConfig.issuer(), jwtConfig.clientSecret(), jwtConfig.expiration());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtAuthenticationProvider jwtAuthenticationProvider(
            Jwt jwt,
            UserRepository userRepository
    ) {
        return new JwtAuthenticationProvider(jwt, userRepository, passwordEncoder());
    }

    @Bean
    public AuthenticationManager authenticationManager(JwtAuthenticationProvider jwtAuthenticationProvider) {
        return new ProviderManager(jwtAuthenticationProvider);
    }

    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter(jwtConfig.header(), jwt());
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests()
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .disable()
                .logout()
                .disable()
                .csrf()
                .disable()
                .headers()
                .disable()
                .httpBasic()
                .disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterAfter(jwtAuthenticationFilter(), SecurityContextPersistenceFilter.class);

        return http.build();
    }
}
