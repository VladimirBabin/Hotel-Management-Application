CREATE TABLE reservation_utilities
(
    reservation_id INT NOT NULL,
    utility_id     INT NOT NULL,
    PRIMARY KEY (reservation_id, utility_id),
    CONSTRAINT fk_reservations FOREIGN KEY (reservation_id) REFERENCES reservations (id) ON DELETE CASCADE,
    CONSTRAINT fk_utilities FOREIGN KEY (utility_id) REFERENCES utilities (id) ON DELETE CASCADE
);
