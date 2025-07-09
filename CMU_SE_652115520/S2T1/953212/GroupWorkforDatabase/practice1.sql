DROP TABLE IF EXISTS tb_user;
-- 选择使用 itcast 数据库
USE itcast;

CREATE TABLE tb_user (
    id INT COMMENT '编号',
    name VARCHAR(50) COMMENT '姓名',
    age INT COMMENT '年龄',
    gender VARCHAR(1) COMMENT '性别'
) COMMENT='用户表';

SET NAMES utf8mb4;
SHOW TABLES;
DESC tb_user;
SHOW CREATE TABLE tb_user;

DROP TABLE IF EXISTS emp;

CREATE TABLE emp (
    id INT COMMENT '编号',
    workno VARCHAR(10) COMMENT '工号',
    name VARCHAR(10) COMMENT '姓名',
    gender VARCHAR(10) COMMENT '性别',
    age TINYINT UNSIGNED COMMENT '年龄',
    idcard CHAR(18) COMMENT '身份证号',
    entrydate DATE COMMENT '入职时间'
) COMMENT='员工表';

-- 先添加 nickname 字段
ALTER TABLE emp ADD COLUMN nickname VARCHAR(30);

-- 然后修改 nickname 字段
ALTER TABLE emp CHANGE nickname username VARCHAR(30) COMMENT '用户名';
ALTER TABLE emp DROP username;

DROP TABLE IF EXISTS employee;
SHOW TABLES;
DESC emp;
ALTER TABLE emp RENAME TO employee;


