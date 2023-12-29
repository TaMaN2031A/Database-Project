package com.example.demo.Service;

import com.example.demo.Controller.NotificationController;
import com.example.demo.Model.Notification;
import com.example.demo.Repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class NotificationService {
    @Autowired
    NotificationRepository notificationRepository;
    public List<Notification> getAll(String userName){
        return notificationRepository.getAll(userName);
    }
}
