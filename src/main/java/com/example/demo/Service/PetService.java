package com.example.demo.Service;

import com.example.demo.DTO.PetDTO;
import com.example.demo.Model.Pet;
import com.example.demo.Model.PetDocument;
import com.example.demo.Repository.PetDocumentRepository;
import com.example.demo.Repository.PetRepository;
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
    PetDocumentRepository petDocumentRepository;

    public ResponseEntity<Map<String, String>> addPet(Pet pet) {
//        try {

            petRepository.savePet(pet);
            if(pet.getPetDocuments() == null) {
                return new ResponseEntity<>(HttpStatus.OK);
            }
            for (PetDocument petDocument : pet.getPetDocuments()) {
                petDocument.setPetId(pet.getID());
                petDocumentRepository.savePetDocument(petDocument);
            }

            return new ResponseEntity<>(HttpStatus.OK);
//        /} catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
    }

    public ResponseEntity<Map<String, String>> updatePet(Pet pet) {
        try {
            petRepository.updatePet(pet);
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
            petsDTO.add(new PetDTO(pet));
        }
        return petsDTO;
    }

    public List<PetDTO> getShelterPets(int shelterId) {
        List<Pet> pets= petRepository.getPetsByShelterId(shelterId);
        List<PetDTO> petsDTO = new ArrayList<>();
        for (Pet pet : pets) {
            pet.setPetDocuments(petDocumentRepository.getAllDocumentsByPetId(pet.getID()));
            petsDTO.add(new PetDTO(pet));
        }
        return petsDTO;
    }
    public Pet getPetProfile(int petId) {
        Pet pet=petRepository.getPetById(petId);
        pet.setPetDocuments(petDocumentRepository.getAllDocumentsByPetId(pet.getID()));
        return pet;
    }
}
