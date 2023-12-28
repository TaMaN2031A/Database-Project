package com.example.demo.Repository;

import com.example.demo.Model.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class NotificationRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void addNotification(Notification notification) {
        String sql = "INSERT INTO notification (content, date) VALUES (?, ?)";
        jdbcTemplate.update(sql, notification.getContent(), notification.getDate());
    }
}
