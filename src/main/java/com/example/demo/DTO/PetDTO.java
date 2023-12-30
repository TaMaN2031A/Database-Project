package com.example.demo.DTO;

import com.example.demo.Enum.DocumentType;
import com.example.demo.Model.Pet;
import com.example.demo.Model.PetDocument;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

@AllArgsConstructor
public class PetDTO {
    int id;
    String name;
    String breed;
    int age;
    int gender;
    String healthStatus;
    String behaviour;
   byte[] image;
    int idOfShelter;
    int neutering;
    int  vaccination;
    public PetDTO (Pet pet){

        this.id = pet.getID();
        this.name = pet.getName();
        this.breed = pet.getBreed();
        this.gender = pet.getGender();
        this.age = pet.getAge();
        this.behaviour = pet.getBehaviour();
        this.healthStatus = pet.getHealthStatus();
        this.neutering = pet.getNeutering();
        this.vaccination = pet.getVaccination();
        this.idOfShelter = pet.getIdOfShelter();
        if(pet.getPetDocuments()==null) this.image=null;
        else{
        for(PetDocument petDocument : pet.getPetDocuments()){
            if(petDocument.getDocumentType()== DocumentType.IMAGE) this.image=petDocument.getDocumentContent();
        }


}

    }
}
