CREATE TABLE reservations
(
    id           INT PRIMARY KEY,
    client_id    INT  NOT NULL,
    apartment_id INT  NOT NULL,
    check_in     DATE NOT NULL,
    check_out    DATE NOT NULL,
    CONSTRAINT fk_client FOREIGN KEY (client_id) REFERENCES clients (personal_id),
    CONSTRAINT fk_apartment FOREIGN KEY (apartment_id) REFERENCES apartments (apartment_id)
);
