package com.example.demo.Repository;

import com.example.demo.DTO.AppGetDTO;
import com.example.demo.Model.App;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class AppRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void addApp(App app) {
        String sql = "INSERT INTO application (status, pet_id, adopter_user_name) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, app.getStatus(), app.getPetId(), app.getAdopterUserName());
    }

    public void updateApp(App app) {
        String sql = "UPDATE application SET status = ? WHERE id = ?";
        jdbcTemplate.update(sql, app.getStatus(), app.getId());
    }

    public List<AppGetDTO> getAllApp(String staffName) {
        String sql = "SELECT a.id AS idOfApp, p.name AS petName, a.status AS statusOfApp, ad.user_name AS adopterName " +
                "FROM application a " +
                "JOIN pet p ON a.pet_id = p.id " +
                "JOIN shelter s ON p.id_of_shelter = s.id " +
                "JOIN staff st ON s.id = st.id_of_shelter " +
                "JOIN adopter ad ON a.adopter_user_name = ad.user_name " +
                "WHERE st.user_name = ?";

        return jdbcTemplate.query(sql, new Object[]{staffName}, new AppGetDTOMapper());
    }

    private static class AppGetDTOMapper implements RowMapper<AppGetDTO> {
        @Override
        public AppGetDTO mapRow(ResultSet resultSet, int i) throws SQLException {
            AppGetDTO appGetDTO = new AppGetDTO();
            appGetDTO.setIdOfApp(resultSet.getInt("idOfApp"));
            appGetDTO.setPetName(resultSet.getString("petName"));
            appGetDTO.setStatusOfApp(resultSet.getString("statusOfApp"));
            appGetDTO.setAdopterName(resultSet.getString("adopterName"));
            return appGetDTO;
        }
    }

    public void replyToApp(String newState, int applicationId) {

        String sql = "UPDATE application SET status = ? WHERE id = ?";
        jdbcTemplate.update(sql, newState, applicationId);
    }



}
