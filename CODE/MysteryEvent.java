import java.util.Random;

/**
 * Represents a chance mystery event that affects the player's morale and/or resources during travel.
 * Extends the Frontend class to handle text display.
 */
public class MysteryEvent {

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
        int eventType = random.nextInt(10);
        Frontend.displayTextSlowly("Something else happened while traveling... Let's see what it is!\n");

        switch (eventType) {
            case 0:
                int resourceGain = random.nextInt(25) + 5;
                player.addResources(resourceGain);
                Frontend.displayTextSlowly("Upon taking inventory, you discover you actually have " + resourceGain + " resources more than you thought!");
                break;

            case 1:
                int moraleLoss = random.nextInt(10) + 5;
                player.removeMorale(moraleLoss);
                Frontend.displayTextSlowly("Your crewmates are feeling particularly homesick... They lost " + moraleLoss + " morale.");
                break;

            case 2:
                resourceGain = random.nextInt(40) + 20;
                player.addResources(resourceGain);
                Frontend.displayTextSlowly("While out in space, you met a mysterious space-faring philantropist who gave your crew " + resourceGain + " resources!");
                break;

            case 3:
                int moraleGain = random.nextInt(15) + 5;
                player.addMorale(moraleGain);
                Frontend.displayTextSlowly("Your crew found a nice movie hidden in one of your storage units to take the edge off. They gained " + moraleGain + " morale.");
                break;

            case 4:
                int resourceLoss = random.nextInt(30) + 10;
                player.removeResources(resourceLoss);
                Frontend.displayTextSlowly("One of your crewmates carelessly damaged some of your supplies while moving around the ship. You lost " + resourceLoss + " resources.");
                break;

            case 5:
                if(player.getPace() == Player.Pace.FAST) {
                    resourceLoss = random.nextInt(20) + 10;
                    player.removeResources(resourceLoss);
                    Frontend.displayTextSlowly("You were traveling at a fast pace, so you didn't notice a small asteroid field and had to take evasive manuevers. You lost " + resourceLoss + " resources.");
                } else {
                    Frontend.displayTextSlowly("Sike, you're traveling at a slower pace, so nothing happened. You evaded the incoming asteroid field with minimal expense.");
                }
                break;

            case 6:
                if(player.getResources() < 100) {
                    resourceGain = random.nextInt(50) + 20;
                    player.addResources(resourceGain);
                    Frontend.displayTextSlowly("A strange ship approached you and took pity on your crew. They gave you " + resourceGain + " resources.");
                } else {
                    Frontend.displayTextSlowly("A strange ship approached you, but they deduced you had enough resources. They left you alone.");
                }
                break;

            case 7:
                if(player.getMorale() < 20) {
                    moraleGain = random.nextInt(10) + 10;
                    player.addMorale(moraleGain);
                    Frontend.displayTextSlowly("At your wit's end, a mysterious figure appeared and bestowed your crew hope. They gained " + moraleGain + " morale.");
                } else {
                    Frontend.displayTextSlowly("A mysterious figure appeared and tried to cheer up your crew, but they were already in high spirits.");
                }
                break;
            
            case 8:
                moraleLoss = random.nextInt(5) + 5;
                player.removeMorale(moraleLoss);
                Frontend.displayTextSlowly("Your crewmates had an incredibly heated argument over a missing lunch meal. They lost " + moraleLoss + " morale.");
                break;

            case 9:
                resourceLoss = random.nextInt(5) + 20;
                player.removeResources(resourceLoss);
                Frontend.displayTextSlowly("The ship's engine malfunctioned and sent a bunch of fuel out into space. You lost " + resourceLoss + " resources.");
                break;

            default:
                System.out.println("No mystery event occurred. You shouldn't see this!");
        }
        return eventType; // Return the type of event that occurred for testing purposes
    }
    
}
