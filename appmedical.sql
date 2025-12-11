-- Création de la base de données
CREATE DATABASE IF NOT EXISTS cabinet_medical;
USE cabinet_medical;

-- Table Catégorie
CREATE TABLE Categorie (
    id INT PRIMARY KEY AUTO_INCREMENT,
    designation VARCHAR(100) NOT NULL,
    description TEXT
);

-- Table Patient
CREATE TABLE Patient (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nom VARCHAR(100) NOT NULL,
    telephone VARCHAR(20),
    email VARCHAR(100)
);

-- Table Utilisateur
CREATE TABLE Utilisateur (
    id INT PRIMARY KEY AUTO_INCREMENT,
    login VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    type ENUM('MEDECIN', 'ASSISTANT') NOT NULL
);

-- Table Consultation
CREATE TABLE Consultation (
    id INT PRIMARY KEY AUTO_INCREMENT,
    date_consultation DATETIME NOT NULL,
    description TEXT,
    prix DECIMAL(10,2) NOT NULL,
    patient_id INT,
    categorie_id INT,
    FOREIGN KEY (patient_id) REFERENCES Patient(id) ON DELETE CASCADE,
    FOREIGN KEY (categorie_id) REFERENCES Categorie(id)
);

ALTER TABLE Consultation ADD COLUMN statut_paiement VARCHAR(20) DEFAULT 'Impayé';
ALTER TABLE Consultation ADD COLUMN relance TINYINT DEFAULT 0;

-- Insertion des données de base
INSERT INTO Categorie (designation, description) VALUES 
('Consultation normale', 'Consultation médicale standard'),
('Contrôle', 'Visite de contrôle'),
('Urgence', 'Consultation en urgence');

select * from Consultation;
select * from Patient;

INSERT INTO Utilisateur (login, password, type) VALUES 
('medecin', 'medecin123', 'MEDECIN'),
('assistante', 'assistante123', 'ASSISTANT');

INSERT INTO Patient (nom, telephone, email) VALUES 
('Dupont Jean', '0123456789', 'jean.dupont@email.com'),
('Martin Marie', '0987654321', 'marie.martin@email.com');

select * from Utilisateur;