// package project; // fix this with whole project

import java.util.List;
import java.util.Random;
import java.util.Scanner;

/*
 * This is the Frontend
 */
public class Frontend {
    static Player cur_player = null;
    static Planet currentPlanet = null;
    static List<Planet> planets = null;
    static int currentPoints = 10000;
    /*
     * This function simplifies the Thread.sleep and adds the special interrupt in case of issues, that way there are no issues in the actaul game
     */
    public static void wait(int milliseconds)
    {
        try {
            Thread.sleep(milliseconds); // simulated delay
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /*
     * This function displays text slowly, simulating a typewriter effect
     */
    public static void displayTextSlowly(String text)
    {
        for(char c : text.toCharArray())
        {
            System.out.print(c);
            wait(30);
        }
    }

    /*
     * Displays text slowly but waits for a certain amount of time after displaying the text
     */
    public static void displayTextSlowly(String text, int milliseconds)
    {
        displayTextSlowly(text);
        wait(milliseconds);
    }
    
    /*
     * This function parses an integer from the scanner, and prompts the user for input until a valid integer is entered
     */
    public static int parseInt(Scanner scanner, String property)
    {
        int num = 0;

        boolean validInput = false;
        while (!validInput) {
            try {
                num = Integer.parseInt(scanner.nextLine());
                validInput = true; // If we get here, input was valid
            } catch (NumberFormatException e) {
                displayTextSlowly("Didn't quite get that, whats your " + property + "?\n");
            }
        }

        return num;
    }

    /*
     * status of player ie are they alive or not?
     * @return The survival boolean
     */
    public static boolean playerStatus() {

        return cur_player.getSurvivalBoolean();

    }

    /*
     * Basic Intro slide for the game
     */
    public static void introSlide(Scanner scanner) {
        frontendUXElements.startScreen();

        /* Now we do the setup */
        System.out.print("USERNAME: ");
        String username = scanner.nextLine();   
        displayTextSlowly("Welcome, Captain " + username + "! \n\n");

        //get the ship name
        displayTextSlowly("What will you call your ship: ");
        String shipName = scanner.nextLine();  
        displayTextSlowly("Excellent Name!!!\n\n", 1000);

        
        int[] resourcesAmount = setUpResourceStore(scanner);

        // Load planets
        planets = PlanetLoader.loadPlanets();
        if (planets.isEmpty()) {
            System.out.println("No planets loaded! Exiting.");
            //return;
        }

        // Set starting planet
        for (Planet planet : planets) {
            if (planet.isStartPlanet()) {
                currentPlanet = planet;
                break;
            }
        }

        //System.out.println("Starting Player-EVent test");
        ////////Intitilize the Player class
        //I recommend testing with Crew: 4 Morale: 50 Resources: 100
        //to pass all events do Crew: 4 Morale: 50 Resources: 105
        cur_player = new Player(1, resourcesAmount[0],resourcesAmount[1], resourcesAmount[2],shipName );
        cur_player.setCurrentPlanet(currentPlanet);

        // Display planet info
        displayCurrentPlanet();

        //Place your testing for Planet, Event and Player here through METHOD CALL ONLY
        runEvents(cur_player);
    }

    /*
    * This is the Frontend call for the encounter (from the server assumably)
    */
    public static void encounterFrontend(int encounterID) {
        displayTextSlowly("Oh...");
        // backendClass.encounterID; // Uncomment when backend is written
    }   

    /*
    * This is the Frontend printout for the end of the game
    */
    public static void gameEnd(boolean atCerberus17) {
        /////// END BLOCK
        if (atCerberus17 == true) {
            frontendUXElements.endScreen();
        } else {
            displayTextSlowly("you died and let the company down.....");
            displayTextSlowly("Well, at least you are replacable");
        }
        /////// END BLOCK
    }

    /*
     * returns whether the current planet has a shop on it or not
     */
    public static boolean planetContainsShop(Planet planet) {
        if(planet == null) {
            return false;
        }

        return planet.getAmenities().contains("Market");
    }
        

    /**
     * Given a shop object we setup based on the planet
     */
    public static void setUpShop(Shop shop) {
        // TODO Auto-generated method stub
        System.out.println("Setting up shop ...");
        wait(1000);
        System.out.println("Shop is ready!");
    }
    /**
     * This function opens the shop if the current planet has a shop on it
    */
    public static void openPlanetResourceStore(Scanner scanner) {
        if(planetContainsShop(currentPlanet)) {
            Shop shop = new Shop();
            setUpShop(shop);

            shop.displayStore();
            displayTextSlowly("\n What would you like to buy? \n", 1000);

            String input = "";

            while(true) {
                input = scanner.nextLine();
                
                if(input.equals("exit")) {
                    displayTextSlowly("Goodbye!");
                    break;
                }
                else if(input.equals("help")) {
                    System.out.println("\nAvailable commands: ");
                    System.out.println("buy <item> <count> - Add a certain number of a particular item to your list");
                    System.out.println("remove <item> <count> - leave the shop.");
                    System.out.println("review - see what is on your list.");
                    System.out.println("complete purchase - buy all the items on your list.");
                    System.out.println("show - show the items available in the shop.");
                    System.out.println("exit - leave shop.\n");
                }
                else if(input.startsWith("buy")) {
                    String[] parts = input.split(" ");
                    if(parts.length == 3) {
                        String item = parts[1];
                        int count = Integer.parseInt(parts[2]);
                        if(shop.shopItems.containsKey(item)) {
                            shop.addItemToReceipt(item, count);
                        } else {
                            System.out.println("Item not found in shop.");
                        }
                    } else {
                        System.out.println("Invalid command format. Use: buy <item> <count>");
                    }
                } 
                else if(input.startsWith("remove")) {
                    String[] parts = input.split(" ");
                    if(parts.length == 3) {
                        String item = parts[1];
                        int count = Integer.parseInt(parts[2]);
                        shop.removeItemFromReceipt(item, count);
                        System.out.println("Removed " + count + " " + item + "(s) from your list.");
                    } else {
                        System.out.println("Invalid command format. Use: remove <item> <count>");
                    }
                } 
                else if(input.equals("review")) {
                    shop.printContentsOfReceipt();
                } 
                else if(input.equals("complete purchase")) {
                    int totalCost = shop.getTotalReceiptCost();

                    //validate the number of points the player has
                    if(totalCost > currentPoints) {
                        System.out.println("You do not have enough points to complete this purchase.");
                        continue;
                    } 

                    //first subtract the number of points from the player
                    currentPoints -= totalCost;
                        
                    //add items to inventory and clear the receipt
                    cur_player.addResources(shop.getShopItemsAsResources());
                    //clear the receipt as you are theoretically done with it
                    shop.clearReceipt();
                } 
                else if(input.equals("show")) {
                    shop.displayStore();
                } 
                else if(input.equals("exit")) {
                    displayTextSlowly("Goodbye!");
                    break;
                } 
                else if(input.equals("help")) {
                    System.out.println("\nAvailable commands: ");
                    System.out.println("buy <item> <count> - Add a certain number of a particular item to your list");
                    System.out.println("remove <item> <count> - leave the shop.");
                    System.out.println("review - see what is on your list.");
                    System.out.println("complete purchase - buy all the items on your list.");
                    System.out.println("show - show the items available in the shop.");
                    System.out.println("exit - leave shop.\n");
                } else {
                    System.out.println("Command not recognized.");
                }
            }
        } else {
            displayTextSlowly("You are not at a shop, you cannot buy items here!");
        }
        
    }

    /*
     * input gathering function that gets information from user (this will now work for the first planet only at the beginning as its a little bit two specific)
     */
    public static int[] setUpResourceStore(Scanner scanner) {

        int startingPoints = 8000;

        System.out.println("*****************************************************************************************************");
        System.out.println(" ____  _   _  ____     ___  _____  __  __  ____   __    _  _  _  _    ___  ____  _____  ____  ____ ");
        System.out.println("(_  _)( )_( )( ___)   / __)(  _  )(  \\/  )(  _ \\ /__\\  ( \\( )( \\/ )  / __)(_  _)(  _  )(  _ \\( ___)");
        System.out.println("  )(   ) _ (  )__)   ( (__  )(_)(  )    (  )___//(__)\\  )  (  \\  /   \\__ \\  )(   )(_)(  )   / )__) ");
        System.out.println(" (__) (_) (_)(____)   \\___)(_____)(_/\\/\\_)(__) (__)(__)(_)/\\_) (__)   (___/ (__) (_____)(_)/_)(____)");
        System.out.println("*****************************************************************************************************");
        System.out.println("*   ATTRIBUTE   *   COMPANY POINTS   *   DESCRIPTION                                                *");
        System.out.println("*****************************************************************************************************");
        System.out.println("*     MORALE    *       40           *   Happy crew members are going to cost you...                *");
        System.out.println("*****************************************************************************************************");
        System.out.println("*     CREW      *       100          *   Bodies cost money!                                         *");
        System.out.println("*****************************************************************************************************");
        System.out.println("*   RESOURCES   *       10           *   Gotta eat, gotta fuel (the first planet requires >50 units)*");
        System.out.println("*****************************************************************************************************");
        System.out.println("*   XM-8900F    *       12000        *   Faster ship, shiny.                                        *");
        System.out.println("*****************************************************************************************************");

        displayTextSlowly("Remaining Balance:" + startingPoints + "\n\n");

        //get the number of crew
        displayTextSlowly("How large is your crew: ");
        int crewNum = -1; 

        while (crewNum < 1 || crewNum > 10)
        {
            crewNum = parseInt(scanner, "crew size");

            if(crewNum < 1) 
            {
                displayTextSlowly("Really ... ", 1000);
                displayTextSlowly("you need SOMEONE to manage the crew!!! \n", 1000);
            }
            if(crewNum > 10)
            {
                displayTextSlowly("Hmm ... ", 1000);     
                displayTextSlowly("That\'s a bit too many mouths to feed. \n", 1000);   
            }       
        }

        currentPoints = currentPoints - crewNum * 100;

        displayTextSlowly("Remaining Balance:" + startingPoints + "\n\n");
        
        if (startingPoints < 0) {
            displayTextSlowly("THE COMPANY DOES NOT APPROVE OF OVERDRAFTS");
            gameEnd(false);
            return new int[]{0, 0, 0};
        }

        displayTextSlowly("Excellent!!!\n\n", 1000);

        //initial morale
        displayTextSlowly("On a scale of 1 to 100, how do you want your crew to feel about this journey (remember it costs you!): ");
        int initialMorale = -1; 

        while (initialMorale < 30 || initialMorale > 70)
        {
            initialMorale = parseInt(scanner, "morale");

            if(initialMorale < 30) displayTextSlowly("Oh come on you've got to have more than that! ... \n", 1000);
            if(initialMorale > 70) displayTextSlowly("Woahhh OK, let's dial it down a little! ... \n", 1000);            
        }

        currentPoints = currentPoints - initialMorale * 40;
        
        displayTextSlowly("Remaining Balance:" + startingPoints + "\n\n");

        if (startingPoints < 0) {
            displayTextSlowly("THE COMPANY DOES NOT APPROVE OF OVERDRAFTS");
            gameEnd(false);
            return new int[]{0, 0, 0};
        }

        displayTextSlowly("Excellent!!!\n\n", 1000);
       
        //resources
        displayTextSlowly("How many resources are you planning to fill your ship with ... \n", 1000);
        displayTextSlowly("Oh ...\n", 1000);
        displayTextSlowly("And just so you know ...\n", 1000);
        displayTextSlowly("more resources means less cargo space ... \n", 1000);
        displayTextSlowly("Remaining Cargo Space: 1000 Units (1 Resource takes 1 unit!)");
        displayTextSlowly("Resources: ");
        int initialResourceCount = parseInt(scanner, "initial resouce count");

        while (initialResourceCount < 50 || initialResourceCount > 1000) { 
            
            initialResourceCount = parseInt(scanner, "resourses");

            if(initialResourceCount < 50) {
                displayTextSlowly("You will be unable to make it to the next planet ...\n");
            }
            if(initialResourceCount > 1000) {
                displayTextSlowly("You ran out of cargo capacity, dumbass ...\n");
            }
        }

        currentPoints = currentPoints - initialResourceCount * 10;

        displayTextSlowly("Remaining Balance:" + startingPoints + "\n\n");

        if (startingPoints < 0) {
            displayTextSlowly("THE COMPANY DOES NOT APPROVE OF OVERDRAFTS");
            gameEnd(false);
            return new int[]{0, 0, 0};
        }

        displayTextSlowly("Excellent!!!\n\n", 1000);

        /////////// IMPLEMENT SHIP LOGIC HERE ////////////

        wait(2000);

        displayTextSlowly("Player Data: Crew: " + crewNum + " Morale: " + initialMorale + " Resources: " + initialResourceCount + "\n");

        displayTextSlowly("Launching\n                                         \n                                         \n3                                         \n2                                         \n1                                         \n\n");

        frontendUXElements.shipArt1();

        displayTextSlowly("Upload Complete\n");
        
        int[] resourcesAmount = {crewNum, initialMorale, initialResourceCount};
        return resourcesAmount;
    }

    /*
     * This is the event integration
     * it does not have the SQL database added yet but just tests multiple types of events and includes the random class
     */
    public static void runEvents(Player curr) {
        Random random = new Random(); //We will need to simulate randomness
        int eventNumber = random.nextInt(5) + 1; //between 1 and 3
        EventSQL eventgetter = new EventSQL(cur_player);
        //return the event
        Event chosen =  eventgetter.getEventFromSQL(eventNumber);
        if (chosen==null){
            throw new NullPointerException("Issue with getting event by index, chosen returning null");
        }

       
        displayTextSlowly(chosen.getDescription());
                
        if ((curr.getResources()-chosen.getResourcesEffect()>=0) &&(curr.getMorale()-chosen.getMoraleEffect()>=0)){
           // displayTextSlowly("You should have survived and the game should continue \n");
           displayTextSlowly("Do you wish to sacrifice your crewmates? This includes yourself but you should not do that");
           displayTextSlowly(cur_player.getCrewNum() + " Crew Members in your Crew - if this is zero you can only sacrifice yourself\n press 1 to sacrifice 0 to not");
           
        }
        else{
            displayTextSlowly("You should be dying and game terminating \n");
        }
        chosen.triggerEvent();

        return;
    }

    // This is the Planet Integration

    /**
     * Displays the current planet's information.
     */
    public static void displayCurrentPlanet() {
        Planet nextPlanet = currentPlanet.getNextPlanet();
        if (currentPlanet.isStartPlanet()) {
            displayTextSlowly("\nDrifting in deep space... ");
            wait(1000);
            displayTextSlowly("Navigational systems online. ");
            wait(1500);
            displayTextSlowly("Locking onto the first planet: " + currentPlanet.getName());
        }
        if (currentPlanet != null) {
            displayTextSlowly("\nYou are traveling to: " + currentPlanet.getName());
            currentPlanet.displayPlanetInfo();
            frontendUXElements.shipArtPlanet1();
        } else {
            displayTextSlowly("You are lost in space...");
        }
    }

    /**
     * Moves to the next planet.
     */
    public static void travelToNextPlanet() {
        if (currentPlanet != null && currentPlanet.getNextPlanet() != null) {
            Planet nextPlanet = currentPlanet.getNextPlanet();
    
            // Special case for first travel (coming from deep space)
            if (currentPlanet.isStartPlanet()) {
                displayTextSlowly("\nDrifting in deep space... ");
                wait(1000);
                displayTextSlowly("Navigational systems online. ");
                wait(1500);
                displayTextSlowly("Locking onto the first planet: " + currentPlanet.getName());
            } else {
                // Normal departure message for every other travel
                displayTextSlowly("\nPreparing for departure from " + currentPlanet.getName() + "...");
                wait(1000);
                displayTextSlowly("Launching...");
            }
            wait(2000);
    
            // Simulate space travel
            displayTextSlowly("\n** Traveling through space **");
            wait(1500);
    
            // Mid-travel event trigger
            displayTextSlowly("\n[WARNING] An event has occurred during travel!");
            currentPlanet.triggerRandomEvent(cur_player);
    
            // Check if the player survived the event
            if (!cur_player.getSurvivalBoolean()) {
                displayTextSlowly("\nYou have perished in space...");
                return;
            }
    
            // Now officially change the planet
            currentPlanet = nextPlanet;
            cur_player.setCurrentPlanet(currentPlanet);
    
            // Announce arrival
            displayTextSlowly("\nYou have safely arrived at " + currentPlanet.getName() + "!");
            displayCurrentPlanet();
            
        } else if (currentPlanet.isLastPlanet()) {
            displayTextSlowly("\nYou have reached your final destination!");
        } else {
            displayTextSlowly("\nNo further planets to travel to.");
        }
    }

    /*
     * Handles user input.
     */
    public static String inputUser(Scanner scanner) {
        System.out.print("Input> ");
        String userInput = scanner.nextLine();

        if (userInput.equalsIgnoreCase("planet")) {
            displayCurrentPlanet();
        } else if (userInput.equalsIgnoreCase("travel")) {
            travelToNextPlanet();
        }
        else if (userInput.equalsIgnoreCase("status")) {
            displayPlayerStatus();
        }
        else if (userInput.equalsIgnoreCase("shop")) {
            setUpResourceStore(scanner);
        }
        else if (userInput.equalsIgnoreCase("help")) {
            help();
        }
        else if (userInput.equalsIgnoreCase("end")) {
            System.out.println("Ending game...");
        } else if (userInput.equalsIgnoreCase("tutorial")) {
            tutorial.tutorialOperator(scanner);
        }
        else {
            displayTextSlowly("Invalid command. Type 'help' for a list of commands.");
        }

        return userInput;
    }

    /*
     * Displays a menu with all the commands that a player can use
     */
    public static void help() {
        frontendUXElements.helpElements();
    }

    public static void displayPlayerStatus() {
        System.out.println("Player Status: ");
        System.out.println("Num Crew Left: " + cur_player.getCrewNum());
        System.out.println("Morale: " + cur_player.getMorale());
        System.out.println("Resources: " + cur_player.getResources());
        System.out.println("Current Planet: " + cur_player.getCurrentPlanet().getName());
        System.out.println("Day Number: " + cur_player.getDayNumber());
    }
}
