USE Coursera;
CREATE TABLE product(
	product_id INT AUTO_INCREMENT PRIMARY KEY,
	category INT(10),
    price INT(10),l
    location VARCHAR(50) DEFAULT 'Bangkok');

ALTER TABLE product ADD category INT(10); 

-- I have a good idea!
SELECT FORM product;
DROP TABLE product;

