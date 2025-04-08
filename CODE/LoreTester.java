import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.List;

public class LoreTester {

    @Test
    void testLoreGetting() {
        //set an example planet name to test
        String planetName = "Bucephalus";
        String lore = LoreLoader.getLoreByPlanetName(planetName);

        // Check if the lore is not null and matches the expected value
        assertNotNull(lore, "Lore should not be null");

        // Example expected lore for Bucephalus
        String expectedLore = "A yellow gas giant, Bucephalus hosts the Greasy Bolt Diner, a pirate-run platform. Jessa \"\"One-Eye\"\" Korran serves Bolt Burgers—synth-meat fried in oil—and guards salvaged purifiers. Storms jolt the sails, and deals sour without a blaster.";
        assertEquals(expectedLore, lore, "Lore should match expected value");
    }

    @Test
    void testGetNonExistentLore() {
        //set an example planet name to test
        String planetName = "NonExistentPlanet";
        String lore = LoreLoader.getLoreByPlanetName(planetName);

        // Check if the lore is null for a non-existent planet
        assertNull(lore, "Lore should be null for non-existent planet");
    }
}
