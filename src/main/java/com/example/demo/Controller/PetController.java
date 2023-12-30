package com.example.demo.Controller;

import com.example.demo.DTO.PetDTO;
import com.example.demo.Model.Pet;
import com.example.demo.Service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/pet")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class PetController {

    @Autowired
    private PetService petService;

    @PostMapping("/add")
    public ResponseEntity<?> addPet(@RequestBody Pet pet) {
        return petService.addPet(pet);
    }

    @PostMapping("/update")
    public ResponseEntity<?> updatePet(@RequestBody Pet pet) {
        return petService.updatePet(pet);
    }

    @PostMapping("/delete/{petID}")
    public ResponseEntity<?> deletePet(@PathVariable int petID) {
        return petService.deletePet(petID);
    }


    @GetMapping("/all")
    public List<PetDTO> getAllPets() {
        return petService.getAllPets();
    }
    @GetMapping("/shelterPets/{shelter_id}")
    public List<PetDTO> getShelterPets(@PathVariable int shelter_id) {
        return petService.getShelterPets(shelter_id);
    }
    @GetMapping("/get_pet_profile/{petId}")
    public Pet getPetProfile(@PathVariable int petId) {
        return petService.getPetProfile(petId);
    }

    @GetMapping("/searchPets/{criteria}/{value}")
    public List<PetDTO> searchPets(@PathVariable String criteria, @PathVariable String value) {

        System.out.println("criteria: " + criteria + " value: " + value);
        List<PetDTO> petDTOS= petService.searchPets(criteria, value);
        for(PetDTO petDTO : petDTOS) {
            System.out.println(petDTO.toString());
        }
        return petDTOS;
    }
}
