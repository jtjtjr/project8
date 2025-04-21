import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.List;

/*
 * This class is a JUnit test class for testing the Shop class
 * It includes tests for loading shop items and checking if the shop functions correctly as expected based on user commands.
 */
public class ShopTester {

    //Below are tests for the shopLoader class
    @Test
    void testGettingShopItems() {
        //set an example planet name to test
        String planetName = "Bucephalus";
        List<ShopItem> shopItems = ShopItemLoader.getShopItemsForPlanet(planetName);

        // Check if the shop items are not null and contain items for the specified planet
        assertNotNull(shopItems, "Shop items should not be null");

        //make sure it has the correct number of items
        assertEquals(3, shopItems.size(), "Shop items size should be 3 for planet " + planetName);

        //make sure the first item is correct
        ShopItem firstItem = shopItems.get(0);
        assertTrue(firstItem.getName().equals("Bolt Burger Delux"), "First item should be called Bolt Burger Delux");
        assertTrue(firstItem.getType().equals("morale"), "This is a morale type object");
        assertTrue(firstItem.getPrice() == 150, "This item should cost 100 credits");

        //make sure the second item is correct
        ShopItem secondItem = shopItems.get(1);
        assertTrue(secondItem.getName().equals("Salvaged Purifiar Core"), "Second item should be called Salvaged Purifiar Core");
        assertTrue(secondItem.getType().equals("resources"), "This is an resources type object");
        assertTrue(secondItem.getPrice() == 200, "This item should cost 200 credits");

        //make sure the third item is correct
        ShopItem thirdItem = shopItems.get(2);
        assertTrue(thirdItem.getName().equals("Pirate Recruitment Voucher"), "Third item should be called Pirate Recuitment Voucher");
        assertTrue(thirdItem.getType().equals("crew"), "This is a crew type object");
        assertTrue(thirdItem.getPrice() == 300, "This item should cost 300 credits");

        //this is the same for all planets so we can generalize that it works for all of them

    }

    @Test
    void testGettingShopItemsFromNonExistentPlanet() {
        //set an example planet name to test
        String planetName = "NonExistentPlanet";
        List<ShopItem> shopItems = ShopItemLoader.getShopItemsForPlanet(planetName);

        // Check if the lore is null for a non-existent planet
        assertNull(shopItems, "Shop items should be null for a non-existent planet");
    }

    //Below are tests for the shop class
    @Test
    void testShopCreation() {

        String planetName = "Bucephalus";
        List<ShopItem> shopItems = ShopItemLoader.getShopItemsForPlanet(planetName);
        
        // Create a new shop instance
        Shop shop = new Shop();

        foreach (ShopItem item : shopItems) {
            // Add items to the shop
            shop.addShopItem(item.getName(), item.getType(), item.getPrice());
        }

        // Check if the shop is created successfully
        
    }
        

        // Load the shop items for the pla
}
