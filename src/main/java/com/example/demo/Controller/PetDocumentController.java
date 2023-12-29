package com.example.demo.Controller;

import com.example.demo.Enum.DocumentType;
import com.example.demo.Model.PetDocument;
import com.example.demo.Service.PetDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pet-documents")
public class PetDocumentController {

    @Autowired
    private PetDocumentService petDocumentService;

    @PostMapping
    public void savePetDocument(@RequestBody PetDocument petDocument) {
        petDocumentService.savePetDocument(petDocument);
    }

    @GetMapping("/{id}")
    public PetDocument getPetDocumentById(@PathVariable int id) {
        return petDocumentService.getPetDocumentById(id);
    }

    @GetMapping
    public List<PetDocument> getAllPetDocuments() {
        return petDocumentService.getAllPetDocuments();
    }

    @DeleteMapping("/{id}")
    public void deletePetDocument(@PathVariable int id) {
        petDocumentService.deletePetDocument(id);
    }

    @GetMapping("/pet/{petId}")
    public List<PetDocument> getAllDocumentsByPetId(@PathVariable int petId) {
        return petDocumentService.getAllDocumentsByPetId(petId);
    }

    @GetMapping("/pet/{petId}/images")
    public List<PetDocument> getAllImagesByPetId(@PathVariable int petId) {
        return petDocumentService.getAllImagesByPetId(petId);
    }

    @GetMapping("/pet/{petId}/documents")
    public List<PetDocument> getDocumentsByTypeAndPetId(
            @PathVariable int petId,
            @RequestParam DocumentType documentType) {
        return petDocumentService.getDocumentsByTypeAndPetId(petId, documentType);
    }

    @DeleteMapping("/pet/{petId}")
    public void deleteAllDocumentsByPetId(@PathVariable int petId) {
        petDocumentService.deleteAllDocumentsByPetId(petId);
    }
}
