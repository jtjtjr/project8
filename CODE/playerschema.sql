-- create the Event table
CREATE TABLE IF NOT EXISTS Player (
    id INT AUTO_INCREMENT PRIMARY KEY,
    player_username VARCHAR(255) NOT NULL,
    player_password VARCHAR(255) NOT NULL,
    player_state VARCHAR(255) NOT NULL
);