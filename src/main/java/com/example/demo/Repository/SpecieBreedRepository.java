package com.example.demo.Repository;

import com.example.demo.Model.SpecieBreed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class SpecieBreedRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void addSpecieBreed(SpecieBreed specieBreed) {
        String sql = "INSERT INTO specie_breed (breed, specie) VALUES (?, ?)";
        jdbcTemplate.update(sql, specieBreed.getBreed(), specieBreed.getSpecie());
    }
    public void updateSpecieBreed(SpecieBreed specieBreed) {
        String sql = "UPDATE specie_breed SET specie = ? WHERE breed = ?";
        jdbcTemplate.update(sql, specieBreed.getSpecie(), specieBreed.getBreed());
    }


    public void deleteSpecieBreed(String breed) {
        System.out.println(breed);
        String sql = "DELETE FROM specie_breed WHERE breed = ?";
        jdbcTemplate.update(sql, breed);
    }

    public ArrayList<SpecieBreed> getSpecieBreed() {
        String sql = "SELECT * FROM specie_breed";
        return (ArrayList<SpecieBreed>) jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(SpecieBreed.class));
    }

}
