package com.example.demo.Controller;

import com.example.demo.Model.Notification;
import com.example.demo.Model.Pet;
import com.example.demo.Service.NotificationService;
import com.example.demo.Service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/notification")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/get_notification/{userName}")
    public List<Notification> getNotification(@PathVariable String userName) {
        System.out.println(userName);
        return notificationService.getAll(userName);
    }

}
