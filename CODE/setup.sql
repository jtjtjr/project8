-- Create the Planet table
CREATE TABLE IF NOT EXISTS Planet (
    id SERIAL PRIMARY KEY,  
    name VARCHAR(255) NOT NULL,
    affiliation VARCHAR(255),
    health_per_day INT NOT NULL,
    crew_per_day INT NOT NULL
);

-- Insert Planet Data
INSERT INTO Planet (name, affiliation, health_per_day, crew_per_day) VALUES 
    ('Orion’s Bane', 'None', -10, 0),
    ('Technon 9', 'Astros Confederation', -5, 0),
    ('Astros Militarum', 'Astros Confederation', 5, -1),
    ('Unknown J76G423-b', 'None', 0, 0),
    ('Cerberus XVII', 'Company', 999, 0)
ON CONFLICT DO NOTHING;
