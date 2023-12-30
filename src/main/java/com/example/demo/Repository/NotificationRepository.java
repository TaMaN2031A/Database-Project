package com.example.demo.Repository;

import com.example.demo.Model.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class NotificationRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void addNotification(Notification notification) {
        String sql = "INSERT INTO notification (content, date) VALUES (?, ?)";
        jdbcTemplate.update(sql, notification.getContent(), notification.getDate());
    }

    public List<Notification> getAll(String adopterUserName) {
        String sql = "SELECT n.id, n.content, n.date FROM notification n " +
                "JOIN notification_adopter na ON n.id = na.id_of_notification " +
                "WHERE na.user_name_of_adopter = ? " +
                "ORDER BY n.date DESC";

        return jdbcTemplate.query(sql, new Object[]{adopterUserName}, new NotificationMapper());
    }

    private static class NotificationMapper implements RowMapper<Notification> {
        @Override
        public Notification mapRow(ResultSet resultSet, int i) throws SQLException {
            Notification notification = new Notification();
            notification.setID(resultSet.getInt("id"));
            notification.setContent(resultSet.getString("content"));
            notification.setDate(resultSet.getDate("date"));
            return notification;
        }
    }
}
