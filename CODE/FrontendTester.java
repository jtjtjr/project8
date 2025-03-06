import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

/**
 * Unit tests for the Frontend class.
 */
public class FrontendTester {

    private Frontend testFrontend;

    @BeforeEach
    void setUp() {
        testFrontend = new Frontend();
    }

    @Test
    void testWait() {
        testFrontend.wait(0);
    }

    @Test
    void testIntroSlide() {
        // can't do much right now since frontend is not fully implemented, finish later
        // introSlide asks for username, ship name, crew number, morale, and resources (in order)
        testFrontend.introSlide(new Scanner("user\nshipname\n5\n50\n50"));
    }
}