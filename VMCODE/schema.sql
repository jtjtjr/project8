-- create the Event table
CREATE TABLE IF NOT EXISTS Event (
    id INT AUTO_INCREMENT PRIMARY KEY,
    event_id INT NOT NULL,
    event_name VARCHAR(255) NOT NULL,
    event_description TEXT,
    morale INT,
    resources INT,
    responses JSON  -- JSON for responses (removed extra comma)
);

-- column to index through JSON (alternative approach)
ALTER TABLE Event ADD COLUMN response_texts TEXT 
    GENERATED ALWAYS AS (JSON_UNQUOTE(JSON_EXTRACT(responses, '$[0].text'))) STORED;

-- index for faster searches (specify key length)
CREATE INDEX idx_response_texts ON Event(response_texts(255));

-- ENTER DATA BELOW
INSERT INTO Event (event_id, event_name, event_description, morale, resources, responses)
VALUES (
    1,
    'Ion Array Failure',
    '*You hear a loud scrape and the sound of sparks.*\nShit...\n*You hear the high voltage from a transformer.*\nThe Ion Array must have failed.\nWhat should you do to fix it?',
    3,
    5,
    '[{"number": 1, "text": "power"}, {"number": 3, "text": "restart"}, {"number": 0, "text": "discharge"}, {"number": 10, "text": "wrench"}, {"number": -10, "text": "will"}]'
);
