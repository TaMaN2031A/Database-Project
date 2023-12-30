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
public class PetController {

    @Autowired
    private PetService petService;

    @PostMapping("/add")
    public ResponseEntity<Map<String, String>> addPet(@RequestBody Pet pet) {



        return petService.addPet(pet);
    }

    @PostMapping("/update")
    public ResponseEntity<Map<String, String>> updatePet(@RequestBody Pet pet) {
        return petService.updatePet(pet);
    }
    @GetMapping("/all")
    public List<PetDTO> getAllPets() {
        return petService.getAllPets();
    }
    @GetMapping("/shelterPets")
    public List<PetDTO> getShelterPets(@RequestParam int shelterId) {
        return petService.getShelterPets(shelterId);
    }
    @GetMapping("/Pet")
    public Pet getPetProfile(@RequestParam int petId) {
        return petService.getPetProfile(petId);
    }
}
