package com.example.demo.Service;

import com.example.demo.Enum.DocumentType;
import com.example.demo.Model.PetDocument;
import com.example.demo.Repository.PetDocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetDocumentService {

    @Autowired
    private PetDocumentRepository petDocumentRepository;

    public void savePetDocument(PetDocument petDocument) {
        petDocumentRepository.savePetDocument(petDocument);
    }

    public PetDocument getPetDocumentById(int id) {
        return petDocumentRepository.getPetDocumentById(id);
    }

    public List<PetDocument> getAllPetDocuments() {
        return petDocumentRepository.getAllPetDocuments();
    }

    public void deletePetDocument(int id) {
        petDocumentRepository.deletePetDocument(id);
    }

    public List<PetDocument> getAllDocumentsByPetId(int petId) {
        return petDocumentRepository.getAllDocumentsByPetId(petId);
    }

    public List<PetDocument> getAllImagesByPetId(int petId) {
        return petDocumentRepository.getAllImagesByPetId(petId);
    }

    public List<PetDocument> getDocumentsByTypeAndPetId(int petId, DocumentType documentType) {
        return petDocumentRepository.getDocumentsByTypeAndPetId(petId, documentType);
    }

    public void deleteAllDocumentsByPetId(int petId) {
        petDocumentRepository.deleteAllDocumentsByPetId(petId);
    }
}
