package com.example.demo.Repository;

import com.example.demo.Model.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RecordRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void addRecord(Record Record) {
        String sql = "INSERT INTO Record (status, pet_id, adopter_user_name) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, Record.getStatus(), Record.getPetId(), Record.getAdopterUserName());
    }

    public void updateRecord(Record Record) {
        String sql = "UPDATE Record SET status = ? WHERE id = ?";
        jdbcTemplate.update(sql, Record.getStatus(), Record.getId());
    }
}
