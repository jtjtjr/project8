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
    static List<Planet> visitedPlanets = null; 
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
     * this method waits for a user to page to the next information
     */
    public static void next(Scanner s) {
        Frontend.displayTextSlowly("Press enter to continue>");
        s.nextLine();
        frontendUXElements.newSlideScene();
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
        frontendUXElements.newSlideScene();
        displayTextSlowly("Would you like a tutorial, press y for yes: ");
        String input = scanner.nextLine();
        
        if (input.contains("y")) {
            frontendUXElements.newSlideScene();
            tutorial.tutorialOperator(scanner);
        }

        frontendUXElements.newSlideScene();

        frontendUXElements.startScreen();

        /* Now we do the setup */
        System.out.print("USERNAME: ");
        String username = scanner.nextLine();   
        displayTextSlowly("Welcome, Captain " + username + "! \n\n");

        //get the ship name
        displayTextSlowly("What will you call your ship: ");
        String shipName = scanner.nextLine();  
        displayTextSlowly("Excellent Name!!!\n\n", 1000);

        next(scanner);

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
        cur_player = new Player( 0, 0, 0, 0, null);
        cur_player.setCurrentPlanet(currentPlanet);

        frontendUXElements.newSlideScene();

        System.out.println("--------------------------- Legend: CARGO = {} SUPPLY = [] Crew = >:| ------------------------------\n\n\n");
        ShipDisplayer.emptyShipDisplay();
        displayTextSlowly("This is your ship, it will act as your home base\n");
        next(scanner);

        frontendUXElements.newSlideScene();

        // Display planet info
        displayCurrentPlanet();

        frontendUXElements.fiadorXdisp();

        wait(3000);

        displayTextSlowly("*I need to travel to Fiador or I am going to run out of resources*\n\n");

        next(scanner);

        frontendUXElements.newSlideScene();

        int[] resourcesAmount = CompanyStore.StoreFrontCompany(scanner, 0, true); ////////////////////////////CHANGE THIS TO PASS IN PLAYER MONEY
        cur_player.setCrewNum(resourcesAmount[0]);
        cur_player.setMorale(resourcesAmount[1]);
        cur_player.setResources(resourcesAmount[2]);
        cur_player.setShipName(shipName);
        cur_player.nextDay();

        frontendUXElements.newSlideScene();

        System.out.println("--------------------------- Legend: CARGO = {} SUPPLY = [] Crew = >:| ------------------------------\n\n\n");
        ShipDisplayer.shipDisplayerBuilder();
        next(scanner);

        frontendUXElements.newSlideScene();

        //Place your testing for Planet, Event and Player here through METHOD CALL ONLY
        runEvents(cur_player, scanner);
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
     * This is the event integration
     * it does not have the SQL database added yet but just tests multiple types of events and includes the random class
     */
    public static void runEvents(Player curr, Scanner scannerEvent) {
        Random random = new Random(); //We will need to simulate randomness
        int eventNumber = random.nextInt(5) + 1; 
        EventSQL eventgetter = new EventSQL(cur_player);
        //return the event
        Event chosen =  eventgetter.getEventFromSQL(eventNumber);
        if (chosen==null){
            throw new NullPointerException("Issue with getting event by index, chosen returning null");
        }

       
        displayTextSlowly(chosen.getDescription() + '\n');
                
        if ((curr.getResources()-chosen.getResourcesEffect()>=0) &&(curr.getMorale()-chosen.getMoraleEffect()>=0)){
           // displayTextSlowly("You should have survived and the game should continue \n");
           displayTextSlowly("You have lost " + chosen.getResourcesEffect() + " resources and " +chosen.getMoraleEffect() +" morale\n" );
           displayTextSlowly("Good news, killing... I mean SACRIFICING your crew will give your resources and sometimes a boost in morale\n");
           displayTextSlowly("Do you wish to sacrifice your crewmates? This includes yourself but you should try not do that" +'\n');
           displayTextSlowly(cur_player.getCrewNum() + " Crew Members in your Crew - if this is zero crew then you can only sacrifice yourself\npress 1 to sacrifice 0 to not\n");
 
          // System.out.print("Input for crew> ");
           int userChoice = -1; 
           int sacNum = -2;
           while (true) {
               System.out.print("Enter 0 for no sacrifice or 1 to sacrifice: ");
               
               if (scannerEvent.hasNextInt()) {
                   userChoice = scannerEvent.nextInt();
                   
                   if (userChoice == 0 ) {
                        //chosen.sacrifice();
                         break; 
                   } 
                   else if(userChoice == 1){
                         sacNum = chosen.sacrifice();
                         if (sacNum == -1){
                            displayTextSlowly("\nYou accidently sacrificed yourself?\n");
                         }
                         else if (sacNum==0){
                            displayTextSlowly("\nResources went up but you killed a good friend among your crew, dropping morale\n");
                            displayTextSlowly(""+ cur_player.getCrewNum() + " Crew Members left in your Crew\n");
                         }
                         else if (sacNum==1){
                            displayTextSlowly("\nResources went up and you killed an annoying person among your crew, increasing morale\n");
                            displayTextSlowly(""+ cur_player.getCrewNum() + " Crew Members left in your Crew\n");
                         }
                         else{
                            System.out.println("Unexpected behavior - error to be added");
                         }
                         break; 
                   }
                   else {
                       System.out.println("Invalid input. Please enter 0 or 1.");
                   }
               } else {
                   System.out.println("Invalid input. Please enter a number (0 or 1).");
                   scannerEvent.next(); 
               }
           }
            displayTextSlowly("\n");
           
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
            visitedPlanets.add(currentPlanet); // Add the planet to the visited list
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
        }
        else if (userInput.equalsIgnoreCase("help")) {
            help();
        }
        else if (userInput.equalsIgnoreCase("end")) {
            System.out.println("Ending game...");
        } else if (userInput.equalsIgnoreCase("tutorial")) {
            tutorial.tutorialOperator(scanner);
        }
        else if (userInput.equalsIgnoreCase("lore")) {
            //display the planets that we have visited
            displayTextSlowly("These are the planets you have currently visited (type \'exit\' to leave): ");
            for(int i = 0; i < visitedPlanets.size(); i++) {
                displayTextSlowly("\t " + visitedPlanets.get(i).getName() + "\n");
            }

            while(true) {
                System.out.print("Lore> ");
                String loreInput = scanner.nextLine();
                if (loreInput.equalsIgnoreCase("exit")) {
                    break;
                }

                String lore = LoreLoader.getLoreByPlanetName(loreInput);
                if (lore != null) {
                    displayTextSlowly(lore);
                } else {
                    displayTextSlowly("Lore not found for " + loreInput + ". Please try again.");
                }
            }
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

    /**
     * Player staus displayer, probably should be removed
     */
    public static void displayPlayerStatus() {
        System.out.println("Player Status: ");
        System.out.println("Num Crew Left: " + cur_player.getCrewNum());
        System.out.println("Morale: " + cur_player.getMorale());
        System.out.println("Resources: " + cur_player.getResources());
        System.out.println("Current Planet: " + cur_player.getCurrentPlanet().getName());
        System.out.println("Day Number: " + cur_player.getDayNumber());
    }
}
