// package project; // fix this with whole project

import java.util.Scanner;
import java.util.Random;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        /////// Intro block
        System.out.println("****************************************************");
        System.out.println("*                                                  *");
        System.out.println("*           WELCOME TO GALACTIC TRAIL              *");
        System.out.println("*                                                  *");
        System.out.println("*                                                  *");
        System.out.println("*                                                  *");
        System.out.println("*                                                  *");
        System.out.println("*                                                  *");
        System.out.println("*        Will you make it to Cerberus XVII?        *");
        System.out.println("****************************************************");
        /////// Intro block

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
        cur_player = new Player(1, null, resourcesAmount[0],resourcesAmount[1], resourcesAmount[2],shipName );
        cur_player.setCurrentPlanet(currentPlanet);

        // Display planet info
        displayCurrentPlanet();

        //Place your testing for Planet, Event and Player here through METHOD CALL ONLY
        runEventsIntegrationTest(cur_player);
        
        

        // THIS IS NOT FINISHED, JUST SIMULATED FOR TESTING///////////////////////////////////////
        boolean connected = false;
        int attempts = 0;
        while (!connected && attempts < 5) {  // Timeout for if it attempst more tha 5 times
            System.out.println("Connecting to servers ...");
            wait(1000);
            attempts++;

            // correct on the 3rd attempt
            if (attempts == 3) {
                connected = true;
            }
        }
        ////////////////////////////////////////////////////////////////////////////////////////////

        if (connected) { // connection checker
            System.out.println("Connected!");
        } else {
            System.out.println("Failed to connect. Try again later.");
        }
    }

    /*
    * This is the Frontend call for the encounter (from the server assumably)
    */
    public static void encounterFrontend(int encounterID) {
        System.out.println("Oh...");
        // backendClass.encounterID; // Uncomment when backend is written
    }   

    /*
    * This is the Frontend printout for the end of the game
    */
    public static void gameEnd(boolean atCerberus17) {
        /////// END BLOCK
        if (atCerberus17 == true) {
            System.out.println("****************************************************");
            System.out.println("*                                                  *");
            System.out.println("*        Well Done on making it alive...           *");
            System.out.println("*                                                  *");
            System.out.println("*                                                  *");
            System.out.println("*                                                  *");
            System.out.println("*                                                  *");
            System.out.println("*                                                  *");
            System.out.println("*                                                  *");
            System.out.println("****************************************************");
        } else {
            System.out.println("you died and let the company down.....");
            System.out.println("Well, at least you are replacable");
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
    
    /*
     * TODO: returns a dictionary with resources as keys and their pirces as values
     */
    public static HashMap<String, Integer> shopInventory(Planet planet) {
        if(planet == null) {
            //System.out.println("You are lost in space...");
            return;
        }

        if(!planetContainsShop(planet)) {
            //System.out.println("No shop available on this planet.");
            return;
        }

        //TODO: Find a way to get the shop inventory of the current planet we are on
    }
    
    /*
     * input gathering function that gets information from user (this will now work for the first planet only at the beginning as its a little bit two specific)
     */
    public static int[] setUpResourceStore(Scanner scanner) {

        System.out.println("*****************************************************************************************************");
        System.out.println("  ____  _   _  ____     ___  _____  __  __  ____   __    _  _  _  _    ___  _____  ____  ____  ____  ");
        System.out.println(" (_  _)( )_( )( ___)   / __)(  _  )(  \\/  )(  _ \\ /__\\  ( \\( )( \\/ )  / __)(  _  )(_  _)(  _ \\( ___) ");
        System.out.println("   )(   ) _ (  )__)   ( (__  )(_)(  )    (  )___//(__)\\  )  (  \\  /   \\__ \\ )(_)(   )(   )   / )__)  ");
        System.out.println("  (__) (_) (_)(____)   \\___)(_____)(_/\\/\\_)(__) (__)(__)(_)\\_) (__)   (___/(_____) (__) (_)_)(____)  ");
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

        System.err.println("Remaining Balance:" + currentPoints);

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

        System.err.println("Remaining Balance:" + currentPoints);
        
        if (currentPoints < 0) {
            System.out.println("THE COMPANY DOES NOT APPROVE OF OVERDRAFTS");
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
        
        System.err.println("Remaining Balance:" + currentPoints);

        if (currentPoints < 0) {
            System.out.println("THE COMPANY DOES NOT APPROVE OF OVERDRAFTS");
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

        System.err.println("Remaining Balance:" + currentPoints);

        if (currentPoints < 0) {
            System.out.println("THE COMPANY DOES NOT APPROVE OF OVERDRAFTS");
            gameEnd(false);
            return new int[]{0, 0, 0};
        }

        displayTextSlowly("Excellent!!!\n\n", 1000);

        /////////// IMPLEMENT SHIP LOGIC HERE ////////////

        wait(2000);

        System.err.println("Player Data: Crew: " + crewNum + " Morale: " + initialMorale + " Resources: " + initialResourceCount);
        displayTextSlowly("Upload Complete\n");
        
        int[] resourcesAmount = {crewNum, initialMorale, initialResourceCount};
        return resourcesAmount;
    }

    /*
     * open the resource store for the current planet
     */
    public static int[] standardResourceStore(Scanner scanner)
    {
        if(!planetContainsShop(currentPlanet)) {
            displayTextSlowly("No shop available on this planet.", 800);
        }

        //the idea is that the resource is the key and the price is the value in this dictioanry
        Map<String, Integer> inventory = shopInventory(currentPlanet);

        shopInventory(currentPlanet);
        System.out.println("*****************************************************************************************************");
        System.out.println("*   ATTRIBUTE   *   COMPANY POINTS   *   DESCRIPTION                                                *");
        System.out.println("*****************************************************************************************************");

        //display everything available in the shop
        for(String key : inventory.keySet()) {
            System.out.println("*     " + key + "    *       " + inventory.get(key) + "           *   " + key + " for sale at " + inventory.get(key) + " points.                *");
            System.out.println("*****************************************************************************************************");
        }

        displayTextSlowly("What would you like to purchase today: ");
        String purchase = scanner.nextLine();

        while(!inventory.containsKey(purchase))
        {
            displayTextSlowly("Sorry, we don't have that in stock. \n");
            purchase = scanner.nextLine();
            //if the customer changes their mind
            if(purchase.equals("exit")) {
                break;
            }
        }
        
    }

    /*
     * This is the event integration
     * it does not have the SQL database added yet but just tests multiple types of events and includes the random class
     */
    public static void runEventsIntegrationTest(Player curr) {
        Random random = new Random(); //We will need to simulate randomness
        int eventNumber = random.nextInt(3) + 1; //between 1 and 3

        //this is not the way wll do for the final prototype
        switch (eventNumber) {
            case 1:
                displayTextSlowly("\nEvent 1: Fox's ship hit you while chasing Wolf \n");
                Event event_1 = new Event("Fox's ship hit you while chasing Wolf", 1, "Fox attack" , 4, 99, curr);
                if ((curr.getResources()-event_1.getResourcesEffect()>=0) &&(curr.getMorale()-event_1.getMoraleEffect()>=0)){
                    displayTextSlowly("You should have survived and the game should continue \n");
                }
                else{
                    displayTextSlowly("You should be dying and game terminating \n");
                }
                event_1.triggerEvent();

                return;
            case 2:
                displayTextSlowly("\nEvent 2: Falco Attacked you by accident \n");
                Event event_2 = new Event("Falco Attacked you by accident", 2, "Eagle attack" , 39, 71, curr);
                if ((curr.getResources()-event_2.getResourcesEffect())>=0 &&(curr.getMorale()-event_2.getMoraleEffect())>=0){
                    displayTextSlowly("You should have survived and the game should continue \n");
                }
                else{
                    displayTextSlowly("You should be dying and game terminating \n");
                }
                event_2.triggerEvent();
                return;
            case 3:
                displayTextSlowly("\nEvent 3: Wolf shot at you while fox is chasing him \n");
                Event event_3= new Event("Wolf shot at you while fox is chasing him", 3, "Wolf attack" , 40, 101, curr);
                if ((curr.getResources()-event_3.getResourcesEffect())>=0 &&(curr.getMorale()-event_3.getMoraleEffect()>=0)){
                    displayTextSlowly("You should have survived and the game should continue \n");
                }
                else{
                    displayTextSlowly("You should be dying and game terminating \n");
                }
                
                event_3.triggerEvent();
                
                return;
            default:
                displayTextSlowly("No event occurred. This should not be happening \n");
                break;
        }
    }

    // This is the Planet Integration

    /**
     * Displays the current planet's information.
     */
    public static void displayCurrentPlanet() {
        if (currentPlanet != null) {
            System.out.println("\nYou are currently at: " + currentPlanet.getName());
            currentPlanet.displayPlanetInfo();
        } else {
            System.out.println("You are lost in space...");
        }
    }

    /**
     * Moves to the next planet.
     */
    public static void travelToNextPlanet() {
        if (currentPlanet != null && currentPlanet.getNextPlanet() != null) {
            Planet nextPlanet = currentPlanet.getNextPlanet();
            
            // Announce departure
            System.out.println("\nTraveling to " + nextPlanet.getName() + "...");
            wait(2000);

            // Trigger the event before reaching the new planet
            currentPlanet.triggerRandomEvent(cur_player);

            // Check if the player survived the event
            if (!cur_player.getSurvivalBoolean()) {
                System.out.println("You have died during the journey...");
                return;
            }

            // Now officially change the planet and display its info
            currentPlanet = nextPlanet;
            cur_player.setCurrentPlanet(currentPlanet);
            
            // Announce arrival
            System.out.println("\nYou have arrived at " + currentPlanet.getName() + "!");
            displayCurrentPlanet();
            
        } else if (currentPlanet.isLastPlanet()) {
            System.out.println("You have reached your final destination!");
        } else {
            System.out.println("No further planets to travel to.");
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
        // TODO Auto-generated method stub
        System.out.println("Input Commands: ");
        System.out.println("planet - Displays the current planet's information.");
        System.out.println("shop - open the shopping menu if you are currently on a planet");
        System.out.println("travel - Moves to the next planet.");  
        System.out.println("status - Displays how you are doing at the moment.");    
        System.out.println("end - Ends the game.");
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
