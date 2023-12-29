package com.example.demo;

import com.example.demo.Model.Pet;
import com.example.demo.Model.Shelter;
import com.example.demo.Model.SpecieBreed;
import com.example.demo.Repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(ManagerRepository managerRepository, ShelterRepository shelterRepository,
                                        StaffRepository staffRepository, PetRepository petRepository,
                                        SpecieBreedRepository specieBreedRepository, NotificationRepository notificationRepository,
                                        NotificationAdopterRepository notificationAdopterRepository, RecordRepository recordRepository,
                                        AppRepository applicationRepository) {
        return args -> {
            System.out.println(managerRepository.getAllManagers());
            Shelter shelter = new Shelter();
            shelter.setContactInfo("01069816302");
            shelter.setName("Tor Saina'a");
            shelter.setLocation("Saina'a");
            shelterRepository.addShelter(shelter);
//			Staff staff = new Staff();
////			staff.setContactInfo("01069816302");
////			staff.setRole("Vet");
////			staff.setIdOfShelter(5);
////			staff.setName("Kimo Tarek");
////			staffRepository.addStaff(staff);
//            SpecieBreed specieBreed = new SpecieBreed("Horse", "Arabian Home");
//            specieBreedRepository.addSpecieBreed(specieBreed);
//        Pet pet = new Pet();
//        pet.setName("Al Sar7an");
//        pet.setBreed("Arabian Home");
//        pet.setAge(3);
//        pet.setGender(1); // Assuming 1 represents male and 2 represents female
//        pet.setBehaviour("Friendly");
//        pet.setHealthStatus("Good");
//        pet.setNeutering(1); // Assuming 1 represents neutered and 0 represents not neutered
//        pet.setVaccination(1); // Assuming 1 represents vaccinated and 0 represents not vaccinated
//        pet.setIdOfShelter(1);
//        petRepository.addPet(pet);

////			Notification notification = new Notification();
////			notification.setContent("Sobhan Rabeka Rabb El Ezzate Amma yasefon");
////			notification.setDate(new Date(20230124));
////			notificationRepository.addNotification(notification);
////			NotificationAdopter notificationAdopter = new NotificationAdopter(3,1);
////			notificationAdopterRepository.addNotificationAdopter(notificationAdopter);
//			Record record = new Record();
//			record.setStatus("Accepted");
//			record.setPetId(2);
//			record.setAdopterUserName("Adopter1");
//
//			recordRepository.addRecord(record);
//			App application = new App();
//			application.setStatus("Wait");
//			application.setPetId(4);
//			application.setAdopterId(2);
//			applicationRepository.addRecord(application);
        };
    }
}



