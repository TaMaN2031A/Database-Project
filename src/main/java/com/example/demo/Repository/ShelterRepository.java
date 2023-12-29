package com.example.demo.Repository;

import com.example.demo.Model.Shelter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class ShelterRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Long addShelter(Shelter shelter) {
        String sql = "INSERT INTO shelter (name, contactInfo, location) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, shelter.getName(), shelter.getContactInfo(), shelter.getLocation());

        // Retrieve the last inserted ID
        String selectLastIdSql = "SELECT LAST_INSERT_ID()";
        return jdbcTemplate.queryForObject(selectLastIdSql, Long.class);
    }
    public void updateShelter(Shelter shelter) {
        String sql = "UPDATE shelter SET name = ?, contactInfo = ?, location = ? WHERE id = ?";
        jdbcTemplate.update(sql, shelter.getName(), shelter.getContactInfo(), shelter.getLocation(), shelter.getID());
    }
    public void deleteShelter(int shelter_id) {
        String sql = "DELETE FROM shelter WHERE id = ?";
        jdbcTemplate.update(sql, shelter_id);
    }

    public ArrayList<Shelter> getShelters() {
        String sql = "SELECT * FROM shelter";
        return (ArrayList<Shelter>) jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Shelter.class));
    }
}
