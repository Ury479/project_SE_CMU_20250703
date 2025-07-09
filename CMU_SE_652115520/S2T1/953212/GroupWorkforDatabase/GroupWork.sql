DROP DATABASE IF EXISTS GroupWork;
CREATE DATABASE GroupWork;
USE GroupWork;

DROP TABLE IF EXISTS User;
DROP TABLE IF EXISTS ChargingStation;
DROP TABLE IF EXISTS PowerBank;
DROP TABLE IF EXISTS Transaction;

CREATE TABLE User (
    UserID INT PRIMARY KEY,
    Name VARCHAR(100),
    Email VARCHAR(100),
    PaymentInfo VARCHAR(255),
    RegistrationDate DATE
);

CREATE TABLE ChargingStation (
    StationID INT PRIMARY KEY,
    Location VARCHAR(255),
    Status ENUM('Available', 'In Use', 'Under Maintenance'),
    NumberOfPowerBanks INT
);

CREATE TABLE PowerBank (
    PowerBankID INT PRIMARY KEY,
    BatteryStatus VARCHAR(50),
    LastChargingDate DATE,
    UsageCount INT,
    StationID INT,
    FOREIGN KEY (StationID) REFERENCES ChargingStation(StationID)
);

CREATE TABLE Transaction (
    TransactionID INT PRIMARY KEY,
    UserID INT,
    StationID INT,
    PaymentMethod VARCHAR(50),
    Amount DECIMAL(10, 2),
    TransactionDate DATE,
    FOREIGN KEY (UserID) REFERENCES User(UserID),
    FOREIGN KEY (StationID) REFERENCES ChargingStation(StationID)
);