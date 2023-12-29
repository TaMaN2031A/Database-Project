package com.example.demo.Repository;

import com.example.demo.Model.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PetRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void addPet(Pet pet) {
        String sql = "INSERT INTO pet (name, breed, age, gender, behaviour, health_status, description, neutering, vaccination, id_of_shelter) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, pet.getName(), pet.getBreed(), pet.getAge(), pet.getGender(), pet.getBehaviour(), pet.getHealthStatus(), pet.getDescription(), pet.getNeutering(), pet.getVaccination(), pet.getIdOfShelter());
    }

    public void updatePet(Pet pet) {
        String sql = "UPDATE pet SET name = ?, breed = ?, age = ?, gender = ?, behaviour = ?, health_status = ?, description = ?, neutering = ?, vaccination = ?, id_of_shelter = ? WHERE id = ?";
        jdbcTemplate.update(sql, pet.getName(), pet.getBreed(), pet.getAge(), pet.getGender(), pet.getBehaviour(), pet.getHealthStatus(), pet.getDescription(), pet.getNeutering(), pet.getVaccination(), pet.getIdOfShelter(), pet.getID());
    }
}
