package com.example.demo.Controller;

import com.example.demo.Enum.DocumentType;
import com.example.demo.Model.PetDocument;
import com.example.demo.Service.PetDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/pet-documents")
public class PetDocumentController {

    @Autowired
    private PetDocumentService petDocumentService;

    @PostMapping("/upload")
    public ResponseEntity<String> savePetDocument(
            @RequestPart("file") MultipartFile file,
            @RequestPart("petId") int petId,
            @RequestPart("fileType") DocumentType documentType

    ) {
        try {
            // Validate and process the uploaded file
            PetDocument petDocument = new PetDocument();
            petDocument.setDocumentContent(file.getBytes());
            petDocument.setPetId(petId);
            petDocument.setDocumentType(documentType);

            // Save information to the database using petDocumentService
            petDocumentService.savePetDocument(petDocument);

            return ResponseEntity.ok("File uploaded successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading file");
        }
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
