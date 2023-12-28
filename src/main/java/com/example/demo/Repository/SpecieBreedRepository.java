package com.example.demo.Repository;

import com.example.demo.Model.SpecieBreed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class SpecieBreedRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void addSpecieBreed(SpecieBreed specieBreed) {
        String sql = "INSERT INTO specie_breed (breed, specie) VALUES (?, ?)";
        jdbcTemplate.update(sql, specieBreed.getBreed(), specieBreed.getSpecie());
    }
}
