import java.util.HashMap;

/**
 * The Shop class represents a shop in the game where players can buy items.
 */
public class Shop {
    
    public HashMap<String, Integer> shopItems; //contains the items in the shop and their prices

    /*
     * Default constructor that initializes the shop with no items.
     */
    public Shop() {
        shopItems = new HashMap<String, Integer>();
    }

    /**
     * Constructor that initializes the shop based on a scaffold of what you might see thorughout the game
     * 
     * @param crewPrice The price of crew.
     * @param moralePrice The price of morale.
     * @param resourcesPrice The price of resources.
     */
    public Shop(int crewPrice, int moralePrice, int resourcesPrice) {
        shopItems = new HashMap<String, Integer>();
        shopItems.put("crew", crewPrice);
        shopItems.put("morale", moralePrice);
        shopItems.put("resources", resourcesPrice);
    }

    /*
     * Retrieves the items in the shop and their prices.
     */
    public HashMap<String, Integer> getShopItems() {
        return shopItems;
    }

    /**
     * Adds an item and its price to the shop
     * 
     * @param item The item to be added.
     * @param price The price of the item.
     */
    public void addShopItem(String item, int price) {
        this.shopItems.put(item, price);   
    }

    /**
     * Removes an item from the shop
     * 
     * @param item The item to be removed.
     */
    public void removeShopItem(String item) {
        this.shopItems.remove(item);
    }
}
