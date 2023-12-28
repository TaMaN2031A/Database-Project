package com.example.demo.Repository;

import com.example.demo.Model.NotificationAdopter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class NotificationAdopterRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void addNotificationAdopter(NotificationAdopter notificationAdopter) {
        String sql = "INSERT INTO notification_adopter (id_of_notification, id_of_adopter) VALUES (?, ?)";
        jdbcTemplate.update(sql, notificationAdopter.getIdOfNotification(), notificationAdopter.getIdOfAdopter());
    }
}
