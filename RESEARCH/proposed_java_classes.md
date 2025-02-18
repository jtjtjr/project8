All of this is subject to change

# EVENT

## Attributes
- **Event Type**: The type of event.
- **Description**: A description of the event to be read as a print statement.
- **Impact Value**: A value that affects player resources. Optional not sure how to implement.

## Constructor
- **Event**: Initializes the event with its type, description, and impact.

## Getters 
- **getEventType()**: Retrieves the event type.
- **getDescription()**: Retrieves the event description.
- **getImpact()**: Retrieves the impact value.

# DAY

## Attributes
- **Day Number**: The number representing the day.
- **List of Possible Events**: A list of all possible events that can occur on this day. (May need to work with a database)
- **Survive**: A boolean function that tracks if the player died on a specific day/event.

## Constructor
- **Day**: Initializes the day with the day number, survival boolean function, list of all possible events, and a number seed value.

## Getters
- **getDayNumber()**: Retrieves the day number.
- **getSurvivalBoolean()**: Retrieves the survival boolean function.
- **getPossibleEvents()**: Retrieves the list of all possible events.
- **getSeedValue()**: Retrieves the number seed value.

## Functions
- **Random Function**: Calculates a random number for a type of event.
- **Next Day Function**: Moves the time forward if the survival boolean is true (default is true).

# Considerations

1. **Event Preloading**:
   - Should the events be preloaded in a database?
   - Use the Event class only when moving from the database to an Event object.

2. **Survival Tracking**:
   - Should the day object track the survival of that day or not?
   - How do we store the last checkpoint of the player?








