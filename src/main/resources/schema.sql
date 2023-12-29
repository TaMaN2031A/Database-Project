CREATE TABLE manager (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
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
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255),
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
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255),
    role VARCHAR(255),
    contact_info VARCHAR(255),
    password VARCHAR(255),
    id_of_shelter INT,
    FOREIGN KEY (id_of_shelter) REFERENCES shelter(id) ON DELETE RESTRICT
);
CREATE TABLE notification_adopter (
    id_of_notification INT,
    id_of_adopter INT,
    PRIMARY KEY (id_of_notification, id_of_adopter),
    FOREIGN KEY (id_of_notification) REFERENCES notification(id),
    FOREIGN KEY (id_of_adopter) REFERENCES adopter(id)
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
    FOREIGN KEY (id_of_shelter) REFERENCES shelter(id),
    FOREIGN KEY (breed) REFERENCES specie_breed(breed)
);
CREATE TABLE application (
    id INT PRIMARY KEY AUTO_INCREMENT,
    status VARCHAR(255),
    pet_id INT,
    adopter_id INT,
    FOREIGN KEY (pet_id) REFERENCES pet(id),
    FOREIGN KEY (adopter_id) REFERENCES adopter(id)
);
CREATE TABLE record (
    id INT PRIMARY KEY AUTO_INCREMENT,
    status VARCHAR(255),
    pet_id INT,
    adopter_id INT,
    FOREIGN KEY (pet_id) REFERENCES pet(id),
    FOREIGN KEY (adopter_id) REFERENCES adopter(id)
);

CREATE TABLE pet_document (
    id INT PRIMARY KEY AUTO_INCREMENT,
    document_type ENUM('IMAGE', 'MEDICAL_RECORD', 'OTHER'),
    document_content BLOB,
    pet_id INT,
    FOREIGN KEY (pet_id) REFERENCES pet(id)
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
    INSERT INTO notification_adopter (id_of_notification, id_of_adopter)
    SELECT @last_notification_id, id FROM adopter;
END;

//

DELIMITER ;
