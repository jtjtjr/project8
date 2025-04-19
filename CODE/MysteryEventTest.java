import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * Test class for the MysteryEvent. Make sure textTimer in Frontend is set to 0 before running tests.
 */
public class MysteryEventTest {
    private Player player;
    private MysteryEvent mysteryEvent;

    @BeforeEach
    void setUp() {
        this.player = new Player("user", 5, 50, 100, "Ship");
        this.mysteryEvent = new MysteryEvent(player, new java.util.Random());
    }

    @Test
    void testMysteryEvent() {
        // Run test with 100 iterations to cover all event types, could use seeds for reproducibility but I'm adding a couple more events later
        for (int i = 0; i < 100; i++) {
            player.setResources(100);
            player.setMorale(50);
            int eventType = mysteryEvent.triggerMysteryEvent();
            assertTrue(eventType >= 0 && eventType < 10, "Event type should be between 0 and 9");

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
                case 5: // Resource loss at fast pace
                    if (player.getPace() == Player.Pace.FAST) {
                        assertTrue(player.getResources() >= 70 && player.getResources() <= 90,
                            "Resource loss event at fast pace should decrease resources by 10 to 30. Current resources: " + player.getResources());
                    } else {
                        assertEquals(100, player.getResources(), "No resource loss at slower pace. Current resources: " + player.getResources());
                    }
                    break;
                case 6: // Resource gain at low resources
                    if (player.getResources() < 100) {
                        assertTrue(player.getResources() >= 120 && player.getResources() <= 170,
                            "Resource gain event at low resources should increase resources by 20 to 70. Current resources: " + player.getResources());
                    } else {
                        assertEquals(100, player.getResources(), "No resource gain at high resources. Current resources: " + player.getResources());
                    }
                    break;
                case 7: // Morale gain at low morale
                    if (player.getMorale() < 50) {
                        assertTrue(player.getMorale() >= 60 && player.getMorale() <= 70,
                            "Morale gain event at low morale should increase morale by 10 to 20. Current morale: " + player.getMorale());
                    } else {
                        assertEquals(50, player.getMorale(), "No morale gain at high morale. Current morale: " + player.getMorale());
                    }
                    break;
                case 8: // Morale loss
                    assertTrue(player.getMorale() >= 40 && player.getMorale() <= 45,
                        "Morale loss event should decrease morale by 5 to 10. Current morale: " + player.getMorale());
                    break;
                case 9: // Resource loss
                    assertTrue(player.getResources() >= 75 && player.getResources() <= 80,
                        "Resource loss event should decrease resources by 20 to 25. Current resources: " + player.getResources());
                    break;

                default:
                    fail("Unexpected event type: " + eventType);   
            }
        }
    }
}
