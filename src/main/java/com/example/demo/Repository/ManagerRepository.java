package com.example.demo.Repository;

import com.example.demo.Model.Adopter;
import com.example.demo.Model.App;
import com.example.demo.Model.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public class ManagerRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Manager> getAllManagers() {
        return jdbcTemplate.query("SELECT * FROM manager", (resultSet, rowNum) ->
                new Manager(
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("contact_info"),
                        resultSet.getString("password"),
                        resultSet.getString("user_name")
                )
        );
    }
    public Manager getManager(String username){
        try {
            String sql = "SELECT * FROM manager WHERE user_name = ?";
            return jdbcTemplate.queryForObject(sql, (resultSet, rowNum) -> Manager.builder()
                            .firstName(resultSet.getString("first_name"))
                            .lastName(resultSet.getString("last_name"))
                            .contactInfo(resultSet.getString("contact_info"))
                            .userName(resultSet.getString("user_name"))
                            .password(resultSet.getString("password"))
                            .build()

                    , username);
        } catch (Exception e) {
            return null;
        }
    }

    public boolean addManager(Manager manager) {
        try {
            String sql = "INSERT INTO manager (first_name,last_name,user_name,password,contact_info) VALUES (?, ?, ?, ?, ?)";
            return jdbcTemplate.update(sql, manager.getFirstName(),manager.getLastName(),manager.getUsername(), manager.getPassword(), manager.getContactInfo())==1;
        }
        catch (Exception e) {
            return false;
        }
    }


}
