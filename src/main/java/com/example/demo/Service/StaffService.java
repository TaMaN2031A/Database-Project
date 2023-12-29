package com.example.demo.Service;

import com.example.demo.Model.Staff;
import com.example.demo.Repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;
@Service
public class StaffService {
    @Autowired
    StaffRepository staffRepository;
    public ResponseEntity<Map<String, String>> addStaff(Staff staff){
        try {
            staffRepository.addStaff(staff);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
