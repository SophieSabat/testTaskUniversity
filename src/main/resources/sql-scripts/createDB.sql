DROP DATABASE IF EXISTS universityDB;

CREATE DATABASE universityDB;

USE universityDB;




-- ----------------------------------------------------------------------------------------------------------------
-- DEGREE
-- ----------------------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS degree
(
    id   INTEGER     NOT NULL AUTO_INCREMENT,
    name VARCHAR(10) NOT NULL,

    PRIMARY KEY (id),
    UNIQUE (name)
) DEFAULT CHARSET utf8;

-- ----------------------------------------------------------------------------------------------------------------
-- LECTORS
-- ----------------------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS lectors
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
CREATE TABLE IF NOT EXISTS departments
(
    id                    INTEGER      NOT NULL AUTO_INCREMENT,
    department_name       VARCHAR(100) NOT NULL,
    head_of_department_id INTEGER      NOT NULL,

    PRIMARY KEY (id),
    UNIQUE (department_name),

    FOREIGN KEY (head_of_department_id)
        REFERENCES lectors (id)
) DEFAULT CHARSET utf8;

-- --------------------------------------------------------------
-- LECTORS & DEPARTMENTS
-- relation between lectors and departments
-- the lectors could work in more than one department
-- --------------------------------------------------------------
CREATE TABLE IF NOT EXISTS lectors_departments
(
    lectors_id    INTEGER NOT NULL,
    department_id INTEGER NOT NULL,

    INDEX (lectors_id),
    FOREIGN KEY (lectors_id)
        REFERENCES lectors (id),

    INDEX (department_id),
    FOREIGN KEY (department_id)
        REFERENCES departments (id)
) DEFAULT CHARSET utf8;



