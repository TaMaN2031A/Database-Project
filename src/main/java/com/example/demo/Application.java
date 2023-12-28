package com.example.demo;

import com.example.demo.Model.*;
import com.example.demo.Repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.Date;


@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	@Bean
	CommandLineRunner commandLineRunner(ManagerRepository managerRepository, ShelterRepository shelterRepository,
										StaffRepository staffRepository, PetRepository petRepository,
										SpecieBreedRepository specieBreedRepository, NotificationRepository notificationRepository){
		return args ->{
			System.out.println(managerRepository.getAllManagers());
			Shelter shelter = new Shelter();
			shelter.setContactInfo("01069816302");
			shelter.setName("Tor Saina'a");
			shelter.setLocation("Saina'a");
			shelterRepository.addShelter(shelter);
			Staff staff = new Staff();
			staff.setContactInfo("01069816302");
			staff.setRole("Vet");
			staff.setIdOfShelter(5);
			staff.setName("Kimo Tarek");
			staffRepository.addStaff(staff);
			Pet pet = new Pet();
			pet.setName("Fluffy");
			pet.setBreed("Breed1");
			pet.setAge(3);
			pet.setGender(1); // Assuming 1 represents male and 2 represents female
			pet.setBehaviour("Friendly");
			pet.setHealthState("Good");
			pet.setNeutering(1); // Assuming 1 represents neutered and 0 represents not neutered
			pet.setVaccination(1); // Assuming 1 represents vaccinated and 0 represents not vaccinated
			pet.setIdOfShelter(1);
			petRepository.addPet(pet);
			SpecieBreed specieBreed = new SpecieBreed("Horse", "Arabian Grey");
			specieBreedRepository.addSpecieBreed(specieBreed);
			Notification notification = new Notification();
			notification.setContent("Sobhan Rabeka Rabb El Ezzate Amma yasefon");
			notification.setDate(new Date(20230124));
			notificationRepository.addNotification(notification);
		};
	}

}
