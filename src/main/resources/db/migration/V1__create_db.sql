-- Create table PLANET
CREATE TABLE planet (
                        id VARCHAR(10) PRIMARY KEY,
                        name VARCHAR(500) NOT NULL
);

-- Create table CLIENT
CREATE TABLE client (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        name VARCHAR(200) NOT NULL
);

-- Create table TICKET
CREATE TABLE ticket (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        created_at TIMESTAMP NOT NULL,
                        client_id BIGINT NOT NULL,
                        from_planet_id VARCHAR(10) NOT NULL,
                        to_planet_id VARCHAR(10) NOT NULL,
                        CONSTRAINT fk_client FOREIGN KEY (client_id) REFERENCES client(id),
                        CONSTRAINT fk_from_planet FOREIGN KEY (from_planet_id) REFERENCES planet(id),
                        CONSTRAINT fk_to_planet FOREIGN KEY (to_planet_id) REFERENCES planet(id)
);
