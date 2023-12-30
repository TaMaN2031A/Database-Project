package com.example.demo.Service;

import com.example.demo.DTO.PetDTO;
import com.example.demo.Model.Pet;
import com.example.demo.Model.PetDocument;
import com.example.demo.Repository.PetDocumentRepository;
import com.example.demo.Repository.PetRepository;
import com.example.demo.Repository.ShelterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class PetService {


    @Autowired
    PetRepository petRepository;

    @Autowired
    ShelterRepository shelterRepository;
    @Autowired
    PetDocumentRepository petDocumentRepository;
    public ResponseEntity<?> addPet(Pet pet) {
        try {
        Long id=petRepository.addPet(pet);

        return new ResponseEntity<>(id,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
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

    public List<PetDTO> getAllPets() {
        List<Pet> pets= petRepository.getAllPets();
        List<PetDTO> petsDTO = new ArrayList<>();
        for (Pet pet : pets) {
            pet.setPetDocuments(petDocumentRepository.getAllDocumentsByPetId(pet.getID()));
            petsDTO.add(new PetDTO(pet, shelterRepository));
        }
        return petsDTO;
    }

    public List<PetDTO> getShelterPets(int shelterId) {
        List<Pet> pets= petRepository.getPetsByShelterId(shelterId);
        List<PetDTO> petsDTO = new ArrayList<>();
        for (Pet pet : pets) {
            pet.setPetDocuments(petDocumentRepository.getAllDocumentsByPetId(pet.getID()));
            petsDTO.add(new PetDTO(pet, shelterRepository));
        }
        return petsDTO;
    }
    public Pet getPetProfile(int petId) {
        Pet pet=petRepository.getPetById(petId);
        pet.setPetDocuments(petDocumentRepository.getAllDocumentsByPetId(pet.getID()));
        return pet;
    }

    public List<PetDTO> searchPets(String criteria, String searchValue) {
        List<Pet> pets = new ArrayList<>();
        if (criteria.equals("breed")) {pets=petRepository.getPetsByBreed(searchValue);}
        else if (criteria.equals("age")) {pets=petRepository.getPetsByAge(Integer.parseInt(searchValue));}
        else if (criteria.equals("location")) {pets=petRepository.getPetsByShelterLocation(searchValue);}

        List<PetDTO> petsDTO = new ArrayList<>();
        assert pets != null;
        for (Pet pet : pets) {
            pet.setPetDocuments(petDocumentRepository.getAllDocumentsByPetId(pet.getID()));
            petsDTO.add(new PetDTO(pet, shelterRepository));
        }
        return petsDTO;
    }

}
