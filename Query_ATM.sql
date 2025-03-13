CREATE DATABASE bankmanagementsystem;

USE bankmanagementsystem;

CREATE TABLE signup(
Formno VARCHAR(10),
Name VARCHAR(50),
Father_name VARCHAR(50),
DOB date,
Gender VARCHAR(15),
Email VARCHAR(25),
Marital VARCHAR(25),
Address VARCHAR(100),
City VARCHAR(25),
Pincode VARCHAR(25),
State VARCHAR(50));

SELECT* FROM signup;

CREATE TABLE signupTwo (
Formno VARCHAR(20),
Religion VARCHAR(20),
Category VARCHAR(20),
Income VARCHAR(20),
Qualification VARCHAR(25),
Occupation VARCHAR(25),
PAN_id VARCHAR(20),
Aadhaar_id VARCHAR(20),
Seniorcitizen VARCHAR(20),
Existingaccount VARCHAR(20));

SELECT* FROM signupTwo;

CREATE TABLE signupThree(
Formno VARCHAR(20),
Account_Type VARCHAR(40),
Card_Number VARCHAR(25),
Pin_Number VARCHAR(10),
Facility  VARCHAR(100)
);

SELECT* FROM signupThree;

CREATE TABLE login(
Formno VARCHAR(20),
Card_Number VARCHAR(25),
Pin_Number VARCHAR(10)
);

SELECT* FROM login;

CREATE TABLE bank (
Pin_Number VARCHAR(25),
Date VARCHAR(50),
Type VARCHAR(25),
Amount VARCHAR(25)
);
SELECT* FROM bank;


