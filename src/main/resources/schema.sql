drop database db;
create database db;
use db;
DROP DATABASE IF EXISTS db;
CREATE DATABASE db;
USE db;

CREATE TABLE manager (
    user_name VARCHAR(255) PRIMARY KEY,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    password VARCHAR(255),
    contact_info VARCHAR(255)
);

CREATE TABLE shelter (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255),
    contactInfo VARCHAR(255),
    location VARCHAR(255)
);

CREATE TABLE adopter (
    user_name VARCHAR(255) PRIMARY KEY,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    password VARCHAR(255),
    contactInfo VARCHAR(255)
);

CREATE TABLE specie_breed (
    breed VARCHAR(255) PRIMARY KEY,
    specie VARCHAR(255)
);

CREATE TABLE notification (
    id INT PRIMARY KEY AUTO_INCREMENT,
    content VARCHAR(512),
    date DATETIME
);

CREATE TABLE staff (
    user_name VARCHAR(255) PRIMARY KEY,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    role VARCHAR(255),
    contact_info VARCHAR(255),
    password VARCHAR(255),
    id_of_shelter INT,
    FOREIGN KEY (id_of_shelter) REFERENCES shelter(id) ON DELETE RESTRICT ON UPDATE CASCADE
);

CREATE TABLE notification_adopter (
    id_of_notification INT,
    user_name_of_adopter VARCHAR(255),
    PRIMARY KEY (id_of_notification, user_name_of_adopter),
    FOREIGN KEY (id_of_notification) REFERENCES notification(id) ON DELETE CASCADE,
    FOREIGN KEY (user_name_of_adopter) REFERENCES adopter(user_name) ON DELETE CASCADE
);

CREATE TABLE pet (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255),
    breed VARCHAR(255),
    age INT,
    gender INT,
    behaviour VARCHAR(255),
    health_status VARCHAR(255),
    description VARCHAR(255),
    neutering INT,
    vaccination INT,
    id_of_shelter INT,
    adopted BOOLEAN,
    FOREIGN KEY (id_of_shelter) REFERENCES shelter(id) ON DELETE RESTRICT ON UPDATE CASCADE,
    FOREIGN KEY (breed) REFERENCES specie_breed(breed) ON DELETE RESTRICT ON UPDATE CASCADE
);

CREATE TABLE application (
    id INT PRIMARY KEY AUTO_INCREMENT,
    status VARCHAR(255),
    pet_id INT,
    adopter_user_name VARCHAR(255),
    FOREIGN KEY (pet_id) REFERENCES pet(id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (adopter_user_name) REFERENCES adopter(user_name) ON DELETE RESTRICT ON UPDATE CASCADE
);

CREATE TABLE record (
    id INT PRIMARY KEY AUTO_INCREMENT,
    status VARCHAR(255),
    pet_id INT,
    adopter_user_name VARCHAR(255),
    FOREIGN KEY (pet_id) REFERENCES pet(id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (adopter_user_name) REFERENCES adopter(user_name) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE pet_document (
    id INT PRIMARY KEY AUTO_INCREMENT,
    document_type ENUM('IMAGE', 'MEDICAL_RECORD', 'OTHER'),
    document_content BLOB,
    pet_id INT,
    FOREIGN KEY (pet_id) REFERENCES pet(id) ON DELETE CASCADE ON UPDATE CASCADE
);

DELIMITER //

CREATE TRIGGER after_insert_pet
AFTER INSERT
ON pet FOR EACH ROW
BEGIN
    -- Insert into notification table
    INSERT INTO notification (content, date)
    VALUES (
        CONCAT(
            'A new pet, ', NEW.name, ', is added. ',
            (SELECT specie FROM specie_breed WHERE breed = NEW.breed),
            ', its age is ', NEW.age,
            ', its gender is ', CASE WHEN NEW.gender = 1 THEN 'female' ELSE 'male' END,
            ', its behaviour is ', NEW.behaviour,
            ', its health_status is ', NEW.health_status,
            ', it is ', CASE WHEN NEW.neutering = 0 THEN 'not ' ELSE '' END, 'neutered',
            ', it took ', CASE WHEN NEW.vaccination = 0 THEN 'no' ELSE '' END, ' vaccinations.',
            ' You could find it in shelter ', NEW.id_of_shelter,
            ' ', (SELECT name FROM shelter WHERE id = NEW.id_of_shelter),
            ' which is located in ', (SELECT location FROM shelter WHERE id = NEW.id_of_shelter)
        ),
        NOW() -- Current date and time
    );

    -- Get the ID of the last inserted notification
    SET @last_notification_id = (SELECT LAST_INSERT_ID());

    -- Insert into notification_adopter table for all adopters
    INSERT INTO notification_adopter (id_of_notification, user_name_of_adopter)
    SELECT @last_notification_id, user_name FROM adopter;
END;

//

DELIMITER ;


DELIMITER //

CREATE TRIGGER before_update_application
BEFORE UPDATE ON application FOR EACH ROW
BEGIN
    DECLARE pet_breed VARCHAR(255);
    DECLARE pet_name VARCHAR(255);
    DECLARE new_status VARCHAR(255);
    DECLARE notification_content VARCHAR(512);

    -- Get pet information from the associated application
    SELECT p.breed, p.name INTO pet_breed, pet_name
    FROM pet p
    WHERE p.id = NEW.pet_id;

    -- Set new_status based on the updated application state
    IF NEW.status = 'accepted' THEN
        SET new_status = 'adopted';
    ELSEIF NEW.status = 'declined' THEN
        SET new_status = 'declined';
    END IF;

    -- Modify the values in the NEW alias directly
    SET NEW.status = new_status;

    -- Insert a tuple into the record table
    INSERT INTO record (status, pet_id, adopter_user_name)
    VALUES (new_status, NEW.pet_id, NEW.adopter_user_name);

    -- Create notification content based on the updated application state
    SET notification_content = CONCAT(
        'Your Application to get the pet "', pet_breed, ' ', pet_name,
        '" was "', new_status, '"'
    );

    -- Insert a tuple into the notification table
    INSERT INTO notification (content, date)
    VALUES (notification_content, NOW());

    -- If accepted, set the adopted attribute to true in the pet table
    IF NEW.status = 'accepted' THEN
        UPDATE pet SET adopted = TRUE WHERE id = NEW.pet_id;
    END IF;
END;

//

DELIMITER ;

