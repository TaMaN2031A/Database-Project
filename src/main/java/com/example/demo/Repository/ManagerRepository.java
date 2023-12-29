package com.example.demo.Repository;

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
                        resultSet.getString("user_name"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("password"),
                        resultSet.getString("contact_info")
                )
        );
    }

}
