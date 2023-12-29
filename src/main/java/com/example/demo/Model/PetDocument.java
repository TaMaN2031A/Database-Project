package com.example.demo.Model;

import com.example.demo.Enum.DocumentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PetDocument {
    private int id;
    private DocumentType documentType;
    private byte[] documentContent; // Assuming document content is stored as a byte array
    private int petId;

    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public DocumentType getDocumentType() {
        return documentType;
    }

    public void setDocumentType(DocumentType documentType) {
        this.documentType = documentType;
    }

    public byte[] getDocumentContent() {
        return documentContent;
    }

    public void setDocumentContent(byte[] documentContent) {
        this.documentContent = documentContent;
    }

    public int getPetId() {
        return petId;
    }

    public void setPetId(int petId) {
        this.petId = petId;
    }

    @Override
    public String toString() {
        return "PetDocument{" +
                "id=" + id +
                ", documentType=" + documentType +
                ", documentContent=" + Arrays.toString(documentContent) +
                ", petId=" + petId +
                '}';
    }
}
