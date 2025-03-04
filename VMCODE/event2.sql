-- insert event
INSERT INTO Event (event_id, event_name, event_description, morale, resources, responses)
VALUES (
    2, -- event number
    'Solar Panel Malfunction',
    'You notice a strange flickering on the solar panels, and power seems unstable. The crew is getting worried about energy loss.',
    2, -- morale value
    4, -- resourse value
    '[{"number": -1, "text": "repair"}, {"number": 0, "text": "inspect"}, {"number": 4, "text": "ignore"}]'
);

-- clone this and upload for future events, this can be done bulk or one by one
