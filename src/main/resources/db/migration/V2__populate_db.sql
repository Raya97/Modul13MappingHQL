-- Insert planets
INSERT INTO planet (id, name) VALUES ('MARS', 'Mars');
INSERT INTO planet (id, name) VALUES ('VEN', 'Venus');
INSERT INTO planet (id, name) VALUES ('EARTH', 'Earth');
INSERT INTO planet (id, name) VALUES ('JUP', 'Jupiter');
INSERT INTO planet (id, name) VALUES ('SAT', 'Saturn');

-- Insert clients
INSERT INTO client (name) VALUES ('John Doe');
INSERT INTO client (name) VALUES ('Jane Doe');
INSERT INTO client (name) VALUES ('Alice Johnson');
INSERT INTO client (name) VALUES ('Bob Smith');
INSERT INTO client (name) VALUES ('Charlie Brown');
INSERT INTO client (name) VALUES ('David Wilson');
INSERT INTO client (name) VALUES ('Eva Green');
INSERT INTO client (name) VALUES ('Frank White');
INSERT INTO client (name) VALUES ('Grace Black');
INSERT INTO client (name) VALUES ('Henry Gold');

-- Insert tickets
INSERT INTO ticket (created_at, client_id, from_planet_id, to_planet_id) VALUES (NOW(), 1, 'MARS', 'VEN');
INSERT INTO ticket (created_at, client_id, from_planet_id, to_planet_id) VALUES (NOW(), 2, 'VEN', 'MARS');
INSERT INTO ticket (created_at, client_id, from_planet_id, to_planet_id) VALUES (NOW(), 3, 'EARTH', 'JUP');
INSERT INTO ticket (created_at, client_id, from_planet_id, to_planet_id) VALUES (NOW(), 4, 'JUP', 'SAT');
INSERT INTO ticket (created_at, client_id, from_planet_id, to_planet_id) VALUES (NOW(), 5, 'SAT', 'EARTH');
INSERT INTO ticket (created_at, client_id, from_planet_id, to_planet_id) VALUES (NOW(), 6, 'MARS', 'JUP');
INSERT INTO ticket (created_at, client_id, from_planet_id, to_planet_id) VALUES (NOW(), 7, 'VEN', 'SAT');
INSERT INTO ticket (created_at, client_id, from_planet_id, to_planet_id) VALUES (NOW(), 8, 'EARTH', 'VEN');
INSERT INTO ticket (created_at, client_id, from_planet_id, to_planet_id) VALUES (NOW(), 9, 'JUP', 'MARS');
INSERT INTO ticket (created_at, client_id, from_planet_id, to_planet_id) VALUES (NOW(), 10, 'SAT', 'MARS');