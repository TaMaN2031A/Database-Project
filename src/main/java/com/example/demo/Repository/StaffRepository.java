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
        String sql = "INSERT INTO staff (user_name, first_name, last_name, role, contact_info, password, id_of_shelter) VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, staff.getUserName(), staff.getFirstName(), staff.getLastName(), staff.getRole(), staff.getContactInfo(), staff.getPassword(), staff.getIdOfShelter());
    }

    public void updateStaff(Staff staff) {
        String sql = "UPDATE staff SET first_name = ?, last_name = ?, role = ?, contact_info = ?, password = ?, id_of_shelter = ? WHERE user_name = ?";
        jdbcTemplate.update(sql, staff.getFirstName(), staff.getLastName(), staff.getRole(), staff.getContactInfo(), staff.getPassword(), staff.getIdOfShelter(), staff.getUserName());
    }
}
