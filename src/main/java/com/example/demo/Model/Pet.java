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
    private int neutering;
    private int vaccination;
    private int idOfShelter;
    private String description;
    private List<PetDocument> petDocuments;
    @Override
    public String toString() {
        return "Pet{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", breed='" + breed + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                ", behaviour='" + behaviour + '\'' +
                ", healthStatus='" + healthStatus + '\'' +
                ", neutering=" + neutering +
                ", vaccination=" + vaccination +
                ", idOfShelter=" + idOfShelter +
                ", description='" + description + '\'' +
                ", petDocuments=" + petDocuments +
                '}';
    }
}
