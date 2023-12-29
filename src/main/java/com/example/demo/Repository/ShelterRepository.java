package com.example.demo.Repository;

import com.example.demo.Model.Shelter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ShelterRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void addShelter(Shelter shelter) {
        String sql = "INSERT INTO shelter (name, contactInfo, location) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, shelter.getName(), shelter.getContactInfo(), shelter.getLocation());
    }
    public void updateShelter(Shelter shelter) {
        String sql = "UPDATE shelter SET name = ?, contactInfo = ?, location = ? WHERE id = ?";
        jdbcTemplate.update(sql, shelter.getName(), shelter.getContactInfo(), shelter.getLocation(), shelter.getID());
    }
}
