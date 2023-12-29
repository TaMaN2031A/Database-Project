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

    @PostMapping("/addStaff")
    public ResponseEntity<Map<String, String>> addShelter(@RequestBody Staff staff) {
        return staffService.addStaff(staff);
    }


}
