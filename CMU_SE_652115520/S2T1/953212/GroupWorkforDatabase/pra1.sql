-- Create the database if it doesn't exist
CREATE DATABASE IF NOT EXISTS college;

-- Use the database
	Use college;
    
-- Create the Students table
CREATE TABLE IF NOT EXISTS Students (
   student_id INT AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(100) NOT NULL,
    birthdate DATE,
    );
    
-- Create the Courses table
CREATE TABLE IF NOT EXISTS Courses(
	course_id  INT AUTO_INCREMENT PRIMARY KEY,
    course_name VARCHAR(100) NOT NULL,
    credits INT NOT NULL,
)

-- Create the Enrollments table
