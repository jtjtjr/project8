
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

    private Ship ship;

    //POTENTIAL IMPLEMENTS
    private int fuel;
    private Pace pace;

    //CARGO?


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

        //CHANGE: added to just not cause error
        this.pace = Pace.SLOW;
        this.fuel = -1;

    }

    /** 
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

        //CHANGE: added to just not cause error
        this.pace = Pace.SLOW;
        this.fuel = -1;
    }

    /**
     * Ship class that determines the difficulty of the game
     * sets the resources burned depending on day according to pace
     */
    public class Ship {
        private int resourceCost;
        private String shipName;

        public Ship(Pace pace) {

            switch(pace) {
                case SLOW:
                    this.resourceCost = 100;
                    this.shipName = "SS Driftwing";
                    break;
                case NORMAL:
                    this.resourceCost = 120;
                    this.shipName = "SS StarBorne";
                    break;
                case FAST:
                    this.resourceCost = 130;
                    this.shipName = "SS Nove Viper";
                    break;
            }

        }

        public int resourceCost() {
            return this.resourceCost;
        }

        public String shipName() {
            return this.shipName;
        }
    }

    /**
     * Sets the pace of the game between 3 options, kind of like the difficulty of the game
     */
    public enum Pace {
        SLOW, NORMAL, FAST;
    }

    private void createShip() {
        this.ship = new Ship(this.pace);
    }

    /**
     * Sets the pace if the game
     * @param pace 1,2, or 3 for now (changable)
     */
    public void setPace(int pace) {
        this.pace = switch (pace) {
            case 1 -> Pace.SLOW;
            case 2 -> Pace.NORMAL;
            case 3 -> Pace.FAST;
            default -> throw new IllegalArgumentException("Invaid option for pace");
        };
        createShip();
    }

    /**
     * If dead (or not) resets the game
     */
    public void reset() {
        this.dayNumber = 1;
        setSurvivalBoolean(true);
        this.morale = -1;
        this.crewNum = -1;
        this.resources = -1;
    }

    /** 
     * Display the current infomation for player(day,crew,...to be added)
     */
    public void display() {
        System.out.println("Ship: " + this.ship.shipName() + ",Day: " + this.dayNumber + ", Crew: " + this.crewNum + ", Resource: " + this.resources + ", Morale: " + this.morale);
    }

    /**
     * The next day of the game
     * Consumes resource, increment day, ...
     */
    public void nextDay() {
        if (!this.survive) 
            return;
        
        incrementDay();

        if (this.resources <= 0)
            this.crewNum -= 1;

        if (this.crewNum <= 0)
            setSurvivalBoolean(false);

        this.resources -= this.ship.resourceCost();
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
        if (survive) {
            this.dayNumber += switch (this.pace) {
                case SLOW -> 3;
                case NORMAL -> 2;
                case FAST -> 1;
            };
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
     */
    public void setSurvivalBoolean(boolean survival) {
        this.survive = survival;
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
        //TODO Sting null if null shipName
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
        //TODO cannot go over 1000
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
