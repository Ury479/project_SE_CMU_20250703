-- 删除旧的数据库（如果存在），然后创建新的数据库
DROP DATABASE IF EXISTS lab9;
CREATE DATABASE lab9;
USE lab9;

-- 创建 employees 表
CREATE TABLE employees (
    emp_no INT PRIMARY KEY,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    birth_date DATE,
    gender CHAR(1),
    hire_date DATE
);

-- 插入示例数据
INSERT INTO employees (emp_no, first_name, last_name, birth_date, gender, hire_date) 
VALUES (10014, 'Berni', 'Doe', '1980-01-01', 'M', '2020-01-01');

-- 查询姓和名
SELECT last_name, first_name FROM employees;
SELECT last_name, first_name FROM employees ORDER BY first_name;

-- 创建 dept_emp 表
CREATE TABLE dept_emp (
    emp_no INT,
    dept_no VARCHAR(10),
    PRIMARY KEY (emp_no, dept_no)
);

-- 插入一些示例数据到 dept_emp 表中
INSERT INTO dept_emp (emp_no, dept_no) 
VALUES (10014, 'd003');

-- 查询在部门 d003 的员工人数
SELECT COUNT(*) FROM dept_emp WHERE dept_no = 'd003';

-- 创建 titles 表
CREATE TABLE titles (
    emp_no INT,
    title VARCHAR(50),
    from_date DATE,
    to_date DATE,
    PRIMARY KEY (emp_no, title)
);

-- 插入一些示例数据到 titles 表中
INSERT INTO titles (emp_no, title, from_date, to_date) 
VALUES (10014, 'Engineer', '2010-01-01', '2020-01-01');

-- 查询职位为 Engineer 的员工名，并按 emp_no 排序
SELECT e.first_name 
FROM employees e 
JOIN titles t ON e.emp_no = t.emp_no 
WHERE t.title = 'Engineer' 
ORDER BY e.emp_no;

-- 查询公司内的女性员工人数
SELECT COUNT(*) FROM employees WHERE gender = 'F';

-- 查询薪水最高的前 10 名员工的 emp_no
CREATE TABLE salaries (
    emp_no INT,
    salary INT
);

INSERT INTO salaries (emp_no, salary)
VALUES (10014, 50000);

SELECT emp_no FROM salaries ORDER BY salary DESC LIMIT 10;

-- 按职位统计不同的职位种类和员工数量
SELECT title, COUNT(*) FROM titles GROUP BY title;

-- 按性别统计公司内的男性和女性人数
SELECT gender, COUNT(*) FROM employees GROUP BY gender;

-- 创建与 employees 表相同的 supervisor 表
CREATE TABLE supervisor LIKE employees;

-- 插入 supervisor 表的示例数据
INSERT INTO supervisor (emp_no, birth_date, first_name, last_name, gender, hire_date) 
VALUES (999901, '1980-01-01', 'John', 'Doe', 'M', '2020-01-01'),
       (999902, '1985-05-05', 'Jane', 'Smith', 'F', '2021-05-05');

-- 更新员工信息，将编号为 10014 的员工 first_name 从 'Berni' 改为 'Kenny'
UPDATE employees 
SET first_name = 'Kenny' 
WHERE emp_no = 10014 AND first_name = 'Berni';
