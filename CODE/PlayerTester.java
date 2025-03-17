import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the Player class.
 */
public class PlayerTester {
    private Player testPlayer;

    @BeforeEach
    void setUp() {
        // TODO: Add Crew and Ship tests
        testPlayer = new Player(1, 5, 10, 10, "Test Ship");
    }

    @Test
    void testPlayerInitialization() {
        assertEquals(1, testPlayer.getDayNumber());
        assertEquals(5, testPlayer.getCrewNum());
        assertEquals(10, testPlayer.getMorale());
        assertEquals(10, testPlayer.getResources());
        assertEquals("Test Ship", testPlayer.getShip().getName());
    }

    @Test
    void testMorale() {
        testPlayer.updateMorale(5);
        assertEquals(15, testPlayer.getMorale());
        testPlayer.updateMorale(-3);
        assertEquals(12, testPlayer.getMorale());
        assertThrows(IllegalArgumentException.class, () -> testPlayer.setMorale(-5));
        testPlayer.setMorale(20);
        assertEquals(20, testPlayer.getMorale());
    }

    @Test
    void testResources() {
        testPlayer.setResources(20);
        assertEquals(20, testPlayer.getResources());
        assertThrows(IllegalArgumentException.class, () -> testPlayer.setResources(-5));
    }

    // test setting, adding, and removing crew members
    @Test
    void testCrewNum() {
        testPlayer.setCrewNum(8);
        assertEquals(8, testPlayer.getCrewNum());
        assertThrows(IllegalArgumentException.class, () -> testPlayer.setCrewNum(-5));
        testPlayer.setCrewNum(0);
        testPlayer.addCrewMember();
        assertEquals(1, testPlayer.getCrewNum());
        testPlayer.removeCrewMember();
        assertEquals(0, testPlayer.getCrewNum());
        testPlayer.removeCrewMember();
        assertEquals(0, testPlayer.getCrewNum());
    }

}
