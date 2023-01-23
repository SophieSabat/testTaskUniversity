DROP DATABASE IF EXISTS universityDB;

CREATE DATABASE universityDB;

USE universityDB;

-- ----------------------------------------------------------------------------------------------------------------
-- DEGREE
-- ----------------------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS degree
(
    id   INTEGER     NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,

    PRIMARY KEY (id),
    UNIQUE (name)
) DEFAULT CHARSET utf8;

-- ----------------------------------------------------------------------------------------------------------------
-- LECTORS
-- ----------------------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS lector
(
    id        INTEGER     NOT NULL AUTO_INCREMENT,
    full_name VARCHAR(50) NOT NULL,
    salary    INTEGER     NOT NULL,
    degree_id INTEGER     NOT NULL,

    PRIMARY KEY (id),
    UNIQUE (full_name),

    FOREIGN KEY (degree_id)
        REFERENCES degree (id)
        ON DELETE CASCADE
        ON UPDATE RESTRICT
) DEFAULT CHARSET utf8;

-- ----------------------------------------------------------------------------------------------------------------
-- DEPARTMENTS
-- ----------------------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS department
(
    id                 INTEGER      NOT NULL AUTO_INCREMENT,
    name               VARCHAR(100) NOT NULL,
    head_of_department INTEGER      NOT NULL,

    PRIMARY KEY (id),
    UNIQUE (name),

    FOREIGN KEY (head_of_department)
        REFERENCES lector (id)
) DEFAULT CHARSET utf8;

-- --------------------------------------------------------------
-- LECTORS & DEPARTMENTS
-- relation between lector and department
-- the lector could work in more than one department
-- --------------------------------------------------------------
CREATE TABLE IF NOT EXISTS lectors_departments
(
    lector_id     INTEGER NOT NULL,
    department_id INTEGER NOT NULL,

    INDEX (lector_id),
    FOREIGN KEY (lector_id)
        REFERENCES lector (id),

    INDEX (department_id),
    FOREIGN KEY (department_id)
        REFERENCES department (id)
) DEFAULT CHARSET utf8;



