-- Create the database if it doesn't exist
CREATE DATABASE IF NOT EXISTS lab7;

-- Use the database
USE lab7;

-- Create the Students table
CREATE TABLE IF NOT EXISTS Students (
    student_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    birthdate DATE
    -- Remove CHECK constraint if not supported
);

-- Create the Courses table
CREATE TABLE IF NOT EXISTS Courses (
    course_id INT AUTO_INCREMENT PRIMARY KEY,
    course_name VARCHAR(100) NOT NULL,
    credits INT NOT NULL
    -- Remove CHECK constraint if not supported
);

-- Create the Enrollments table
CREATE TABLE IF NOT EXISTS Enrollments (
    enrollment_id INT AUTO_INCREMENT PRIMARY KEY,
    student_id INT,
    course_id INT,
    enrollment_date DATE DEFAULT CURDATE(),
    FOREIGN KEY (student_id) REFERENCES Students(student_id),
    FOREIGN KEY (course_id) REFERENCES Courses(course_id)
);