package com.example.demo.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    //requestMatchers("/**").permitAll()
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests( auth -> auth
                        .requestMatchers("/auth/**").permitAll()
                        .requestMatchers("/Adopter/**").hasAuthority("ADOPTER")
                        .requestMatchers("/Manager/**").hasAuthority("MANAGER")
                        .requestMatchers("/Staff/**").hasAuthority("STAFF")
                        .requestMatchers("/api/app/add").hasAuthority("ADOPTER")
                        .requestMatchers("/api/pet/searchPets/**").hasAuthority("ADOPTER")
                        .requestMatchers("/api/pet/searchPets/**").hasAuthority("STAFF")
                        .requestMatchers("/api/app/getApps/**").hasAuthority("STAFF")
                        .requestMatchers("/replyToApp/**").hasAuthority("STAFF")
                        .requestMatchers("/api/Breed/getBreed").permitAll()
                        .requestMatchers("/api/Breed/**").hasAuthority("STAFF")
                        .requestMatchers("/api/notification/**").hasAuthority("ADOPTER")
                        .requestMatchers("/api/pet-documents/upload").hasAuthority("STAFF")
                        .requestMatchers("/api/pet/add").hasAuthority("STAFF")
                        .requestMatchers("/api/pet/update").hasAuthority("STAFF")
                        .requestMatchers("/api/pet/delete/**").hasAuthority("STAFF")
                        .requestMatchers("/api/pet/all").permitAll()
                        .requestMatchers("/api/pet/shelterPets/**").hasAuthority("STAFF")
                        .requestMatchers("/api/pet/get_pet_profile/**").hasAuthority("STAFF")
                        .requestMatchers("/api/pet/shelterPets/**").hasAuthority("ADOPTER")
                        .requestMatchers("/api/pet/get_pet_profile/**").hasAuthority("ADOPTER")
                        .requestMatchers("/api/shelter/name/**").permitAll()
                        .requestMatchers("/api/shelter/getShelter").permitAll()
                        .requestMatchers("/api/shelter/**").hasAuthority("MANAGER")
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
