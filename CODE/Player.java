import java.util.List;
import java.util.Random;

/**
 * The Day class represents a single day in Galactic Trail (name of game), 
 *containing the day number, survival boolean, and random event handling.
 */
public class Player {
    
    private Ship ship;
    private int crewNum;
    private int morale;
    private int resources;
    private int location;

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
    public Player(int dayNumber, List<Event> possibleEvents, int crewNum, int morale, int resources, String shipName) {
        this.dayNumber = dayNumber;

        //needs to be True, in future implementation will need events to alter this function so it will not be private 
        //but rather protecter or public
        this.survive = True; 
        this.possibleEvents = possibleEvents;

        //0 - possibleEvents.size() acting as a random value index, just an idea on how to use an event
        this.seedValue = new Random().nextInt(possibleEvents.size());
        
        this.random = new Random(this.seedValue);

        // The Ship portion
        this.crewNum = crewNum;
        this.morale = morale;
        this.resources = resources;
        this.ship = new Ship(shipName);
    }

    /**
     * Inner class representing the Ship.
     */
    public class Ship {
        private String name;

        /**
         * Constructor for Ship class.
         * @param name name of the ship
         */
        public Ship(String name) {
            this.name = name;
        }

        /**
         * Retrieves the ship's name.
         * @return ship name
         */
        public String getName() {
            return name;
        }

        /**
         * Sets the ship's name.
         * @param name new ship name
         */
        public void setName(String name) {
            this.name = name;
        }
    }

    /**
     * enum for what roles each Crew can be
     */
    public enum CrewRole {
        KNIGHT,
        MAGE,
        ARCHER;

        public static CrewRole roleID(int ID) {
            switch (ID) {
                case 1:
                    return KNIGHT;
                case 2:
                    return MAGE;
                case 3:
                    return ARCHER;
                default:
                    throw new IllegalArgumentException();
            }
        }
    }

    /**
     * Crew class with info about the crew, ie. stats and crewRole
     */
    public class Crew {
        protected final CrewRole role;
        protected int health;
        protected int strength;
        protected int intelligence;

        public Crew(int ID) {
             role = CrewRole.roleID(ID);

            switch (role) {
                case KNIGHT:
                    health = 100;
                    strength = 20;
                    intelligence = 10;
                    break;
                case MAGE:
                    health = 30;
                    strength = 50;
                    intelligence = 50;
                    break;
                case ARCHER:
                    health = 40;
                    strength = 40;
                    intelligence = 50;
                    break;
            }
        }

        public int getHealth() { return this.health; }

        public int getStrength() { return this.strength; }

        public int getIntelligence() { return this.intelligence; }

        public CrewRole getRole() { return this.role; }
        
        @Override
        public String toString() {
            return "Crew{" +
                    "role=" + role +
                    ", health=" + health +
                    ", strength=" + strength +
                    ", intelligence=" + intelligence +
                    '}';
        }
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

    /**
     * Retrieves the ship object.
     * @return the ship
     */
    public Ship getShip() {
        return ship;
    }

    /**
     * Retrieves the number of crew members.
     * @return number of crew members
     */
    public int getCrewNum() {
        return crewNum;
    }

    /**
     * Sets the number of crew members.
     * @param crewNum new number of crew members
     */
    public void setCrewNum(int crewNum) {
        this.crewNum = crewNum;
    }

    /**
     * Retrieves the current morale level.
     * @return morale value
     */
    public int getMorale() {
        return morale;
    }

    /**
     * Sets the morale level.
     * @param morale new morale value
     */
    public void setMorale(int morale) {
        this.morale = morale;
    }

    /**
     * Retrieves the amount of resources.
     * @return amount of resources
     */
    public int getResources() {
        return resources;
    }

    /**
     * Sets the amount of resources.
     * @param resources new resources value
     */
    public void setResources(int resources) {
        this.resources = resources;
    }

    /**
     * Updates morale by a given change amount.
     * @param change amount to change morale by
     */
    public void updateMorale(int change) {
        morale += change;
    }

    /**
     * Uses specified resources, if available.
     * @param amount amount of resources to use
     */
    public void useResources(int amount) {
        if (resources >= amount) {
            resources -= amount;
        } else {
            System.out.println("Not enough resources!");
        }
    }

    /**
     * Adds one crew member.
     */
    public void addCrewMember() {
        crewNum++;
    }

    /**
     * Removes one crew member if any are present.
     */
    public void removeCrewMember() {
        if (crewNum > 0) {
            crewNum--;
        }
    }

    @Override
    public String toString() {
        return "Player{" +
                "ship=" + ship +
                ", crewNum=" + crewNum +
                ", morale=" + morale +
                ", resources=" + resources +
                ", dayNumber=" + dayNumber +
                '}';
    }
}
