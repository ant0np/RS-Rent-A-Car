CREATE TABLE cars
(
    id BIGINT(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    model VARCHAR(255),
    year VARCHAR(255)
);
CREATE UNIQUE INDEX cars_id_uindex ON cars (id);

CREATE TABLE clients
(
    id BIGINT(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name VARCHAR(255),
    year VARCHAR(255)
);
CREATE UNIQUE INDEX clients_id_uindex ON clients (id);