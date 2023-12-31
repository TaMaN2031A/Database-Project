package com.example.demo.Service;

import com.example.demo.Model.Shelter;
import com.example.demo.Repository.ShelterRepository;
import com.example.demo.Repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Map;

@Service
public class ShelterService {
    @Autowired
    ShelterRepository shelterRepository;

    @Autowired
    StaffRepository staffRepository;

    public ResponseEntity<?> addShelter(Shelter shelter) {
        try {
            long shelter_id=shelterRepository.addShelter(shelter);
            return new ResponseEntity<>(shelter_id,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> updateShelter(Shelter shelter) {
        try {
            shelterRepository.updateShelter(shelter);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    public ResponseEntity<?> deleteShelter(int shelter_id) {
        try {
            shelterRepository.deleteShelter(shelter_id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> getShelters() {
        try {
            ArrayList<Shelter> shelters=shelterRepository.getShelters();
            return new ResponseEntity<>(shelters,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public int getShelterNameByStaffUserName(String staffUserName) {
        int shelterId = staffRepository.getShelterIdByUserName(staffUserName);
        return shelterId;
    }


}
