import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

/**
 * Unit tests for the Planet class.
 */
public class PlanetTester {
    private Planet testPlanet;
    private ArrayList<Event> eventList = new ArrayList<>();
    private ArrayList<String> amenities = new ArrayList<>();

    @BeforeEach
    void setUp() {
        amenities.add("Fuel");
        amenities.add("Market");
        eventList.add(new Event("Test Event desc", 9999999, "Test Event", 5, 10, null)); // last parameter is player, but player needs event list to be created, which is a paradox. Bug found!
        testPlanet = new Planet("Test Planet", "Test Faction", 5, 10, 5, amenities, 20);

        for (Event event : eventList) {
            testPlanet.addEvent(event);
        }
    }

    @Test
    void testPlanetInitialization() {
        assertEquals("Test Planet", testPlanet.getName());
        assertEquals("Test Faction", testPlanet.getAffiliation());
        assertEquals(5, testPlanet.getHealthPerDay());
        assertEquals(10, testPlanet.getCrewPerDay());
        assertEquals(5, testPlanet.getDangerLevel());
        assertEquals(2, testPlanet.getAmenities().size());
        assertEquals(20, testPlanet.getAtmosphere());
    }

    @Test
    void testAddAmenities() {
        testPlanet.addAmenity("Test Amenity");
        assertEquals(3, testPlanet.getAmenities().size());
        testPlanet.addAmenity("s\ns\n");
        assertEquals(4, testPlanet.getAmenities().size());
        testPlanet.addAmenity("");
        assertEquals(5, testPlanet.getAmenities().size());
    }

    @Test
    void testAddEvents() {
        ArrayList<Event> tempEventList = new ArrayList<>();
        tempEventList.add(new Event("", 9999999, "Test Event", 5, 10, null));
        tempEventList.add(new Event("Test desc", 9999999, "", 5, 10, null));
        tempEventList.add(new Event("Test Event desc", 9999999, "Test Event", 0, 10, null)); // last parameter is player, but player needs event list to be created, which is a paradox. Bug found!
        tempEventList.add(new Event("Test Event desc", 9999999, "Test Event", 5, 0, null));
        //TODO: add player to the events once bug mentioned above is fixed
        
        int x = 0;
        for (Event event : tempEventList) {
            testPlanet.addEvent(event);
            assertEquals(tempEventList.get(x), testPlanet.getEvents().get(x+1));
            x++;
        }
    }

    @Test
    void testGetNextPlanet() {
        assertThrows(IllegalArgumentException.class, () -> testPlanet.getNextPlanet()); // CHANGE IN CODE
        Planet testPlanet2 = new Planet("Test Planet 2", "Test Faction 2", 5, 10, 5, amenities, 20);
        testPlanet.setNextPlanet(testPlanet2);
        assertEquals(testPlanet2, testPlanet.getNextPlanet());
    }

    @Test
    void testTriggerRandomEvent() {
        // testPlanet.triggerRandomEvent(null); fix the dependency feedback loop b/n event and player before testing this
    }
    
}
