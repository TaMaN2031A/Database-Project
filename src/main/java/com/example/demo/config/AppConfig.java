package com.example.demo.config;

import com.example.demo.Repository.AdopterRepository;
import com.example.demo.Repository.ManagerRepository;
import com.example.demo.Repository.StaffRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class AppConfig {

    private final AdopterRepository adopterRepository;
    private final ManagerRepository managerRepository;
    private final StaffRepository staffRepository;
    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            UserDetails userDetails = adopterRepository.getAdopter(username);

            if(userDetails == null) {
                userDetails = staffRepository.getStaff(username);
            }

            if(userDetails == null) {
                userDetails = managerRepository.getManager(username);
            }
            if(userDetails == null)
                throw new UsernameNotFoundException("User not found");
            return userDetails;
        };
    }
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return  authenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
