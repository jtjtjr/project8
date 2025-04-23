import java.util.Random;

/**
 * Represents an event that affects the player's morale and resources.
 * Assumes that both morale_effect and resources_effect are negative for the player. 
 * This assumuption is prone to change
 */
public class Event {
    private String description;

    //The negative effect on the player's morale and resources respectivly
    private int morale_effect; //keep positive if assuming this effect is negative
    private int resources_effect;
    private int event_id;
    private String event_name;
    // private Random random = new Random(); // Possible to add more chance in gameplay 
    private Player player;

    /**
     * Constructs an Event with a description and its effects on morale and resources.
     * @param description The description of the event.
     * @param morale_effect The effect on the player's morale (be a positive value that will be subtracted).
     * @param resources_effect The effect on the player's resources (be a positive value that will be subtracted).
     * @param player The player affected by the event.
     */
    public Event(String description, int event_id, String event_name, int morale_effect, int resources_effect, Player player) {
        this.description = description;
        this.morale_effect = morale_effect;
        this.resources_effect = resources_effect;
        this.player = player;
        this.event_id = event_id;
        this.event_name = event_name;
    }

    /**
     * Gets the event name
     * @return The event_name.
     */
    public String getEventName() {
        return event_name;
    }

    /**
     * Gets the event id.
     * @return The event_id .
     */
    public int getEventID() {
        return event_id;
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
       // System.out.println("Event: " + description);
        Boolean nofail = true;
        
        if (!player.getSurvivalBoolean()) {
            throw new IllegalArgumentException("Player should be alive for the event to work");
        }

        if ((player.getResources() - resources_effect) < 0) {
            player.setSurvivalBoolean(false);
            nofail = false;
        } else {
            player.setResources(player.getResources() - resources_effect);
            
        }

        if ((player.getMorale() - morale_effect) < 0) {
            // Assume morale below zero kills the player? Prone to change.
            player.setSurvivalBoolean(false);
            nofail = false;
        } else {
            player.setMorale(player.getMorale() - morale_effect);
           // player.incrementDay();
        }

        if (nofail==true){
            player.incrementDay();
        }
    }
    /**
     * Sacrifice a players crew memeber, this can include the player themselves - if the player
     * safricses themselves the game ends. If the player sacrifises the crew further resources are gained for the player
     * @return -2 is more of a error check, -1 means the player died, 1- means the morale went up and 0 means morale went down
     */
    public int sacrifice(){
        if(player.getCrewNum()>=1){
            //TODO add specific flags?
            player.setResources(player.getResources()+25);
            Random rand = new Random();
            int randomInt = rand.nextInt(2);
            if (randomInt==0){
                player.setMorale(player.getMorale()-5);
            }
            else{
                player.setMorale(player.getMorale()+5);
            }
            player.removeCrewNum(1);
            return randomInt;
        }
        else if(player.getCrewNum()==0){
            player.setSurvivalBoolean(false);
            return -1;
        }
        else{
            return -2;
        }
        //return 0;
    }

    /**
     * Reduces the player's resources by 10% (rounded to nearest whole number).
     * If resources fall below 0, sets survival status to false.
     * 
     * @return The player's remaining resources after deduction.
     */
    public int percentLossResources(){
        int curr_res = player.getResources();
        int tenPercent = (int) Math.round(curr_res * 0.1);
        player.setResources(player.getResources()-tenPercent);
        if (player.getResources()< 0) {
            player.setSurvivalBoolean(false);
        }
        return player.getResources();
    }

    /**
     * Reduces the player's morale by 10% (rounded to nearest whole number).
     * If morale falls below 0, sets survival status to false.
     * 
     * @return The player's remaining morale after deduction.
     */
    public int percentLossMorale(){ 
        int curr_mor = player.getMorale(); 
        int tenPercent = (int) Math.round(curr_mor * 0.1); 
        player.setMorale(player.getMorale()-tenPercent);
        if (player.getMorale()< 0) {
            player.setSurvivalBoolean(false);
        }
        return player.getMorale();
    }

    /**
     * Randomly triggers a 30% chance of negative effects when sacrificing a crewmate in hard mode:
     * - 10% chance: Loses 10% of resources.
     * - 10% chance: Loses 10% of morale.
     * - 10% chance: Loses 10% of both resources and morale.
     * - 70% chance: No effect.
     * 
     * @return A message describing the outcome, or null if nothing happens.
     */
    public String hardModeSacrifice(){
        Random rand = new Random();
        int randomInt = rand.nextInt(10);
        //chance loss
        if (randomInt==0){
            int result = percentLossResources();
            String accident = "The sacrificed crewmate demands a last meal request and eats 10% of your resources!\n";
            return accident + "Leaving you with " + result + " resources";
        }
        else if (randomInt==1)
        {
            int result = percentLossMorale();
            String accident = "The sacrificed crewmate admits that he pissed on the wall, taking morale down by 10%\n";
            return accident + "Leaving you with " + result + " morale";
        }
        else if (randomInt==2)
        {
            int result_1 = percentLossResources();
            int result_2 = percentLossMorale();
            String accident = "The sacrificed crewmate begins crying and binge eating, taking morale and resources down 10%\n";
            return accident + "Leaving you with " + result_1 + " resources and " + result_2 + " morale";
        }
        else{
            return "";
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
