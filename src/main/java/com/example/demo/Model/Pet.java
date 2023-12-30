package com.example.demo.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pet {
    private int ID;
    private String name;
    private String breed;
    private int age;
    private int gender;
    private String behaviour;
    private String healthStatus;
    private String description;
    private int neutering;
    private int vaccination;
    private int idOfShelter;
    private List<PetDocument> petDocuments;
    private boolean adopted;
}
