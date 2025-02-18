import java.util.List;
import java.util.Random;

/**
 * The Day class represents a single day in Galactic Trail (name of game), 
 *containing the day number, survival boolean, and random event handling.
 */
public class Day {
    
    private int dayNumber;
    //private List<Event> possibleEvents; This needs the Event object
    private boolean survive;
    private int seedValue; //index for possible events
    private Random random; //for random events

    //Needs a Default Constructor for Day 1

    /**
     * Constructs a Day object with a specified day number and list of possible events
     *The survival status is initially set to true
     *A seed value is randomly selected based on the size of the possible events list
     * 
     * @param dayNumber The current day number.
     * @param possibleEvents The list of possible events for the day.
     */
    public Day(int dayNumber, List<Event> possibleEvents) {
        this.dayNumber = dayNumber;

        //needs to be True, in future implementation will need events to alter this function so it will not be private 
        //but rather protecter or public
        this.survive = True; 
        this.possibleEvents = possibleEvents;

        //0 - possibleEvents.size() acting as a random value index, just an idea on how to use an event
        this.seedValue = new Random().nextInt(possibleEvents.size());
        
        this.random = new Random(this.seedValue);
    }

    /**
     * Retrieves the current day number
     * @return The current day number.
     */
    public int getDayNumber() {
        return dayNumber;
    }

    /**
     * Retrieves the surivial boolean status.
     * @return The survival boolean
     */
    public boolean getSurvivalBoolean() {
        return survive;
    }

    // commented out because Even is not implemented
    // public List<Event> getPossibleEvents() {
    //     return possibleEvents;
    // }

    /**
     * Retrieves the seed value
     * @return the integer seedvalue
     */
    public int getSeedValue() {
        return seedValue;
    }

    /**
     * Changes the day plus one if survival is true
     * @return true if the day successfuly change forward
     */
    public boolean nextDay() {
        if (survive) 
        {
            dayNumber++; //move to the next day
            return true;
        }
        //additional functionality 
        return false; //did not survive
    }
}
