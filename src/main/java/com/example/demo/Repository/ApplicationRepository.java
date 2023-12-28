package com.example.demo.Repository;

import com.example.demo.Model.App;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ApplicationRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void addRecord(App app) {
        String sql = "INSERT INTO application (status, pet_id, adopter_id) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, app.getStatus(), app.getPetId(), app.getAdopterId());
    }
}
