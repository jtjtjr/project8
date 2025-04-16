import java.util.HashMap;
import java.util.List;

/**
 * The Shop class represents a shop in the game where players can buy items.
 */
public class Shop {
        
    public HashMap<String, ShopItem> shopItems; //contains the items in the shop and their price and description

    public HashMap<String, Integer> receipt; //contains the items bought and their price

    /*
     * Default constructor that initializes the shop with no items.
     */
    public Shop() {
        shopItems = new HashMap<String, ShopItem>();
        receipt = new HashMap<String, Integer>();
    }

    /**
     * Constructor that initializes the shop with a list of items.
     * 
     * @param shopItems The list of items to be added to the shop.
     */
    public Shop(List<ShopItem> shopItems) {
        this.shopItems = new HashMap<String, ShopItem>();
        this.receipt = new HashMap<String, Integer>();
        for (ShopItem item : shopItems) {
            this.shopItems.put(item.getName(), item);
        }
    }

    /**
     * Adds an item and its price to the shop
     * 
     * @param item The item to be added.
     * @param price The price of the item.
     */
    public void addShopItem(ShopItem item) {
        this.shopItems.put(item.getName(), item);   
    }

    /**
     * Removes an item from the shop
     * 
     * @param item The item to be removed.
     */
    public void removeShopItem(String item) {
        this.shopItems.remove(item);
    }

    /**
     *  Display Shop
     */
    public void displayStore() {
        // Define column widths
        final int ITEM_WIDTH = 15;
        final int POINTS_WIDTH = 15;
        final int DESC_WIDTH = 60;
        final int TOTAL_WIDTH = ITEM_WIDTH + POINTS_WIDTH + DESC_WIDTH + 6; // +6 for separators and padding
        
        // Print header
        printCenteredLine("*".repeat(TOTAL_WIDTH));
        printCenteredLine(String.format("* %-" + ITEM_WIDTH + "s* %-" + POINTS_WIDTH + "s* %-" + DESC_WIDTH + "s*", 
            "Item", "Company Points", "Description"));
        printCenteredLine("*".repeat(TOTAL_WIDTH));
    
        // Display all shop items
        for (String key : this.shopItems.keySet()) {
            ShopItem inventory = this.shopItems.get(key);
            printCenteredLine(String.format("* %-" + ITEM_WIDTH + "s* %-" + POINTS_WIDTH + "d* %-" + DESC_WIDTH + "s*", 
                key, inventory.getPrice(), inventory.getDescription()));
            printCenteredLine("*".repeat(TOTAL_WIDTH));
        }
    }
    
    // Helper method to center text
    private void printCenteredLine(String text) {
        int padding = Math.max(0, (96 - text.length()) / 2); // 96 matches your original width
        String centered = " ".repeat(padding) + text;
        System.out.println(centered);
    }

    //below are methods for interacting with the shop

    /**
     * Adds an item to the receipt with the price of the item.
     * 
     * @param item The item to be added.
     * @param numItems The number of items to be added.
     */
    public void addItemToReceipt(String item, int numItems) {
        if (this.shopItems.containsKey(item)) {
            this.receipt.put(item, shopItems.get(item).getPrice());
        } else {
            System.out.println("Item not found in the shop.");
        }
    }

    /**
     * Removes an item from the receipt.
     * 
     * @param item The item to be removed.
     * @param numItems The number of items to be removed.
     */
    public void removeItemFromReceipt(String item, int numItems) {
        // Check if the item exists in the receipt and remove it
        // If the item exists, reduce the quantity or remove it if quantity is zero
        if (this.receipt.containsKey(item)) 
        {
            // Reduce the quantity of the item in the receipt
            this.receipt.replace(item, this.receipt.get(item) - numItems);
            if (this.receipt.get(item) <= 0) {
                this.receipt.remove(item);
            }
        } else {
            // If the item is not in the receipt, print a message
            System.out.println("Item not found in the receipt.");
        }
    }
    
    /*
     * Clears the receipt of anything on it.
     */
    public void clearReceipt() {
        this.receipt.clear();
    }

