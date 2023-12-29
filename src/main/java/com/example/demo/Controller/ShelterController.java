package com.example.demo.Controller;

import com.example.demo.Model.Shelter;
import com.example.demo.Service.ShelterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/shelter")
public class ShelterController {

    @Autowired
    private ShelterService shelterService;

    @PostMapping("/addShelter")
    public ResponseEntity<Map<String, String>> addShelter(@RequestBody Shelter shelter) {
        return shelterService.addShelter(shelter);
    }

    @PostMapping("/updateShelter")
    public ResponseEntity<Map<String, String>> updateShelter(@RequestBody Shelter shelter) {
        return shelterService.updateShelter(shelter);
    }

}
