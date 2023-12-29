package com.example.demo.Service;


import com.example.demo.Model.Shelter;
import com.example.demo.Model.SpecieBreed;
import com.example.demo.Repository.ShelterRepository;
import com.example.demo.Repository.SpecieBreedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service

public class SpecieBreedService {
    @Autowired
    SpecieBreedRepository speciebreedRepository ;

    public ResponseEntity<?> addspeciebreed(SpecieBreed specieBreed) {
        try {
           speciebreedRepository.addSpecieBreed(specieBreed);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> updatespecieBreed(SpecieBreed specieBreed) {
        try {
            speciebreedRepository.updateSpecieBreed(specieBreed);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    public ResponseEntity<?> deletespecieBreed(String  breed) {
        try {
            speciebreedRepository.deleteSpecieBreed(breed);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> getspecieBreed() {
        try {
            ArrayList<SpecieBreed> shelters=speciebreedRepository.getSpecieBreed();
            return new ResponseEntity<>(shelters,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