    /*
     * Prints the contents of the receipt in a formatted manner.
     */
    public void printContentsOfReceipt() {
        // Define column widths
        final int ITEM_WIDTH = 20;
        final int COST_WIDTH = 15;
        final int TOTAL_WIDTH = ITEM_WIDTH + COST_WIDTH + 10; // Extra space for decorations
        
        // Calculate total cost while collecting receipt lines
        int grandTotal = 0;
        StringBuilder receiptLines = new StringBuilder();
        
        // Build header
        receiptLines.append("*".repeat(TOTAL_WIDTH)).append("\n");
        // Manually center "Receipt" by adding spaces
        int titlePadding = (TOTAL_WIDTH - 10) / 2; // 10 accounts for "Receipt" length + 2 for * *
        receiptLines.append("*" + " ".repeat(titlePadding) + "Receipt" + " ".repeat(titlePadding) + "*\n");
        receiptLines.append("*".repeat(TOTAL_WIDTH)).append("\n");
        receiptLines.append(String.format("* %-" + ITEM_WIDTH + "s* %-" + COST_WIDTH + "s*\n", 
            "Item", "Cost (points)"));
        receiptLines.append("*".repeat(TOTAL_WIDTH)).append("\n");
    
        // Process each item in receipt
        for (String key : this.receipt.keySet()) {
            int quantity = this.receipt.get(key);
            int unitPrice = this.shopItems.get(key).getPrice();
            int totalCost = quantity * unitPrice;
            grandTotal += totalCost;
            
            receiptLines.append(String.format("* %-" + ITEM_WIDTH + "s* %-" + COST_WIDTH + "d*\n", 
                key + " (x" + quantity + ")", totalCost));
        }
    
        // Add total
        receiptLines.append("*".repeat(TOTAL_WIDTH)).append("\n");
        receiptLines.append(String.format("* %-" + ITEM_WIDTH + "s* %-" + COST_WIDTH + "d*\n", 
            "GRAND TOTAL", grandTotal));
        receiptLines.append("*".repeat(TOTAL_WIDTH)).append("\n");
    
        // Print centered receipt
        String[] lines = receiptLines.toString().split("\n");
        for (String line : lines) {
            int padding = Math.max(0, (80 - line.length()) / 2); // Center in 80-char width
            System.out.println(" ".repeat(padding) + line);
        }
    }

    /**
     * Returns the total cost of the items in the receipt.
     */
    public int getTotalReceiptCost()
    {
        int grandTotal = 0;
        for (String key : this.receipt.keySet()) {
            int quantity = this.receipt.get(key);
            int unitPrice = this.shopItems.get(key).getPrice();
            int totalCost = quantity * unitPrice;
            grandTotal += totalCost;
        }
        return grandTotal;
    }
    
    /**
     * Return totalNumber of ResourceItems on your list
     */
    public int getResourceShopItems() {
        int totalResources = 0;
        for (String key : this.shopItems.keySet()) {
            //check if its a resource item
            if(this.shopItems.get(key).getType().equals("resources")) {
                totalResources += this.shopItems.get(key).getQuantity();
            }
        }

        //returns the total number of resources in the shop
        return totalResources;
    }

    /**
     * Returns the total number of moraleItems on your list
     */
    public int getMoraleShopItems() {
        int totalMorale = 0;
        for (String key : this.shopItems.keySet()) {
            //check if its a morale item
            if(this.shopItems.get(key).getType().equals("morale")) {
                totalMorale += this.shopItems.get(key).getQuantity();
            }
        }

        //returns the total number of morale items in the shop
        return totalMorale;
    }

    /**
     * Returns the total number of crewItems on your list
     */
    public int getCrewShopItems() {
        int totalCrew = 0;
        for (String key : this.shopItems.keySet()) {
            //check if its a crew item
            if(this.shopItems.get(key).getType().equals("crew")) {
                totalCrew += this.shopItems.get(key).getQuantity();
            }
        }

        //returns the total number of crew items in the shop
        return totalCrew;
    }

}
