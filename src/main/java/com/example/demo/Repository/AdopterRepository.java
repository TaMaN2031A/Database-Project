package com.example.demo.Repository;
import com.example.demo.Model.Adopter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
@Repository
public class AdopterRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AdopterRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public boolean addAdopter(Adopter adopter) {
        try {
            String sql = "INSERT INTO adopter (first_name,last_name,user_name,password,contactInfo) VALUES (?, ?, ?, ?, ?)";
            return jdbcTemplate.update(sql, adopter.getFirstName(),adopter.getLastName(),adopter.getUsername(), adopter.getPassword(), adopter.getContactInfo())==1;
        }
        catch (Exception e) {
            return false;
        }
    }

    public Adopter getAdopter(String username){
        try {
            String sql = "SELECT * FROM adopter WHERE user_name = ?";
            return jdbcTemplate.queryForObject(sql,(resultSet, rowNum) -> Adopter.builder()
                            .firstName(resultSet.getString("first_name"))
                            .lastName(resultSet.getString("last_name"))
                            .contactInfo(resultSet.getString("contactInfo"))
                            .userName(resultSet.getString("user_name"))
                            .password(resultSet.getString("password"))
                            .build()
                    , username);
        } catch (Exception e) {
            return null;
        }
    }
}
