import java.util.Random;

/**
 * Represents an event that affects the player's morale and resources.
 * Assumes that both morale_effect and resources_effect are negative for the player. 
 * This assumuption is prone to change
 */
public class Event {
    private String description;

    private int crew_effect; //- needs implementation once crew is finalized as a data struct

    //The negative effect on the player's morale and resources respectivly
    private int morale_effect; //keep positive if assuming this effect is negative
    private int resources_effect;
    // private Random random = new Random(); // Possible to add more chance in gameplay 
    private Player player;

    /**
     * Constructs an Event with a description and its effects on morale and resources.
     * @param description The description of the event.
     * @param morale_effect The effect on the player's morale (be a positive value that will be subtracted).
     * @param resources_effect The effect on the player's resources (be a positive value that will be subtracted).
     * @param player The player affected by the event.
     */
    public Event(String description, int morale_effect, int resources_effect, Player player) {
        this.description = description;
        this.morale_effect = morale_effect;
        this.resources_effect = resources_effect;
        this.player = player;
    }

    /**
     * Gets the description of the event.
     * @return The event description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the event.
     * @param description_change The changed event description.
     */
    public void setDescription(String description_change){
        description = description_change;
    }

    /** Gets the morale effect of the event.
     * @return The negative effect on the player's morale.
     */
    public int getMoraleEffect(){
        return morale_effect;
    }

    /** Sets the morale effect of the event.
     * @param morale_effect_change  the changed morale effect value.
     */
    public void setMoraleEffect(int morale_effect_change) {
        morale_effect = morale_effect_change;
    }

    /**
     * Gets the resources effect of the event.
     * @return The negative effect on the player's resources.
     */
    public int getResourcesEffect() {
        return resources_effect;
    }

    /**
     * Sets the resources effect of the event.
     * @param resources_effect_change The new resources effect value.
     */
    public void setResourcesEffect(int resources_effect_change){
        resources_effect = resources_effect_change;
    }

    /**
     * Triggers the event, affecting the player's morale and resources.
     * If the player's resources or morale drop below zero, they are considered dead.
     * @throws IllegalArgumentException If the player is already dead before the event occurs.
     */
    public void triggerEvent() {
        System.out.println("Event: " + description);
        
        if (!player.getSurvivalBoolean()) {
            throw new IllegalArgumentException("Player should be alive for the event to work");
        }

        if ((player.getResources() - resources_effect) < 0) {
            player.setSurvivalBoolean(false);
        } else {
            player.setResources(player.getResources() - resources_effect);
        }

        if ((player.getMorale() - morale_effect) < 0) {
            // Assume morale below zero kills the player? Prone to change.
            player.setSurvivalBoolean(false);
        } else {
            player.setMorale(player.getMorale() - morale_effect);
        }
    }

    /** Returns a string representation of the event.
     * @return A formatted string describing the event and its effects.
     */
    @Override
    public String toString() {
        return "Event:" +
            "description='" + description + '\'' + 
            ", morale_effect=" + morale_effect +
            ", resources_effect=" + resources_effect +
            '.';
    }
}
