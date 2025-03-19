import java.util.List;

/**
 * The Day class represents a single day in Galactic Trail (name of game), 
 *containing the day number, survival boolean, and random event handling.
 */
public class Player {
    
    private String shipName;
    private int crewNum;
    private int morale;
    private int resources;
    private Planet currentPlanet;
    private int dayNumber;
    private boolean survive;

    //private Event currentEvent; not too sure how we want to do this yet

    /**
     * Default Constructor that takes no input yet (testing??)
     */
    public Player() {
        this.dayNumber = 1;
        this.survive = true;

        this.morale = -1;
        this.crewNum = -1;
        this.resources = -1;
        this.shipName = "";
        this.currentPlanet = null;
    }

    /**                 //NEED TO DECIDE WHAT PARAM TO TAKE
     * Constructs a Day object with a specified day number and list of possible events
     *The survival status is initially set to true
     *A seed value is randomly selected based on the size of the possible events list
     * 
     * @param dayNumber The current day number.
     */
    public Player(int dayNumber, int crewNum, int morale, int resources, String shipName) {
        this.dayNumber = dayNumber;
        this.survive = true; 

        // The Ship portion
        this.crewNum = crewNum;
        this.morale = morale;
        this.resources = resources;
        this.shipName = shipName;
    }

    /**
     * Retrieves the current day number
     * @return The current day number.
     */
    public int getDayNumber() {
        return dayNumber;
    }

    /**
     * Changes the day plus one if survival is true
     * @return true if the day successfuly change forward
     */
    public void incrementDay() {
        if (survive) 
        {
            dayNumber++; //move to the next day
        }
    }

    /**
     * Retrieves the surivial boolean status.
     * @return The survival boolean
     */
    public boolean getSurvivalBoolean() {
        return survive;
    }

     /**
     * sets the surivial boolean status.
     * @param survival is the player alive
     * @return The survival boolean
     */
    public boolean setSurvivalBoolean(boolean survival) {
        survive = survival;
        return survive;
    }

    /**
     * Get the name of the ship
     * @return the ship name
     */
    public String getShipName() {
        return shipName;
    }

    /**
     * Set the name of the ship
     * @param shipName name of ship
     */
    public void setShipName(String shipName) {
        this.shipName = shipName;
    }

    /**
     * Retrieves the current morale level.
     * @return morale value
     */
    public int getMorale() {
        return this.morale;
    }

    /**
     * Sets the morale level.
     * @param morale new morale value
     */
    public void setMorale(int morale) {
        if(morale < 0) {
            throw new IllegalArgumentException("Morale cannot be negative");
        }
        this.morale = morale;
    }

    /**
     * Add to current morale caps at 100
     * @param morale number of morale added
     */
    public void addMorale(int morale) {
        if (100 - this.morale < morale) {
            this.morale = 100;
        } else {
            this.morale += morale;
        }
    }

    /**
     * Remove to current morale, min of 0
     * @param morale number of morale lost
     */
    public void removeMorale(int morale) {
        if (this.morale < morale) {
            this.morale = 0;
        } else {
            this.morale -= morale;
        }
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
     * @param this.resources new resources value
     */
    public void setResources(int resources) {
        if(resources < 0) {
            throw new IllegalArgumentException("Resources cannot be negative");
        }
        this.resources = resources;
    }

    /**
     * Add to current resource
     * @param resources new resources value
     */
    public void addResources(int resources) {
        this.resources += resources;
    }

    /**
     * Uses specified resources, if available.
     * @param resources amount of resources to use
     */
    public void removeResources(int resources) {
        if (this.resources >= resources) {
            this.resources -= resources;
        } else {
            System.out.println("Not enough resources!");
        }
    }

    /**
     * Sets the number of crew members.
     * @param crewNum new number of crew members
     */
    public void setCrewNum(int crewNum) {
        if(crewNum < 0) {
            throw new IllegalArgumentException("Crew number cannot be negative");
        }
        this.crewNum = crewNum;
    }

    /**
     * Add crew members.
     * @param num number of crew added
     */
    public void addCrewNum(int num) {
        this.crewNum += num;
    }

    /**
     * Removes crew Members
     * @param num number of crew remove
     */
    public void removeCrewNum(int num) {
        if (crewNum > num) {
            crewNum -= num;
        } else {
            setSurvivalBoolean(false);
        }
    }

    /**
     * Retrieves the number of crew members.
     * @return number of crew members
     */
    public int getCrewNum() {
        return crewNum;
    }

    /**
     * This sets the current planet that the player is currently on
     * @param currentPlanet the current planet
     */
    public void setCurrentPlanet(Planet currentPlanet) {
        this.currentPlanet = currentPlanet;
    }

    /**
     * This returns the current planet that the player is on
     * @return current planet
     */
    public Planet getCurrentPlanet() {
        return this.currentPlanet;
    }

    ////////////////////////////////////////////////////////
    /**
     * Retrieves the seed value
     * @return the integer seedvalue
     */
    //public int getSeedValue() {
    //    return seedValue;
    //}
    ///// COMMENTED OUT BC WE CURRENTLY DO NOT USE SEED /////
    @Override
    public String toString() {
        return "Player{" +
                " shipName=" + shipName +
                ", crewNum=" + crewNum +
                ", morale=" + morale +
                ", resources=" + resources +
                ", dayNumber=" + dayNumber +
                " }";
    }
}
