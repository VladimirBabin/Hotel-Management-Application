CREATE TABLE clients
(
    personal_id  INT PRIMARY KEY,
    first_name   VARCHAR(100) NOT NULL,
    last_name    VARCHAR(100) NOT NULL,
    phone_number VARCHAR(20)  NOT NULL
);
