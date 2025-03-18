import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Represents a planet that the player can visit in the game.
 * Each planet has different attributes that affect the player's survival.
 */
public class Planet {
    private String name;            // Name of the planet
    private String affiliation;     // Faction that owns the planet (if any)
    private int healthPerDay;       // Health impact per day on this planet
    private int crewPerDay;         // Crew impact per day on this planet
    private int dangerLevel;        // Scale of risk for visiting (1-10)
    private List<String> amenities; // List of available services (fuel, food, etc.)
    private List<Event> events;     // Possible events that may happen on this planet
    private int atmosphere;         //Percent of O2 in in the planet

    private Planet nextPlanet;      //The next planet that will be traveled
    private boolean firstPlanet;     //The planet that they came from
    private boolean lastPlanet;      //The destination

    /**
     * Constructs a new Planet with the given attributes.
     *
     * @param name         The name of the planet.
     * @param affiliation  The faction that controls the planet (or "None" if unclaimed).
     * @param healthPerDay The health impact per day for the player.
     * @param crewPerDay   The crew impact per day (loss or gain).
     * @param dangerLevel  A rating from 1 to 10 indicating how dangerous this planet is.
     * @param amenities    A list of services available on this planet (e.g., refueling, market).
     */
    public Planet(String name, String affiliation, int healthPerDay, int crewPerDay, int dangerLevel, List<String> amenities, int atmosphere) {
        this.name = name;
        this.affiliation = affiliation;
        this.healthPerDay = healthPerDay;
        this.crewPerDay = crewPerDay;
        this.dangerLevel = dangerLevel;
        this.amenities = new ArrayList<>(amenities);
        this.events = new ArrayList<>();

        this.atmosphere = atmosphere;
        this.firstPlanet = false;
        this.lastPlanet = false;

    }

    /**
     * Gets the name of the planet.
     *
     * @return The planet's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the faction controlling the planet.
     *
     * @return The planet's affiliation (e.g., "Astros Confederation").
     */
    public String getAffiliation() {
        return affiliation;
    }

    /**
     * Gets the daily health impact for players on this planet.
     *
     * @return The health effect per day.
     */
    public int getHealthPerDay() {
        return healthPerDay;
    }

    /**
     * Gets the daily crew impact for players on this planet.
     *
     * @return The crew effect per day.
     */
    public int getCrewPerDay() {
        return crewPerDay;
    }

    /**
     * Gets the danger level of the planet.
     *
     * @return The danger level (1-10).
     */
    public int getDangerLevel() {
        return dangerLevel;
    }

    /**
     * Gets the list of amenities available on the planet.
     *
     * @return A list of amenities such as refueling, trading, etc.
     */
    public List<String> getAmenities() {
        return amenities;
    }

    /**
     * Adds an amenity to the planet.
     *
     * @param amenity The new amenity (e.g., "Fuel Station").
     */
    public void addAmenity(String amenity) {
        this.amenities.add(amenity);
    }

    /**
     * Gets the percent 02 level of the planet
     * @return the percent 02 level
     */
    public int getAtmosphere() { return this.atmosphere; }

    /**
     * Gets the list of events associated with this planet.
     *
     * @return A list of potential events that can happen.
     */
    public List<Event> getEvents() {
        return events;
    }

    /**
     * Adds an event to this planet.
     *
     * @param event The event to add.
     */
    public void addEvent(Event event) {
        this.events.add(event);
    }

    /**
     * Randomly triggers an event on the planet if any exist.
     *
     * @param player The player affected by the event.
     */
    public void triggerRandomEvent(Player player) {
        if (!events.isEmpty()) {
            Event randomEvent = events.get(new Random().nextInt(events.size()));
            randomEvent.triggerEvent();
            System.out.println("Event triggered on " + name + ": " + randomEvent.getDescription());
        }
    }

    /**
     * This set the next planet in the order of the determined path
     * @param next the next planet in the path
     */
    public void setNextPlanet(Planet next) {
        if (next != null) {
            this.nextPlanet = next;
        } else {
            throw new IllegalArgumentException("The next planet cannot be null");
        }
    }

    /**
     * This gets the next planet down the line
     * @return the next planet
     */
    public Planet getNextPlanet() {
        return this.nextPlanet;
    }

    /**
     * This sets the current planet as the starting point
     */
    public void setStartingPlanet() {
        this.firstPlanet = true;
    }

    /**
     * This sets the current planet as the ending point
     */
    public void setLastPlanet() {
        this.lastPlanet = true;
    }

    /**
     * This confirms if the planet is the starting planet
     * @return true if it's the starting planet
     */
    public boolean isStartPlanet() {
        return this.firstPlanet;
    }

    /**
     * This confirms if the planet is the last planet
     * @return true if it's the last planet
     */
    public boolean isLastPlanet() {
        return this.lastPlanet;
    }

    /**
     * Displays the planet's information.
     */
    public void displayPlanetInfo() {
        System.out.println();
        System.out.println("Planet: " + name);
        System.out.println("Affiliation: " + affiliation);
        System.out.println("Danger Level: " + dangerLevel + "/10");
        System.out.println("Health Effect Per Day: " + healthPerDay);
        System.out.println("Crew Effect Per Day: " + crewPerDay);
        System.out.println("Amenities: " + amenities);
        System.out.println("Number of Possible Events: " + events.size());
        System.out.println("Atmosphere level: " + atmosphere + "% Oxygen");
    }
}