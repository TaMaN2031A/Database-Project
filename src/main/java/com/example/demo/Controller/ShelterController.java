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
    public ResponseEntity<?> addShelter(@RequestBody Shelter shelter) {
        return shelterService.addShelter(shelter);
    }

    @PostMapping("/updateShelter")
    public ResponseEntity<?> updateShelter(@RequestBody Shelter shelter) {
        return shelterService.updateShelter(shelter);
    }
    @PostMapping("/deleteShelter/{shelter_id}")
    public ResponseEntity<?> deleteShelter(@PathVariable int shelter_id) {
        return shelterService.deleteShelter(shelter_id);
    }
    @GetMapping("/getShelter")
    public ResponseEntity<?> getShelter() {
        return shelterService.getShelters();
    }

}
