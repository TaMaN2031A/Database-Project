package com.example.demo.Service;

import com.example.demo.Controller.AuthenticationRequest;
import com.example.demo.Controller.AuthenticationResponse;
import com.example.demo.Controller.RegisterRequest;
import com.example.demo.Model.Adopter;
import com.example.demo.Model.Manager;
import com.example.demo.Model.Staff;
import com.example.demo.Repository.AdopterRepository;
import com.example.demo.Repository.ManagerRepository;
import com.example.demo.Repository.StaffRepository;
import com.example.demo.config.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final StaffRepository staffRepository;
    private final ManagerRepository managerRepository;
    private final AdopterRepository adopterRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    public AuthenticationResponse register(RegisterRequest request) {
        switch (request.getRoleInSystem().name()) {
            case "ADOPTER" -> {
                Adopter adopter = Adopter.builder()
                        .firstName(request.getFirstName())
                        .lastName(request.getLastName())
                        .contactInfo(request.getContactInfo())
                        .userName(request.getUserName())
                        .password(passwordEncoder.encode(request.getPassword()))
                        .build();
                if(!adopterRepository.addAdopter(adopter))
                    return null;

                var jwtToken = jwtService.generateJwtToken(adopter);

                return AuthenticationResponse.builder()
                        .token(jwtToken)
                        .build();
            }
            case "STAFF" -> {
                Staff staff = Staff.builder()
                        .firstName(request.getFirstName())
                        .lastName(request.getLastName())
                        .contactInfo(request.getContactInfo())
                        .userName(request.getUserName())
                        .password(passwordEncoder.encode(request.getPassword()))
                        .role(request.getRole())
                        .idOfShelter(request.getIdOfShelter())
                        .build();
                if(!staffRepository.addStaff(staff))
                    return null;
                var jwtToken = jwtService.generateJwtToken(staff);

                return AuthenticationResponse.builder()
                        .token(jwtToken)
                        .build();
            }
            case "MANAGER" -> {
                Manager manager = Manager.builder()
                        .firstName(request.getFirstName())
                        .lastName(request.getLastName())
                        .contactInfo(request.getContactInfo())
                        .userName(request.getUserName())
                        .password(passwordEncoder.encode(request.getPassword()))
                        .build();
                if(!managerRepository.addManager(manager))
                    return null;
                var jwtToken = jwtService.generateJwtToken(manager);
                return AuthenticationResponse.builder()
                        .token(jwtToken)
                        .build();
            }
            default -> {
                return null;
            }
        }
    }

    public AuthenticationResponse login(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUserName(),
                        request.getPassword()
                )
        );

        switch (request.getRole().name()) {
            case "ADOPTER" -> {
                Adopter user = adopterRepository.getAdopter(request.getUserName());
                if(user== null)
                    return null;
                var jwtToken = jwtService.generateJwtToken(user);
                return AuthenticationResponse.builder()
                        .token(jwtToken)
                        .build();
            }
            case "STAFF" -> {
                Staff user = staffRepository.getStaff(request.getUserName());
                if(user== null)
                    return null;
                var jwtToken = jwtService.generateJwtToken(user);
                return AuthenticationResponse.builder()
                        .token(jwtToken)
                        .build();
            }
            case "MANAGER" -> {
                Manager user = managerRepository.getManager(request.getUserName());
                if(user == null)
                    return null;
                var jwtToken = jwtService.generateJwtToken(user);
                return AuthenticationResponse.builder()
                        .token(jwtToken)
                        .build();
            }
            default -> {
                return null;
            }
        }

    }
}
