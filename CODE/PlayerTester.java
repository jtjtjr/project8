import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the Player class.
 */
public class PlayerTester {
    private Player testPlayer;

    @BeforeEach
    void setUp() {
        testPlayer = new Player("user", 5, 10, 10, "Ship");
    }

    @Test
    void testPlayerInitialization() {
        assertEquals(1, testPlayer.getDayNumber());
        assertEquals(5, testPlayer.getCrewNum());
        assertEquals(10, testPlayer.getMorale());
        assertEquals(10, testPlayer.getResources());
        assertEquals("Ship", testPlayer.getShipName());
    }

    @Test
    void testMorale() {
        testPlayer.addMorale(5);
        assertEquals(15, testPlayer.getMorale());
        testPlayer.addMorale(-3);
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
        testPlayer.addCrewNum(1);
        assertEquals(1, testPlayer.getCrewNum());
        testPlayer.removeCrewNum(1);
        assertEquals(false, testPlayer.getSurvivalBoolean());
    }

    @Test
    void testCurrentPlanet() {
        List<String> amenities = new ArrayList<>();
        amenities.add("Test Amenity");
        Planet planet = new Planet("Test Planet", "Test Affiliation", 5, 2, 3, amenities, 1);
        testPlayer.setCurrentPlanet(planet);
        assertEquals(planet, testPlayer.getCurrentPlanet());
    }

    @Test
    void testSetShipName() {
        testPlayer.setShipName("New Ship");
        assertEquals("New Ship", testPlayer.getShipName());
        testPlayer.setShipName("");
        assertEquals("", testPlayer.getShipName());
        testPlayer.setShipName("A\na");
        assertEquals("A\na", testPlayer.getShipName());
    }

    @Test
    void testShipPace(){
        testPlayer.setPace(2);
        assertEquals(25+13*5, testPlayer.getShip().resourceCost());

        testPlayer.setPace(3);
        assertEquals(30+15*5, testPlayer.getShip().resourceCost());
    }

    @Test
    void testToString() {
        String expected = "Player{ shipName=Ship, shipType=SS Driftwing, crewNum=5, morale=10, resources=10, dayNumber=1, dailyResourceCost=70 }";
        assertEquals(expected, testPlayer.toString());
    }

}
