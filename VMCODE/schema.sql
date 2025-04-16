-- Ensure the database exists
CREATE DATABASE IF NOT EXISTS Events;
USE Events;

-- Create the Event table
CREATE TABLE IF NOT EXISTS Event (
    id INT AUTO_INCREMENT PRIMARY KEY,
    event_id INT NOT NULL,
    event_name VARCHAR(255) NOT NULL,
    event_description TEXT,
    morale INT,
    resources INT
);

-- Insert Data into Event table
INSERT INTO Event (event_id, event_name, event_description, morale, resources)
VALUES 
(1, 'Ion Array Failure', '*You hear a loud scrape and the sound of sparks.*Shit...* You hear the high voltage from a transformer. *The Ion Array must have failed.', 3, 5),
(2, 'Solar Panel Malfunction', 'You notice a strange flickering on the solar panels, and power seems unstable. The crew is getting worried about energy loss.', 2, 4),
(3, 'Pirates Attack Your Ship', 'Florian Pirates begin firing on your port side. They are known for taking no prisoners', 4, 5),
(4, 'Your Crew Mutinys', 'Steven, your first officer, decides that your booze allotments for your crew is not enough. You think Steven is being rediculous the crew already drinks their weight in beer', 1, 1),
(5, 'Your Tail Gunner Falls Asleep', 'Your tail gunner--dan--falls asleep at the controls and sends a barrage of lazer blasts into your engine   damint!   you say, this is going to be a long day', 1, 2),
(6, 'Your Toilet Breaks', 'Toilet Broke -> Your excrement had no where to go, its piling up and gained intellegence - This is bad', 10, 10),
(7, 'Shower Stops working', 'Your shower broke -> Your crewmates stink - you save water but man is it painful', 15, 1),
(8, 'Rust buildup', 'Rust has started to damage the ship and repairs have to be done quickly', 7, 15);