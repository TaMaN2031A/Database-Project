package com.example.demo.Controller;

import com.example.demo.Enum.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String firstName;
    private String lastName;
    private String contactInfo;
    private String password;
    private String userName;
    private Role roleInSystem;
    private String role;
    private int idOfShelter;

}
