DROP DATABASE IF EXISTS Erronka;
CREATE DATABASE IF NOT EXISTS Erronka;
USE Erronka;

CREATE TABLE kategoria (
    Id_kategoria INT PRIMARY KEY AUTO_INCREMENT,
    Deskribapena_kategoria VARCHAR(100),
    izena_kategoria VARCHAR(50) NOT NULL
);

CREATE TABLE erabiltzailea (
    Id_erabiltzailea INT PRIMARY KEY AUTO_INCREMENT,
    izena_erabiltzailea VARCHAR(50),
    abizena_erabiltzailea VARCHAR(50),
    emaila VARCHAR(50),
    pasaitza VARCHAR(50),     
    sorkuntza_data_erabiltzailea DATE
);

CREATE TABLE produktua (
    Id_produktua INT PRIMARY KEY AUTO_INCREMENT,
    Deskribapena_produktua VARCHAR(50),
    Izena_produktua VARCHAR(50),
    prezioa DECIMAL(10,2), 
    stocka INT,
    sorkuntza_data_produktua DATE,
    Id_kategoria INT,
    FOREIGN KEY (Id_kategoria) REFERENCES kategoria(Id_kategoria)
);

CREATE TABLE eskaera (
    Id_Eskaera INT PRIMARY KEY AUTO_INCREMENT,
    Id_erabiltzailea INT, 
    guztira DECIMAL(10,2), 
    data DATE,
    egoera VARCHAR(50),
    FOREIGN KEY (Id_erabiltzailea) REFERENCES erabiltzailea(Id_erabiltzailea)
);

CREATE TABLE produktu_eskaera (
    Id_produktua INT,
    Id_eskaera INT,
    Kopurua INT,
    Subtotala DECIMAL(10,2), 
    PRIMARY KEY (Id_produktua, Id_eskaera),
    FOREIGN KEY (Id_produktua) REFERENCES produktua(Id_produktua),
    FOREIGN KEY (Id_eskaera) REFERENCES eskaera(Id_eskaera)
);

INSERT INTO kategoria (Deskribapena_kategoria, izena_kategoria) VALUES
('Chaquetas kategoria berria', 'Chaquetas'),
('Sportwear arropa', 'Sportwear'),
('Calzado kategoria', 'Calzado'),
('Jerseys eta jertseak', 'Jerseys');

INSERT INTO erabiltzailea (izena_erabiltzailea, abizena_erabiltzailea, emaila, pasaitza, sorkuntza_data_erabiltzailea) VALUES
('Aitor', 'Bilbao', 'aitor.bilbao@email.eus', '12345', '2024-12-01'),   
('Miren', 'Donostia', 'miren.donostia@email.eus', '12345', '2024-12-05'), 
('Jon', 'Pamplona', 'jon.pamplona@email.eus', '12345', '2024-12-10'),     
('Ane', 'Vitoria', 'ane.vitoria@email.eus', '12345', '2024-12-15');       

INSERT INTO produktua (Deskribapena_produktua, Izena_produktua, prezioa, stocka, sorkuntza_data_produktua, Id_kategoria) VALUES
('Anorak berokia', 'Anorak', 120.00, 50, '2024-12-20', 1),         
('Plumifero gorria', 'Plumifero', 150.00, 30, '2024-12-20', 1),    
('Kamiseta termikoa', 'Termica', 55.00, 100, '2024-12-20', 2),     
('Zapatilla korrika', 'Zapatillas CSV', 75.00, 40, '2024-12-20', 3),
('Artilezko txaketa', 'Chaqueta de Lana', 130.00, 20, '2024-12-20', 4),
('Mendi botak', 'Botas', 110.00, 25, '2024-12-20', 3),             
('Malla elastikoak', 'Mallas', 45.00, 60, '2024-12-20', 2),        
('Txandal osoa', 'Chandal', 60.00, 45, '2024-12-20', 2),
('Udako sandaliak', 'Sandalias', 45.00, 30, '2024-12-21', 3),            
('Txanoarekin sudadera', 'Sudadera', 35.00, 80, '2024-12-21', 2),          
('Kotoizko jertsea', 'Jersey Cuello Pico', 40.00, 45, '2024-12-22', 4),    
('Haize-babesa arina', 'Cortavientos', 65.00, 20, '2024-12-22', 1),        
('Kirol praka motzak', 'Short Deportivo', 25.00, 100, '2024-12-23', 2),    
('Janzteko zapatak', 'Zapatos', 85.00, 15, '2024-12-24', 3),               
('Txaketa iragazgaitza', 'Chubasquero', 55.00, 60, '2024-12-25', 1);

INSERT INTO eskaera (Id_erabiltzailea, guztira, data, egoera) VALUES
(1, 360.00, '2025-01-10', 'recibido'),   
(2, 120.00, '2025-01-26', 'enviado'),    
(2, 150.00, '2025-01-23', 'recibido'),   
(3, 55.00,  '2025-01-27', 'en reparto'),
(1, 150.00, '2025-01-01', 'recibido'),
(2, 260.00, '2025-01-10', 'enviado'),   
(4, 360.00, '2025-01-08', 'recibido'),   
(1, 330.00, '2025-01-08', 'recibido'),   
(3, 150.00, '2025-01-22', 'enviado'),   
(1, 180.00, '2025-01-20', 'en reparto'); 

INSERT INTO produktu_eskaera (Id_produktua, Id_eskaera, Kopurua, Subtotala) VALUES
(1, 1, 3, 360.00),   
(1, 2, 1, 120.00),   
(2, 3, 1, 150.00),  
(3, 4, 1, 55.00),  
(4, 5, 2, 150.00),   
(5, 6, 2, 260.00),  
(1, 7, 3, 360.00),  
(6, 8, 3, 330.00),
(4, 9, 2, 150.00), 
(8, 10, 3, 180.00);