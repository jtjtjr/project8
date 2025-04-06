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


-- Create the Event table
CREATE TABLE IF NOT EXISTS PlanetLore (
    planet_name TEXT NOT NULL, 
    planet_lore TEXT NOT NULL
);

INSERT INTO PlanetLore (planet_name, planet_lore) VALUES 
('Bucephalus', 'A yellow gas giant, Bucephalus hosts the Greasy Bolt Diner, a pirate-run platform. Jessa "One-Eye" Korran serves Bolt Burgers—synth-meat fried in oil—and guards salvaged purifiers. Storms jolt the sails, and deals sour without a blaster.'),
('Norman''s Rock', 'An airless gray rock, this Company mine was a failing startup until the buyout, but salaries fell, hours grew, and accidents worsened. Miners tried to unionize years ago, but with no off-world aid and food exports cut, it collapsed fast. A lost iridium vein taunts them, yet the Company won’t dig. The Rusty Pick saloon gouges traders, and exiled guards rule the desperate.'),
('Atlas Station', 'A void-bound lab, Atlas Station hunts graviton anomalies to crack FTL travel. Dr. Elara Voss’s team stares at Norman’s Rock, decoding spacetime’s warp. Asteroids and breaches test them, but the data rewrites physics.'),
('Ezekiel''s Salvation', 'An ocean refuge, it was colonized by religious fishermen fleeing the Company’s grasp, seeking freedom. In a fierce storm decades ago, they saw a whale circling—when it cleared and they survived, they named it Balenus and worshipped it. Algae farming later eased their fish-heavy diet, curing scurvy and ending fruit imports. From above, some say Balenus still swims, steadying the waves with strength.'),
('Mu 6', 'A moon by a blue giant, Mu 6’s Skimmer Clan peddles cheap fuel from drone scraps. Rings hide wrecks, but they don’t care—a quiet blip.'),
('Astros Confederation', 'A rebel union born in the Stellar Schism, they broke the Company’s yoke like old Earth’s Confederates. Taxed and mined to death, they rose in blood, guarding freedom with fury.'),
('Technon 9', 'An industrial forge, Technon 9 fled the Company in the Iron Pact Rebellion. Once their shipyard, workers seized it from wage cuts and smog, joining the Confederation for liberty—pride’s high.'),
('Astros Militarum', 'A station, it mutinied in the Night of the Long Watch against Company overlords. Stimulant-fueled soldiers joined the Confederation as a shield, their air a gift, resolve absolute.'),
('Orion''s Bane', 'A jungle world, its plants hum with alien electromagnetism, a buried secret. Foragers fall to fever and beasts in this untamed sprawl.'),
('Unknown J76G423-b', 'An enigma, its crystal dunes bend starlight into trances. Unclaimed, it’s a riddle of curse or riches.'),
('Cerberus XVII', 'The Company’s HQ, its spires hide a DNA vault of the exploited. Cargo sells, quotas judge under power’s weight.');
