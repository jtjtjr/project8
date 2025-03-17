import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Scanner;

public class PlanetIntegrationTest {

    private List<Planet> testPlanets;
    private Planet currentPlanet;
    private Player testPlayer;

    @BeforeEach
    void setUp() {
        // Load planets from CSV
        testPlanets = PlanetLoader.loadPlanets();
        assertNotNull(testPlanets, "Planet list should not be null");
        assertFalse(testPlanets.isEmpty(), "Planet list should not be empty");

        // Assign starting planet
        for (Planet planet : testPlanets) {
            if (planet.isStartPlanet()) {
                currentPlanet = planet;
                break;
            }
        }

        // Initialize test player
        testPlayer = new Player(1, null, 5, 50, 100, "Test Ship");
        testPlayer.setCurrentPlanet(currentPlanet);
    }

    @Test
    void testPlanetLoading() {
        assertNotNull(testPlanets, "Planets should be loaded");
        assertFalse(testPlanets.isEmpty(), "There should be at least one planet");

        Planet firstPlanet = testPlanets.get(0);
        assertNotNull(firstPlanet.getName(), "Planet should have a name");
        assertNotNull(firstPlanet.getAffiliation(), "Planet should have an affiliation");
        assertTrue(firstPlanet.getDangerLevel() >= 1 && firstPlanet.getDangerLevel() <= 10, "Danger level should be within valid range");
    }

    @Test
    void testPlanetNavigation() {
        assertNotNull(currentPlanet, "Starting planet should not be null");
        assertNotNull(currentPlanet.getNextPlanet(), "There should be a next planet");

        Planet nextPlanet = currentPlanet.getNextPlanet();
        testPlayer.setCurrentPlanet(nextPlanet);

        assertEquals(nextPlanet, testPlayer.getCurrentPlanet(), "Player should have moved to the next planet");
    }

    @Test
    void testTriggerEventOnPlanet() {
        assertNotNull(currentPlanet, "Current planet should not be null");

        int initialResources = testPlayer.getResources();
        int initialMorale = testPlayer.getMorale();

        // Trigger a random event
        currentPlanet.triggerRandomEvent(testPlayer);

        // Ensure an event has an effect (this may need more specific testing depending on event logic)
        assertTrue(testPlayer.getResources() != initialResources || testPlayer.getMorale() != initialMorale, "An event should modify player stats");
    }
}