import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * Test class for the MysteryEvent.
 */
public class MysteryEventTest {
    private Player player;
    private MysteryEvent mysteryEvent;

    @BeforeEach
    void setUp() {
        this.player = new Player( 1, 5, 50, 100, "Ship");
        this.mysteryEvent = new MysteryEvent(player, new java.util.Random());
    }

    @Test
    void testMysteryEvent() {
        // Run test with 100 iterations to cover all event types, could use seeds for reproducibility but I'm adding a couple more events later
        for (int i = 0; i < 100; i++) {
            player.setResources(100);
            player.setMorale(50);
            int eventType = mysteryEvent.triggerMysteryEvent();
            assertTrue(eventType >= 0 && eventType < 5, "Event type should be between 0 and 4");

            switch (eventType) {
                case 0: // Resource gain
                    assertTrue(player.getResources() >= 105 && player.getResources() <= 130,
                        "Resource gain event should increase resources by 5 to 30. Current resources: " + player.getResources());
                    break;
                case 1: // Morale loss
                    assertTrue(player.getMorale() >= 35 && player.getMorale() <= 45,
                        "Morale loss event should decrease morale by 5 to 15. Current morale: " + player.getMorale());
                    break;
                case 2: // Resource gain 2
                    assertTrue(player.getResources() >= 120 && player.getResources() <= 160,
                        "Resource gain event 2 should increase resources by 20 to 60. Current resources: " + player.getResources());
                    break;
                case 3: // Morale gain
                    assertTrue(player.getMorale() >= 55 && player.getMorale() <= 70,
                        "Morale gain event should increase morale by 5 to 20. Current morale: " + player.getMorale());
                    break;
                case 4: // Resource loss
                    assertTrue(player.getResources() >= 60 && player.getResources() <= 90,
                        "Resource loss event should decrease resources by 10 to 40. Current resources: " + player.getResources());
                    break;
                default:
                    fail("Unexpected event type: " + eventType);   
            }

            
        }
    }
    
}
