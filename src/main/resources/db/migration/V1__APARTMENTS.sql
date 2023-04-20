CREATE TABLE apartments
(
    apartment_id     INT PRIMARY KEY,
    price            NUMERIC(19, 2) NOT NULL,
    apartment_type   VARCHAR(50)    NOT NULL,
    apartment_status VARCHAR(50)    NOT NULL,
    CONSTRAINT apartment_type_check CHECK (apartment_type IN ('SINGLE', 'DOUBLE')),
    CONSTRAINT apartment_status_check CHECK (apartment_status IN ('AVAILABLE', 'OCCUPIED', 'UNAVAILABLE'))
);
