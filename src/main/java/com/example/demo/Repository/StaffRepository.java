package com.example.demo.Repository;

import com.example.demo.Model.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class StaffRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void addStaff(Staff staff) {
        String sql = "INSERT INTO staff (name, contact_info, role, id_of_shelter) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, staff.getName(), staff.getContactInfo(), staff.getRole(), staff.getIdOfShelter());
    }
}
