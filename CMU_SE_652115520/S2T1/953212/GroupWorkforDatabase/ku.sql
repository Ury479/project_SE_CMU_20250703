
-- 1. 检查数据库是否已经存在，若不存在则创建数据库
CREATE DATABASE IF NOT EXISTS powerbank;

-- 2. 使用数据库
USE powerbank;
-- user
CREATE TABLE IF NOT EXISTS User (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(255) NOT NULL UNIQUE,
    phone_number VARCHAR(20) UNIQUE,
    payment_information VARCHAR(255),
    feedback VARCHAR(500)
);
-- . Station table
CREATE TABLE IF NOT EXISTS Station (
    station_id INT PRIMARY KEY AUTO_INCREMENT,
    location VARCHAR(255) NOT NULL,
    status VARCHAR(50) NOT NULL,
    num_of_power_bank INT NOT NULL,
    remain_of_power_bank INT NOT NULL
);
-- . PowerBank table
CREATE TABLE IF NOT EXISTS PowerBank (
    powerbank_id INT PRIMARY KEY AUTO_INCREMENT,
    serial_number VARCHAR(50) UNIQUE NOT NULL,
    battery_status VARCHAR(50),
    num_of_uses INT,
    last_charge_date DATE,
    return_status ENUM('Returned', 'Not Returned') NOT NULL,
    station_id INT,
    FOREIGN KEY (station_id) REFERENCES Station(station_id) ON DELETE CASCADE
);
-- Updated Transaction table to reference serial_number instead of user_id
CREATE TABLE IF NOT EXISTS Transaction (
    transaction_id INT PRIMARY KEY AUTO_INCREMENT,
    serial_number VARCHAR(50) NOT NULL,
    station_id_rented_from INT,
    station_id_returned_to INT,
    payment_methods VARCHAR(255),
    transaction_amount DECIMAL(10, 2),
    rental_time DATETIME,
    return_time DATETIME,
    FOREIGN KEY (serial_number) REFERENCES PowerBank(serial_number) ON DELETE CASCADE,
    FOREIGN KEY (station_id_rented_from) REFERENCES Station(station_id) ON DELETE CASCADE,
    FOREIGN KEY (station_id_returned_to) REFERENCES Station(station_id) ON DELETE CASCADE
);
-- Feedback table remains unchanged
CREATE TABLE IF NOT EXISTS Feedback (
    feedback_id INT PRIMARY KEY AUTO_INCREMENT,
    contact_of_feedback VARCHAR(255),
    user_id INT,
    station_id INT,
    FOREIGN KEY (user_id) REFERENCES User(user_id) ON DELETE CASCADE,
    FOREIGN KEY (station_id) REFERENCES Station(station_id) ON DELETE CASCADE
);
