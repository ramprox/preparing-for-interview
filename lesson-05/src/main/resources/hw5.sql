DROP DATABASE IF EXISTS prepare_interview;

CREATE DATABASE prepare_interview;

use prepare_interview;

CREATE TABLE students (
    id bigint NOT NULL AUTO_INCREMENT,
    name varchar(48) NOT NULL,
    mark int NOT NULL,
    PRIMARY KEY(id)
);