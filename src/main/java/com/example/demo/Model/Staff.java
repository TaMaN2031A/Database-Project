package com.example.demo.Model;

import jdk.jfr.Enabled;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Staff {
    @Id
    private int ID;
    private String name;
    private String contactInfo;
    private String role;
    private int idOfShelter;
}