package com.example.demo.Model;

import jdk.jfr.Enabled;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Manager {
    private String userName;
    private String firstName;
    private String lastName;
    private String contactInfo;
    private String password;
}
