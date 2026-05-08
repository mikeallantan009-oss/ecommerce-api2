package com.ws101.Tan.EcommerceApi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(auth -> auth

                        .requestMatchers(HttpMethod.GET, "/api/v1/products").permitAll()

                        .requestMatchers(HttpMethod.POST, "/api/v1/products").permitAll()

                        .requestMatchers("/api/v1/auth/register").permitAll()

                        .requestMatchers(HttpMethod.POST, "/api/v1/orders").authenticated()

                        .requestMatchers(HttpMethod.DELETE, "/api/v1/products/**").hasRole("ADMIN")

                        .anyRequest().authenticated()
                )

                .formLogin(Customizer.withDefaults())

                .logout(Customizer.withDefaults())

                .csrf(csrf -> csrf.disable());

        return http.build();
    }
}