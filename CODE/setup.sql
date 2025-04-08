-- Create the Planet table
CREATE TABLE IF NOT EXISTS Planet (
    id INT AUTO_INCREMENT PRIMARY KEY,  
    name VARCHAR(255) NOT NULL,
    affiliation VARCHAR(255),
    health_per_day INT NOT NULL,
    crew_per_day INT NOT NULL
);

-- Create the Event table
CREATE TABLE IF NOT EXISTS Event (
    id INT AUTO_INCREMENT PRIMARY KEY,  
    planet_id INT NOT NULL, 
    event_name VARCHAR(255) NOT NULL,
    event_description TEXT,
    event_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (planet_id) REFERENCES Planet(id) ON DELETE CASCADE
);

-- Insert Planet Data
INSERT INTO Planet (name, affiliation, health_per_day, crew_per_day) VALUES 
    ('Orion’s Bane', 'None', -10, 0),
    ('Technon 9', 'Astros Confederation', -5, 0),
    ('Astros Militarum', 'Astros Confederation', 5, -1),
    ('Unknown J76G423-b', 'None', 0, 0),
    ('Cerberus XVII', 'Company', 999, 0);