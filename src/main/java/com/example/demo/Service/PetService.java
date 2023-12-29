package com.example.demo.Service;

import com.example.demo.Model.Pet;
import com.example.demo.Repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PetService {

    @Autowired
    PetRepository petRepository;

    public ResponseEntity<?> addPet(Pet pet) {
//        try {
            Long id=petRepository.addPet(pet);
            System.out.println(id);
            return new ResponseEntity<>(id,HttpStatus.OK);
//        /} catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
    }

    public ResponseEntity<Map<String, String>> updatePet(Pet pet) {
        try {
            System.out.println("up");
            System.out.println(pet.toString());

            petRepository.updatePet(pet);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    public ResponseEntity<Map<String, String>> deletePet(int petID) {
        try {

            petRepository.deletePet(petID);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }



}
