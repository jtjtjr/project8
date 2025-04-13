public class ShopItem {
    private String planetName;
    private String name;
    private String type;
    private int price;
    private int quantity;
    private String description;

    /*
     * Constructor to initialize a ShopItem object with the given parameters.
     */
    public ShopItem(String planetName, String name, String type, int price, int quantity, String description) {
        this.planetName = planetName;
        this.name = name;
        this.type = type;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
    }

    // Getters
    public String getPlanetName() {
        return planetName;
    }

    public String getName() {
        return name;
    }
    
    public String getType() {
        return type;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void decrimentQuantity() {
        if (this.quantity <= 0) {
            System.out.println("No more items available.");
        }
        this.quantity--;
    }

    public String getDescription() {
        return description;
    }
}
