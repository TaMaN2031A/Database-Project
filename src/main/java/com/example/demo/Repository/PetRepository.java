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

    public Long addPet(Pet pet) {
        String sql = "INSERT INTO pet (name, breed, age, gender, behaviour, health_status, description, neutering, vaccination, id_of_shelter) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, pet.getName(), pet.getBreed(), pet.getAge(), pet.getGender(), pet.getBehaviour(), pet.getHealthStatus(), pet.getDescription(), pet.getNeutering(), pet.getVaccination(), pet.getIdOfShelter());

        // Retrieve the last inserted ID
        String selectLastIdSql = "SELECT LAST_INSERT_ID()";
        return jdbcTemplate.queryForObject(selectLastIdSql, Long.class);

    }

    public void updatePet(Pet pet) {
        System.out.println(pet.getID());
        String sql = "UPDATE pet SET name = ?, breed = ?, age = ?, gender = ?, behaviour = ?, health_status = ?, description = ?, neutering = ?, vaccination = ?, id_of_shelter = ? WHERE id = ?";
        jdbcTemplate.update(sql, pet.getName(), pet.getBreed(), pet.getAge(), pet.getGender(), pet.getBehaviour(), pet.getHealthStatus(), pet.getDescription(), pet.getNeutering(), pet.getVaccination(), pet.getIdOfShelter(), pet.getID());
    }
    public void deletePet(int petID) {
        String sql = "DELETE FROM pet WHERE id = ?";
        jdbcTemplate.update(sql, petID);
    }
    public List<Pet> getPetsByShelterId(int shelterId) {
        String sql = "SELECT * FROM pet WHERE id_of_shelter = ?";
        return jdbcTemplate.query(sql, new Object[]{shelterId}, new BeanPropertyRowMapper<>(Pet.class));
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
    public List<Pet> getPetsByShelterLocation(String shelterLocation) {
        String sql = "SELECT p.* FROM pet p " +
                "JOIN shelter s ON p.id_of_shelter = s.id " +
                "WHERE s.location = ?";
        return jdbcTemplate.query(sql, new Object[]{shelterLocation}, new BeanPropertyRowMapper<>(Pet.class));
    }
    public List<Pet> getPetsByBreed(String breed) {
        String sql = "SELECT * FROM pet WHERE breed = ?";
        return jdbcTemplate.query(sql, new Object[]{breed}, new BeanPropertyRowMapper<>(Pet.class));
    }

    // Retrieve pets by age
    public List<Pet> getPetsByAge(int age) {
        String sql = "SELECT * FROM pet WHERE age <= ?";
        return jdbcTemplate.query(sql, new Object[]{age}, new BeanPropertyRowMapper<>(Pet.class));
    }
}
