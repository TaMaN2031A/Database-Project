package com.example.demo.Repository;

import com.example.demo.Model.Adopter;
import com.example.demo.Model.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class StaffRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean addStaff(Staff staff) {
        try {
            String sql = "INSERT INTO staff (user_name, contact_info, role, id_of_shelter,first_name,last_name,password) VALUES (?, ?, ?, ?, ?, ?, ?)";
            return jdbcTemplate.update(sql, staff.getUsername(), staff.getContactInfo(), staff.getRole(), staff.getIdOfShelter(),staff.getFirstName(),staff.getLastName(),staff.getPassword())==1;
        }
        catch (Exception e) {
            return false;
        }
    }

    public Integer getShelterIdByUserName(String userName) {
        String sql = "SELECT id_of_shelter FROM staff WHERE user_name = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, userName);
    }

    public Staff getStaff(String username){
        try {
            System.out.println(username);
            String sql = "SELECT * FROM staff WHERE user_name = ?";
            return jdbcTemplate.queryForObject(sql,(resultSet, rowNum) -> Staff.builder()
                            .firstName(resultSet.getString("first_name"))
                            .lastName(resultSet.getString("last_name"))
                            .contactInfo(resultSet.getString("contact_info"))
                            .userName(resultSet.getString("user_name"))
                            .password(resultSet.getString("password"))
                            .role(resultSet.getString("role"))
                            .idOfShelter(resultSet.getInt("id_of_shelter"))
                            .build()

                    , username);
        } catch (Exception e) {
            return null;
        }
    }


}
