-- 检查数据库是否已经存在，若不存在则创建数据库
CREATE DATABASE IF NOT EXISTS PowerBank;

-- 使用数据库
USE PowerBank;

-- 创建表
CREATE TABLE IF NOT EXISTS User (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,  -- 确保 password 字段不能为空
    phone_number VARCHAR(15),
    feedback VARCHAR(500),
    payment_information VARCHAR(255)
);

-- 插入数据
INSERT INTO User (username, password, phone_number, feedback, payment_information) 
VALUES ('JohnDoe', 'password123', '123-456-7890', 'Great service', 'Credit Card');

-- 确认 User 表的结构
DESCRIBE User;

-- 创建 Station 表
CREATE TABLE IF NOT EXISTS Station (
    station_id INT PRIMARY KEY AUTO_INCREMENT,
    location VARCHAR(255) NOT NULL,
    status ENUM('available', 'in_use', 'maintenance') NOT NULL,  -- 保留 ENUM 类型的状态字段
    num_of_power_bank INT NOT NULL,
    remain_of_power_bank INT NOT NULL
);

SELECT station_id, location, status, num_of_power_bank, remain_of_power_bank FROM Station;

-- 创建 PowerBank 表
CREATE TABLE IF NOT EXISTS PowerBank (
    powerbank_id INT PRIMARY KEY AUTO_INCREMENT,
    battery_status VARCHAR(50),
    num_of_uses INT,
    last_charge_date DATE,
    return_status ENUM('Returned', 'Not Returned') NOT NULL,
    station_id INT,
    FOREIGN KEY (station_id) REFERENCES Station(station_id) ON DELETE CASCADE
);

-- 创建 Transaction 表
CREATE TABLE IF NOT EXISTS Transaction (
    transaction_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT,
    powerbank_id INT,
    station_id INT,
    payment_methods VARCHAR(255),
    transaction_amount DECIMAL(10, 2),
    trading_hours TIME,
    FOREIGN KEY (user_id) REFERENCES User(user_id) ON DELETE CASCADE,
    FOREIGN KEY (powerbank_id) REFERENCES PowerBank(powerbank_id) ON DELETE CASCADE,
    FOREIGN KEY (station_id) REFERENCES Station(station_id) ON DELETE CASCADE
);

-- 创建 Feedback 表
CREATE TABLE IF NOT EXISTS Feedback (
    feedback_id INT PRIMARY KEY AUTO_INCREMENT,
    contact_of_feedback VARCHAR(255),
    user_id INT,
    station_id INT,
    FOREIGN KEY (user_id) REFERENCES User(user_id) ON DELETE CASCADE,
    FOREIGN KEY (station_id) REFERENCES Station(station_id) ON DELETE CASCADE
);

-- 创建 inventory 表
CREATE TABLE IF NOT EXISTS inventory (
    id INT PRIMARY KEY AUTO_INCREMENT,
    item_name VARCHAR(255) NOT NULL,
    quantity INT NOT NULL
);

-- 创建 payments 表
CREATE TABLE IF NOT EXISTS payments (
    id INT PRIMARY KEY AUTO_INCREMENT,  -- 支付ID，自动递增
    amount DECIMAL(10, 2) NOT NULL,     -- 支付金额
    status VARCHAR(50) NOT NULL,        -- 支付状态，例如 'Processed', 'Failed' 等
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP  -- 创建时间
);

-- 设置触发器结束符
DELIMITER $$

-- 删除已有的触发器，避免重复
DROP TRIGGER IF EXISTS before_station_insert;
DROP TRIGGER IF EXISTS before_station_update;

-- 定义插入操作的触发器
CREATE TRIGGER before_station_insert
BEFORE INSERT ON Station
FOR EACH ROW
BEGIN
    IF NEW.remain_of_power_bank > NEW.num_of_power_bank THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'remain_of_power_bank cannot be greater than num_of_power_bank';
    END IF;
END $$

DELIMITER ;

-- 创建数据库用户并赋予权限
CREATE USER IF NOT EXISTS 'powerbank'@'localhost' IDENTIFIED BY 'cmu123wyh';
GRANT ALL PRIVILEGES ON PowerBank.* TO 'powerbank'@'localhost';
FLUSH PRIVILEGES;

-- 显示数据库中的索引和表结构
SHOW INDEX FROM Station;
SHOW TABLES;

-- 赋予用户权限，如果用户已经存在，不必重复创建
GRANT ALL PRIVILEGES ON PowerBank.* TO 'powerbank'@'localhost';
FLUSH PRIVILEGES;

-- 插入示例数据到 User 表，提供 password 字段
INSERT INTO User (username, phone_number, feedback, payment_information, password) 
VALUES ('JohnDoe', '123-456-7890', 'Great service', 'Credit Card', 'your_password');

-- 插入示例数据到 Station 表
INSERT INTO Station (location, status, num_of_power_bank, remain_of_power_bank) 
VALUES ('Downtown', 'available', 10, 5);

-- 确保 payments 表中存在 transaction_id 列
IF NOT EXISTS (
    SELECT * 
    FROM INFORMATION_SCHEMA.COLUMNS 
    WHERE TABLE_NAME = 'payments' 
    AND COLUMN_NAME = 'transaction_id'
) THEN
    ALTER TABLE payments ADD COLUMN transaction_id VARCHAR(36) UNIQUE;
END IF;

-- 确保 PowerBank 表中存在 station_id 列
IF NOT EXISTS (
    SELECT * 
    FROM INFORMATION_SCHEMA.COLUMNS 
    WHERE TABLE_NAME = 'PowerBank' 
    AND COLUMN_NAME = 'station_id'
) THEN
    ALTER TABLE PowerBank ADD COLUMN station_id INT;

-- 删除已有触发器并创建自动维护触发器
DELIMITER $$

DROP TRIGGER IF EXISTS auto_maintenance;

CREATE TRIGGER auto_maintenance
AFTER UPDATE ON PowerBank
FOR EACH ROW
BEGIN
    -- 假设 PowerBank 表具有 station_id 字段，用于链接到 Station 表
    IF NEW.num_of_uses >= 100 THEN
        UPDATE Station 
        SET status = 'maintenance' 
        WHERE station_id = NEW.station_id; -- 确保 PowerBank 表有 station_id 字段
    END IF;
END $$

DELIMITER ;
