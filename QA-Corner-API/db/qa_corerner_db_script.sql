CREATE database if not exists qacorner;

CREATE TABLE if not exists qacorner.student_info(first_name varchar(100) NOT NULL, last_name varchar(100) NOT NULL,student_password varchar(25) NOT NULL, school_name varchar(200) NOT NULL,class_name varchar(10) NOT NULL,screen_name varchar(40) UNIQUE NOT NULL,dob date NOT NULL,gender ENUM('Male', 'Femail'),registration_date date NOT NULL ,city varchar(28) NOT NULL,state varchar(30) NOT NULL,qacorner_student_id varchar(20) NOT NULL PRIMARY KEY);

CREATE TABLE if not exists qacorner.student_login_info(qacorner_student_id varchar(20) NOT NULL PRIMARY KEY, last_login timestamp, client_information text);

CREATE TABLE if not exists qacorner.india_locations(state varchar(28), city varchar(30), state_id varchar(4));