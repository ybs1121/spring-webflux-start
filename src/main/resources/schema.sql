DROP TABLE IF EXISTS customer;
CREATE TABLE customer
(
    id        SERIAL  PRIMARY KEY,
    firstname VARCHAR(100) NOT NULL,
    lastname  VARCHAR(100) NOT NULL);