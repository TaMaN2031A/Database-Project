package com.example.demo.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.example.demo.Model.Record;

@Repository
public class RecordRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void addRecord(Record record) {
        String sql = "INSERT INTO record (status, pet_id, adopter_id) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, record.getStatus(), record.getPetId(), record.getAdopterId());
    }
}
