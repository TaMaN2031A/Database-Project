package com.example.demo.Model;

import jdk.jfr.Enabled;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pet {
    @Id
    private int ID;
    private String name;
    private String breed;
    private int age;
    private int gender;
    private String behaviour;
    private String healthState;
    private int neutering;
    private int vaccination;
    private int idOfShelter;
}
