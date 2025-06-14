package com.chirag.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests) -> requests
                .requestMatchers("/contact").permitAll()
                .requestMatchers("/public/**").permitAll()
                // Any requests that start with public will be bypassed
                .requestMatchers("/admin").denyAll()
                // denies all requests, generally used to deny all deprecated requests
                .anyRequest().authenticated());

        // http.formLogin(withDefaults());
        http.csrf(csrf -> csrf.disable());
        // Making it stateless
        http.sessionManagement(session -> session.sessionCreationPolicy(
                SessionCreationPolicy.STATELESS));
        http.httpBasic(withDefaults());
        return http.build();
    }

    // Very basic auth used by Spring By default ^^^

}
