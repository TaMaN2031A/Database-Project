package com.example.demo.DTO;

import com.example.demo.Enum.DocumentType;
import com.example.demo.Model.Pet;
import com.example.demo.Model.PetDocument;
import com.example.demo.Repository.ShelterRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;

@Data

@AllArgsConstructor
public class PetDTO {

    ShelterRepository shelterRepository;
    int id;
    String name;

    @Override
    public String toString() {
        return "PetDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", breed='" + breed + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                ", healthStatus='" + healthStatus + '\'' +
                ", behaviour='" + behaviour + '\'' +
                ", idOfShelter=" + idOfShelter +
                ", neutering=" + neutering +
                ", vaccination=" + vaccination +
                '}';
    }

    String breed;
    int age;
    int gender;
    String healthStatus;
    String behaviour;
    byte[] image;
    int idOfShelter;
    int neutering;
    int  vaccination;
    String shelterLocation;
    public PetDTO (Pet pet, ShelterRepository shelterRepository){
        this.shelterRepository=shelterRepository;
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
        this.shelterLocation=shelterRepository.getShelterLocationById(this.idOfShelter);
        if(pet.getPetDocuments()==null) this.image=null;
        else{
            for(PetDocument petDocument : pet.getPetDocuments()){
                if(petDocument.getDocumentType()== DocumentType.IMAGE) this.image=petDocument.getDocumentContent();
            }


        }

    }
}