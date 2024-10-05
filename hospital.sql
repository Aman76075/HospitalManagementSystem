CREATE DATABASE hospital;
USE hospital;
CREATE TABLE Patient (
    patientId INT PRIMARY KEY,
    firstName VARCHAR(50),
    lastName VARCHAR(50),
    dateOfBirth DATE,
    gender VARCHAR(10),
    contactNumber VARCHAR(15),
    address VARCHAR(255)
);

CREATE TABLE Doctor (
    doctorId INT PRIMARY KEY,
    firstName VARCHAR(50),
    lastName VARCHAR(50),
    specialization VARCHAR(100),
    contactNumber VARCHAR(15)
);

CREATE TABLE Appointment (
    appointmentId INT PRIMARY KEY,
    patientId INT,
    doctorId INT,
    appointmentDate DATE,
    description VARCHAR(255),
    FOREIGN KEY (patientId) REFERENCES Patient(patientId),
    FOREIGN KEY (doctorId) REFERENCES Doctor(doctorId)
);

INSERT INTO Patient (patientId, firstName, lastName, dateOfBirth, gender, contactNumber, address) 
VALUES 
(1, 'Aman', 'Pandey', '1995-01-12', 'Male', '9876543210', '23 MG Road, Lucknow, UP'),
(2, 'Neha', 'Sharma', '1990-07-19', 'Female', '8765432109', '12 Civil Lines, Jaipur, Rajasthan'),
(3, 'Rahul', 'Verma', '1985-03-25', 'Male', '7654321098', '8 Lal Kothi, Bhopal, MP'),
(4, 'Pooja', 'Singh', '1992-05-15', 'Female', '6543210987', '55 Park Street, Kolkata, WB'),
(5, 'Vikas', 'Mishra', '2000-08-20', 'Male', '5432109876', '101 Ashok Nagar, Kanpur, UP');


INSERT INTO Doctor (doctorId, firstName, lastName, specialization, contactNumber) 
VALUES 
(1, 'Dr. Anjali', 'Deshmukh', 'Cardiologist', '9123456780'),
(2, 'Dr. Ravi', 'Patel', 'Dermatologist', '9012345678'),
(3, 'Dr. Priya', 'Nair', 'Pediatrician', '8901234567'),
(4, 'Dr. Suresh', 'Kumar', 'Orthopedic Surgeon', '7890123456'),
(5, 'Dr. Manish', 'Gupta', 'Neurologist', '6789012345');

INSERT INTO Appointment (appointmentId, patientId, doctorId, appointmentDate, description) 
VALUES 
(1, 1, 1, '2024-10-15', 'Routine heart checkup for high BP'),
(2, 2, 2, '2024-10-16', 'Skin rash due to allergy'),
(3, 3, 3, '2024-10-17', 'Child vaccination'),
(4, 4, 4, '2024-10-18', 'Consultation for back pain treatment'),
(5, 5, 5, '2024-10-19', 'Treatment for migraine headaches');

