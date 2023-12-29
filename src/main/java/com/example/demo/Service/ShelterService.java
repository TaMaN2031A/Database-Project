package com.example.demo.Service;

import com.example.demo.Model.Shelter;
import com.example.demo.Repository.ShelterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ShelterService {
    @Autowired
    ShelterRepository shelterRepository;

    public ResponseEntity<Map<String, String>> addShelter(Shelter shelter) {
        try {
            shelterRepository.addShelter(shelter);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Map<String, String>> updateShelter(Shelter shelter) {
        try {
            shelterRepository.updateShelter(shelter);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
