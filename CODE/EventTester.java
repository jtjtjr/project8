import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

/**
 * Unit tests for the Event class, excluding Player-related functionality.
 */
public class EventTester {
    private Event event;
    private Event event_2;
    private Player test_guy;
    private Event event_3;

    @BeforeEach
    void setUp() {
        // Create a test event (Player is set to null since it's not being tested)
        event = new Event("Test Event", 1, "coyote", 10, 15, null);
        ArrayList<Event> eventList = new ArrayList<>();
       eventList.add(event);
         test_guy = new Player(1, eventList, 5, 10, 10, null);
        event_2 = new Event("Test Event_2", 2, "animal" , 6, 7, test_guy);
        event_3 = new Event("Test Event_2", 3, "bruush attack", 0, 7, test_guy);
    }

    //Test getDescription() and setDescription() 
    @Test
    void testGetDescription(){
        assertEquals("Test Event", event.getDescription());
    }

    @Test
    void testSetDescription(){
        event.setDescription("Updated Event");
        assertEquals("Updated Event", event.getDescription());
    }

    //Test getEventName() and getEventID() 
    @Test
    void testGetName() {
        assertEquals("coyote", event.getEventName());
    }
    @Test
    void testGetID() {
        assertEquals(1, event.getEventID());
    }



    //Test getMoraleEffect() and setMoraleEffect() 
    @Test
    void testGetMoraleEffect() {
        assertEquals(10, event.getMoraleEffect());
    }

    @Test
    void testSetMoraleEffect(){
        event.setMoraleEffect(20);
        assertEquals(20, event.getMoraleEffect());
    }



    //Test getResourcesEffect() and setResourcesEffect() 
    @Test
    void testGetResourcesEffect() {
        assertEquals(15, event.getResourcesEffect());
    }

    @Test
    void testSetResourcesEffect(){
        event.setResourcesEffect(25);
        assertEquals(25, event.getResourcesEffect());
    }

    //Test triggerEvent()
    @Test
    void testTriggerEvent(){
        event_2.triggerEvent();
        assertEquals(4, test_guy.getMorale());
        assertEquals(3, test_guy.getResources());

        event_3.triggerEvent(); //should be too much resource use setting survival to false
        assertEquals(4, test_guy.getMorale());
        assertEquals(false, test_guy.getSurvivalBoolean());

        assertThrows(IllegalArgumentException.class, () -> {
            event_3.triggerEvent(); // This should throw an exception since 
        });

    }

    // Test toString() 
    @Test
    void testToString() {
        String expected = "Event:description='Test Event', morale_effect=10, resources_effect=15.";
        assertEquals(expected, event.toString());
    }
}

