// src/main/java/com/example/demo/SecurityConfig.java
package com.cansupport.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig { // No longer extends WebSecurityConfigurerAdapter

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize // Use authorizeHttpRequests
                        .requestMatchers("/hello").permitAll() // <-- Changed from antMatchers() to requestMatchers()
                        .anyRequest().authenticated())
                .formLogin(form -> form // Modern way to configure form login
                        .permitAll())
                .logout(logout -> logout // Modern way to configure logout
                        .permitAll());
        return http.build();
    }

    // You likely still need this for default authentication or if you have other
    // secure endpoints
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.builder()
                .username("user")
                .password(passwordEncoder().encode("password")) // Always encode passwords!
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(user);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}