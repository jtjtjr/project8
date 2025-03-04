import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.beans.Transient;
import java.util.ArrayList;

/**
 * Unit tests for the Day class.
 */
public class DayTester {
    
    private Day testDay;
    private ArrayList<Event> eventList;
    private Event testEvent; // unused for now, use when events are integrated into Day

    @BeforeEach
    void setUp() {
        eventList = new ArrayList<>();
        testEvent = new Event("Test Event", 10, 15, null);
        eventList.add(testEvent);
        testDay = new Day(1, eventList);
    }

    @Test
    void testDayInitialization() {
        assertEquals(1, testDay.getDayNumber());
        assertTrue(testDay.getSurvivalBoolean());
    }

    // test nextDay() method and survival mechanics
    @Test
    void testSurvival() {
        assertTrue(testDay.getSurvivalBoolean());
        testDay.nextDay();
        assertEquals(2, testDay.getDayNumber());
        assertTrue(testDay.getSurvivalBoolean());

        //TODO: test dying when survival mechanics are fully implemented
    }
}
