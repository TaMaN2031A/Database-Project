package com.example.demo.Controller;

import com.example.demo.Model.Staff;
import com.example.demo.Service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/staff")
public class StaffController {

    @Autowired
    private StaffService staffService;

    @PostMapping("/add")
    public ResponseEntity<Map<String, String>> addStaff(@RequestBody Staff staff) {
        return staffService.addStaff(staff);
    }

    @PostMapping("/update")
    public ResponseEntity<Map<String, String>> updateStaff(@RequestBody Staff staff) {
        return staffService.updateStaff(staff);
    }
}
