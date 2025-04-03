import java.util.Random;

/**
 * Represents a chance mystery event that affects the player's morale and/or resources during travel.
 * Extends the Frontend class to handle text display.
 */
public class MysteryEvent extends Frontend {

    private Player player;
    private Random random;

    public MysteryEvent(Player player, Random random) {
        this.player = player;
        this.random = random;
    }

    /**
     * Triggers a mystery event that affects the player's morale and resources. Hardcoded because there won't be that many of these.
     */
    public int triggerMysteryEvent() {
        int eventType = random.nextInt(5);
        displayTextSlowly("Something else happened while traveling... Let's see what it is!\n");

        switch (eventType) {
            case 0:
                int resourceGain = random.nextInt(25) + 5;
                player.addResources(resourceGain);
                displayTextSlowly("Upon taking inventory, you discover you actually have " + resourceGain + " resources more than you thought!");
                break;

            case 1:
                int moraleLoss = random.nextInt(10) + 5;
                player.removeMorale(moraleLoss);
                displayTextSlowly("Your crewmates are feeling particularly homesick... They lost " + moraleLoss + " morale.");
                break;

            case 2:
                resourceGain = random.nextInt(40) + 20;
                player.addResources(resourceGain);
                displayTextSlowly("While out in space, you met a mysterious space-faring philantropist who gave your crew " + resourceGain + " resources!");
                break;

            case 3:
                int moraleGain = random.nextInt(15) + 5;
                player.addMorale(moraleGain);
                displayTextSlowly("Your crew found a nice movie hidden in one of your storage units to take the edge off. They gained " + moraleGain + " morale.");
                break;

            case 4:
                int resourceLoss = random.nextInt(30) + 10;
                player.removeResources(resourceLoss);
                displayTextSlowly("One of your crewmates carelessly damaged some of your supplies while moving arount the ship. You lost " + resourceLoss + " resources.");
                break;

            default:
                System.out.println("No mystery event occurred. You shouldn't see this!");
        }
        return eventType; // Return the type of event that occurred for testing purposes
    }
    
}
