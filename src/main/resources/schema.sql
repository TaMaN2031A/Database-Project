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

