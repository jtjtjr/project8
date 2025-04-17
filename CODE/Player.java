
/**
 * The Day class represents a single day in Galactic Trail (name of game), 
 *containing the day number, survival boolean, and random event handling.
 */
public class Player {
    
    private String userName;
    private String shipName;
    private int crewNum;
    private int morale;
    private int resources;
    private Planet currentPlanet;
    private int dayNumber;
    private boolean survive;
    private boolean hardMode = false;

    private Ship ship;
    private Pace pace;
    private int money;

    /**
     * Default Constructor that takes no input yet
     * This is only for testing purposes
     */
    public Player() {
        this.userName = "TestBob";
        this.dayNumber = 1;
        this.survive = true;
        this.morale = -1;
        this.crewNum = -1;
        this.resources = -1;
        this.shipName = "";
        this.currentPlanet = null;
        this.pace = Pace.SLOW;
        this.money = -1;
        createShip();
    }

    /**         CHANGE
     * Constructs a Player object with a specified day number and list of possible events
     *The survival status is initially set to true
     *A seed value is randomly selected based on the size of the possible events list
     * 
     * @param dayNumber The current day number.
     */
    public Player(String userName, int crewNum, int morale, int resources, String shipName) {
        this.userName = userName;
        this.dayNumber = 1;
        this.survive = true; 
        this.crewNum = crewNum;
        this.morale = morale;
        this.resources = resources;
        this.shipName = shipName;
        this.pace = Pace.SLOW;
        createShip();
        this.money = 0;
    }

    /**
     * Ship class that determines the difficulty of the game
     * sets the resources burned depending on day according to pace
     */
    public class Ship {
        private int resourceCost;
        private String shipType;

        public Ship(Pace pace) {

            switch(pace) {
                case SLOW:
                    this.resourceCost = 20 + 10*crewNum;
                    this.shipType = "SS Driftwing";
                    break;
                case NORMAL:
                    this.resourceCost = 25 + 13*crewNum;
                    this.shipType = "SS StarBorne";
                    break;
                case FAST:
                    this.resourceCost = 30 + 15*crewNum;
                    this.shipType = "SS Nova Viper";
                    break;
            }

        }

        public int resourceCost() {
            return this.resourceCost;
        }

        public String shipType() {
            return this.shipType;
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
     * Retrieves the current pace of the game.
     * @return The current pace.
     */
    public Pace getPace() {
        return this.pace;
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
        Frontend.displayTextSlowly("Ship: " + this.shipName + ", Ship Type:" + this.ship.shipType() + ", Day: " + this.dayNumber + ", Crew: " + this.crewNum + ", Resource: " + this.resources + ", Morale: " + this.morale + "\n");
        if (this.crewNum == 1) {
            Frontend.displayTextSlowly("WARNING: Only 1 crew member left!\n");
        }
        if (this.morale <= 5 && this.morale > 0) {
            Frontend.displayTextSlowly("WARNING: Low team morale!\n");
        }
        if (this.resources <= 100 && this.resources > 0) {
            Frontend.displayTextSlowly("WARNING: Low resources left!\n");
        }
        if (this.morale <= 0) {
            Frontend.displayTextSlowly("WARNING: No team morale!\n");
        }
        if (this.resources <= 0) {
            Frontend.displayTextSlowly("WARNING: No resources left!\n");
        }
    }

    /**
     * The next day of the game
     * Consumes resource, increment day, ...
     * 
     * This is what should be called as game progess
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
     * Set the money that player has
     * @param money int value of money
     */
    public void setMoney(int money) {
        this.money = money;
    }

    /**
     * Get the current money that player has
     * @return current money
     */
    public int getMoney() {
        return this.money;
    }

    /**
     * Spend money method
     * @param money money deducted CHECKS if player has enough
     */
    public void removeMoney(int money) {
        if (this.money >= money) {
            this.money -= money;
        } else {
            System.out.println("NOT ENOUGH MONEY: BACKEND");
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
        if ((this.resources+resources) <= 1000) {
            this.resources += resources;
        }
        else {
            this.resources = 1000;
        }
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

    /**
     * This mode that the player is using
     */
    public void setHardMode(boolean mode) {
        this.hardMode = mode;
    }
    
    /**
     * This returns the current mode that the player is using
     * @return current mode
     */
    public boolean isHardMode() {
        return this.hardMode;
    }
    

    ////////////////////////////////////////////////////////
    /**
     * toString of player
     * @return String of some information in player class
     */
    @Override
    public String toString() {
        return "Player{" +
                " shipName=" + this.shipName +
                ", shipType=" + this.ship.shipType() +
                ", crewNum=" + this.crewNum +
                ", morale=" + this.morale +
                ", resources=" + this.resources +
                ", dayNumber=" + this.dayNumber +
                ", resourceConsumed=" + this.ship.resourceCost() +
                " }";
    }
}
