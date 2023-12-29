package com.example.demo.Service;

import com.example.demo.DTO.AppGetDTO;
import com.example.demo.Model.App;
import com.example.demo.Repository.AppRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AppService {

    @Autowired
    AppRepository appRepository;

    public ResponseEntity<Map<String, String>> addApp(App app) {
        try {
            appRepository.addApp(app);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Map<String, String>> updateApp(App app) {
        try {
            appRepository.updateApp(app);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public List<AppGetDTO> getApplications(String staffName) {
        return appRepository.getAllApp(staffName);
    }
    public ResponseEntity<Map<String, String>> replyToApplication(String reply, String id){
        appRepository.replyToApp(reply, Integer.parseInt(id));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
