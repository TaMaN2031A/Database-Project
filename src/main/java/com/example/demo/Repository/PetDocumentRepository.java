package com.example.demo.Repository;

import com.example.demo.Enum.DocumentType;
import com.example.demo.Model.PetDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.SqlLobValue;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PetDocumentRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    public void savePetDocument(PetDocument petDocument) {
        String sql = "INSERT INTO pet_document (document_type, document_content, pet_id) VALUES (?, ?, ?)";
        SqlLobValue lobValue = new SqlLobValue(petDocument.getDocumentContent());
        jdbcTemplate.update(sql, petDocument.getDocumentType().name(), petDocument.getDocumentContent(), petDocument.getPetId());
    }


    public PetDocument getPetDocumentById(int id) {
        String sql = "SELECT * FROM pet_document WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<>(PetDocument.class));
    }

    public List<PetDocument> getAllPetDocuments() {
        String sql = "SELECT * FROM pet_document";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(PetDocument.class));
    }

    public void deletePetDocument(int id) {
        String sql = "DELETE FROM pet_document WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    public List<PetDocument> getAllDocumentsByPetId(int petId) {
        String sql = "SELECT * FROM pet_document WHERE pet_id = ?";
        return jdbcTemplate.query(sql, new Object[]{petId}, new BeanPropertyRowMapper<>(PetDocument.class));
    }

    public List<PetDocument> getAllImagesByPetId(int petId) {
        String sql = "SELECT * FROM pet_document WHERE pet_id = ? AND document_type = 'IMAGE'";
        return jdbcTemplate.query(sql, new Object[]{petId}, new BeanPropertyRowMapper<>(PetDocument.class));
    }


    public List<PetDocument> getDocumentsByTypeAndPetId(int petId, DocumentType documentType) {
        String sql = "SELECT * FROM pet_document WHERE pet_id = ? AND document_type = ?";
        return jdbcTemplate.query(sql, new Object[]{petId, documentType.name()}, new BeanPropertyRowMapper<>(PetDocument.class));
    }
    public void deleteAllDocumentsByPetId(int petId) {
        String sql = "DELETE FROM pet_document WHERE pet_id = ?";
        jdbcTemplate.update(sql, petId);
    }
}
