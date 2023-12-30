package com.example.demo.Repository;

import com.example.demo.Model.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PetRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void savePet(Pet pet) {
        String sql = "INSERT INTO pet (name, breed, age, gender, behavior, health_status, description, neutering, vaccination, id_of_shelter, adopted) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                pet.getName(), pet.getBreed(), pet.getAge(), pet.getGender(),
                pet.getBehaviour(), pet.getHealthStatus(), pet.getDescription(),
                pet.getNeutering(), pet.getVaccination(), pet.getIdOfShelter(), pet.isAdopted());
    }

    // Retrieve a pet by its ID
    public Pet getPetById(int petId) {
        String sql = "SELECT * FROM pet WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{petId}, new BeanPropertyRowMapper<>(Pet.class));
    }

    // Retrieve all pets
    public List<Pet> getAllPets() {
        String sql = "SELECT * FROM pet";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Pet.class));
    }

    // Update an existing pet
    public void updatePet(Pet pet) {
        String sql = "UPDATE pet SET name=?, breed=?, age=?, gender=?, behavior=?, health_status=?, description=?, " +
                "neutering=?, vaccination=?, id_of_shelter=?, adopted=? WHERE id=?";
        jdbcTemplate.update(sql,
                pet.getName(), pet.getBreed(), pet.getAge(), pet.getGender(),
                pet.getBehaviour(), pet.getHealthStatus(), pet.getDescription(),
                pet.getNeutering(), pet.getVaccination(), pet.getIdOfShelter(), pet.isAdopted(), pet.getID());
    }

    // Delete a pet by its ID
    public void deletePet(int petId) {
        String sql = "DELETE FROM pet WHERE id=?";
        jdbcTemplate.update(sql, petId);}
    public List<Pet> getPetsByShelterId(int shelterId) {
        String sql = "SELECT * FROM pet WHERE id_of_shelter = ?";
        return jdbcTemplate.query(sql, new Object[]{shelterId}, new BeanPropertyRowMapper<>(Pet.class));
    }

}
