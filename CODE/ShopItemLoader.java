import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * This class loads store items from a CSV file and creates a list of ShopItem objects.
 */
public class ShopItemLoader {

    // Path to the CSV file containing store items
    private static final String STORE_ITEM_CSV_FILE = "shopItems.csv";

    /**
     * Parses the storeItems.csv file and returns a list of StoreItem objects.
     */
    public static List<ShopItem> loadShopItems() {
        List<ShopItem> ShopItems = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(STORE_ITEM_CSV_FILE))) {
            String line;
            br.readLine(); // Skip header line

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length < 5) {
                    System.out.println("Skipping invalid line: " + line);
                    continue;
                }

                // Parse CSV data
                String planetName = data[0].trim();
                String type = data[1].trim();
                String itemName = data[2].trim();
                int price = Integer.parseInt(data[3].trim());
                int quantity = Integer.parseInt(data[4].trim());
                String description = data[5].trim();

                // Create store item and store in map
                ShopItem ShopItem = new ShopItem(planetName, itemName, type, price, quantity, description);
                ShopItems.add(ShopItem);
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }

        return ShopItems;
    }

    public static List<ShopItem> getShopItemsForPlanet(String planetName) {
        List<ShopItem> ShopItems = loadShopItems();
        List<ShopItem> filteredShopItems = new ArrayList<>();

        for (ShopItem item : ShopItems) {
            if (item.getPlanetName().equalsIgnoreCase(planetName)) {
                filteredShopItems.add(item);
            }
        }

        return filteredShopItems;  
    }
}
