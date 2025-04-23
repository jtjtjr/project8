// package project; // fix this with whole project

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

/*
 * This is the Frontend
 */
public class Frontend {
    static Player cur_player = null;
    static Planet currentPlanet = null;
    static List<Planet> planets = null;
    static List<Planet> visitedPlanets = new ArrayList<>(); 
    static int currentPoints = 10000;
    static int textTimer = 30;
    static String[] crewMatesArray = null;
    static boolean gameOver = false;
    static int cargo = -1;
    static boolean devToggle = false;
    
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
            wait(textTimer);
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
    
    public static void inputAsk() {
        System.out.print("\n\nInput> ");
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
                displayTextSlowly("Didn't quite get that, whats your " + property + "?\n", textTimer);
            }
        }

        return num;
    }

    /*
     * this method waits for a user to page to the next information
     */
    public static void next(Scanner s) {
        Frontend.displayTextSlowly("\nPress enter to continue>", textTimer);
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
        displayTextSlowly("\n\nHow fast would you like main story:\n\t[1] I want the senic experience\n\t[2] I want a normal Game\n\t[3] I'm a pro and can play fast\n\n\nInput>", textTimer);
        String speedInput = scanner.nextLine();
        boolean validCommand = false;

        while (!validCommand) {
            if (speedInput.contains("1")) {
                textTimer = 30;
                displayTextSlowly("\n\nWe approve of the dedication", textTimer);
                validCommand = true;
            } else if (speedInput.contains("2")) {
                textTimer = 10;
                displayTextSlowly("\n\nRespectable", textTimer);
                validCommand = true;
            } else if (speedInput.contains("3")) {
                textTimer = 0;
                displayTextSlowly("\n\nI hope you are a dev or have played before", textTimer);
                validCommand = true;
            } else {
                displayTextSlowly("\n\nPlease enter a valid command", textTimer);
            }
        }
        next(scanner);
        frontendUXElements.newSlideScene();

        displayTextSlowly("Would you like a tutorial, press y for yes: ", textTimer);
        String input = scanner.nextLine();
        
        if (input.contains("y")) {
            
            frontendUXElements.newSlideScene();
            Tutorial.tutorialOperator(scanner);
        } else if (input.equalsIgnoreCase("dev")) {
            devToggle = true;
            currentPoints = 999999999;
            textTimer = 0;
        }

        frontendUXElements.newSlideScene();

        frontendUXElements.startScreen();

        /* Now we do the setup */
        System.out.print("USERNAME: ");
        String username = scanner.nextLine();   
        displayTextSlowly("Welcome, Captain " + username + "! \n\n", textTimer);

        //get the ship name
        displayTextSlowly("What will you call your ship: ", textTimer);
        String shipName = scanner.nextLine();  
        displayTextSlowly("Excellent Name!!!\n\n", textTimer);

        //Steve - Moved initialization for player class earlier to set ship type when game begins
        cur_player = new Player( username, 0, 0, 0, shipName);
        next(scanner);

        //set ship type - FAST, MED, SLOW
        setUpShipType(scanner);
        next(scanner);

        // Load planets
        Map<String, List<Planet>> paths = PlanetLoader.loadEasyAndHardPaths();
        if (paths.isEmpty()) {
            System.out.println("No paths found. Exiting.");
            return;
        }

        displayTextSlowly("Select your difficulty (1 for Easy, 2 for Hard)", textTimer);
        inputAsk();
        int difficulty = parseInt(scanner, "difficulty");

        if (difficulty == 2) {
            displayTextSlowly("You chose HARD mode. Buckle up...\n\n", textTimer);
            planets = paths.get("hard");
            //cur_player = new Player();
            cur_player.setHardMode(true);
        } else {
            displayTextSlowly("You chose EASY mode. Let's go for a ride...\n\n", textTimer);
            planets = paths.get("easy");
            //cur_player = new Player();
            cur_player.setHardMode(false);
        }

        currentPlanet = planets.get(0);
        cur_player.setCurrentPlanet(currentPlanet);
        visitedPlanets.add(currentPlanet);

        //I recommend testing with Crew: 4 Morale: 50 Resources: 100
        //to pass all events do Crew: 4 Morale: 50 Resources: 105

        cur_player.setCurrentPlanet(currentPlanet);

        frontendUXElements.newSlideScene();

        System.out.println("--------------------------- Legend: CARGO = {} SUPPLY = [] Crew = >:| ------------------------------\n\n\n");
        ShipDisplayer.emptyShipDisplay();
        displayTextSlowly("This is your ship, it will act as your home base\n", textTimer);
        
        next(scanner);
        
        frontendUXElements.newSlideScene();

        displayTextSlowly("*I need to travel to a planet or I am going to run out of resources*\n\n", textTimer);

        next(scanner);

        frontendUXElements.newSlideScene();

        // Display planet info
        displayCurrentPlanet(scanner);

        frontendUXElements.newSlideScene();

        int[] resourcesAmount = CompanyStore.StoreFrontCompany(scanner, currentPoints, true);
        cur_player.setCrewNum(resourcesAmount[0]);
        cur_player.setMorale(resourcesAmount[1]);
        cur_player.setResources(resourcesAmount[2]);
        cur_player.setShipName(shipName);
        cur_player.nextDay();
        cur_player.setMoney(resourcesAmount[3]);
        cargo = 1000 - resourcesAmount[2];
        if (devToggle) {
            cargo = 999999999;
        }

        // Subtract from the number of points its hardcoded
        currentPoints -= resourcesAmount[0] * 100;
        currentPoints -= resourcesAmount[1] * 40;
        currentPoints -= resourcesAmount[2] * 10;

        CrewMates.crewGenerator(cur_player.getCrewNum());

        frontendUXElements.newSlideScene();

        System.out.println("--------------------------- Legend: CARGO = {} SUPPLY = [] Crew = >:| ------------------------------\n\n\n");
        ShipDisplayer.shipDisplayerBuilder();
        next(scanner);

        //Place your testing for Planet, Event and Player here through METHOD CALL ONLY

        int playerCrewNumber = cur_player.getCrewNum();
        
        displayTextSlowly("Would you like an event tutorial [y]es/[n]?", textTimer);
        inputAsk();
        Boolean validUserInput = false;
        while (!validUserInput) { // event tutorial loop
            String userEventTutorialInput = scanner.nextLine();
            if (userEventTutorialInput.equalsIgnoreCase("y")) {
                validUserInput = true;
                Tutorial.eventTutorial(scanner, playerCrewNumber);
            } else if (!userEventTutorialInput.equalsIgnoreCase("n")) {
                displayTextSlowly("Didnt quite get that, come again...", textTimer);
                inputAsk();
            } else {
                validUserInput = true;
            }
        }
        
        frontendUXElements.newSlideScene();

        runEvents(cur_player, scanner);
    }

    /*
    * This is the Frontend call for the encounter (from the server assumably)
    */
    public static void encounterFrontend(int encounterID) {
        displayTextSlowly("Oh...", textTimer);
        // backendClass.encounterID; // Uncomment when backend is written
    }   

    /**
     * Set ship type, calls on by introslide
     * @param scanner user input passing in
     */
    public static void setUpShipType(Scanner scanner) {
        //Ship Type stuff - SLOW, MED, FAST
        frontendUXElements.shipChooser();
        displayTextSlowly("\n\n\nCurrent Balance: " + currentPoints, textTimer);
        inputAsk();
        String currentShipDisplayer = "";
        Boolean validChoice = false;

        while (!validChoice){
            String shipType = scanner.nextLine();
            if (shipType.equals("1")) {
                displayTextSlowly("\n\nGreat! This is recommended for most people. ", textTimer);
                displayTextSlowly("Loading new ship...\n\n\n", 100 * textTimer);
                next(scanner);
                cur_player.setPace(1);
                displayTextSlowly("New Ship has been set!\n", textTimer);
                currentShipDisplayer += ("SS Driftwing\n\n * Description: Slow pace ship. Travels slow but consumes very little resources per day.");
                validChoice = true;
            } else if (shipType.equals("2")) {
                displayTextSlowly("\n\nGood Choice! A solid balance between speed and resource consumption.", textTimer);
                displayTextSlowly("Loading new ship...\n\n\n", 100 * textTimer);
                next(scanner);
                cur_player.setPace(2);
                displayTextSlowly("New Ship has been set!\n", textTimer);
                currentShipDisplayer += ("SS StarBorne\n\n * Description: Travels average and consumes average resources per day.");
                validChoice = true;
            } else if (shipType.equals("3")) {
                displayTextSlowly("\n\nBold Decision! Speed comes at a price.", textTimer);
                displayTextSlowly("Loading new ship...\n\n\n", 100 * textTimer);
                next(scanner);
                cur_player.setPace(3);
                displayTextSlowly("New Ship has been set!\n", textTimer);
                currentShipDisplayer += ("SS Nova Viper\n\n * Description: Travels fast but consumes a lot of resources per day.");
                validChoice = true;
            } else {
                displayTextSlowly("Not a valid input.\n\n\n", textTimer);
                inputAsk();
            }

        }  

        displayTextSlowly("\n * Ship Name: " + cur_player.getShipName() + "\n\n * Ship Type: " + currentShipDisplayer + "\n\n\n", textTimer);

        next(scanner);
    }

    /*
     * returns whether the current planet has a shop on it or not
     */
    public static boolean planetContainsShop(Planet planet) {

        if(planet == null) {

            if(textTimer == 0) {
                System.out.println("No planet found.");
            } 

            return false;
        }

        // Check if the planet has a shop
        if(textTimer == 0)
        {
            System.out.println("Amenities on " + planet.getName() + ": ");
            for (String p : planet.getAmenities()) 
            {
                System.out.print(p + " ");
            }
            System.out.println();
        }

        return planet.getAmenities().contains("Market");
    }
        

    /**
     * Given a shop object we setup based on the planet
     */
    public static Shop setUpShopOnCurrentPlanet() {
        List<ShopItem> itemsOnCurrPlanet = ShopItemLoader.getShopItemsForPlanet(currentPlanet.getName());

        Shop shop = new Shop(itemsOnCurrPlanet);

        wait(1000);
        return shop;
    }
    
    /**
     * This function opens the shop if the current planet has a shop on it
    */
    public static void openPlanetResourceStore(Scanner scanner) {
        if(planetContainsShop(currentPlanet)) {
            Shop shop = setUpShopOnCurrentPlanet();

            //if shop on planet has an art style, display it here
            if(currentPlanet.getName().equals("Bucephalus")) {
                frontendUXElements.shopArtBrucephalus2();
            } 
            else if (currentPlanet.getName().equals("Ezekiel's Salvation")) {
                frontendUXElements.shopArtEzekialsSalvation();
            }
            else
            {
                //nothing to do here
            }

            displayTextSlowly("\nYou have " + currentPoints + " points to spend.\n\n", textTimer);
            displayTextSlowly("You currently have " + cur_player.getResources() + " resources, " + cur_player.getMorale() + " morale, and " + cur_player.getCrewNum() + " crew members.\n\n", textTimer);

            //display the store and then ask what to buy
            shop.displayStore();
            displayTextSlowly("\n What would you like to buy? \n", textTimer);
            
            String input = "";

            while(true) {
                //show the shop console
                System.out.print("Shop> ");
                input = scanner.nextLine();
                System.out.println();
                
                if(input.equals("exit")) {
                    displayTextSlowly("Goodbye! - you are leaving with: " + currentPoints + " points,"  + cur_player.getResources() + " resources, " + cur_player.getMorale() + " morale, " + cur_player.getCrewNum() + " crew members\n\n", textTimer);
                    break;
                }
                else if(input.equals("help")) {
                    frontendUXElements.availableCommands();
                }
                else if(input.startsWith("buy")) {
                    try {
                        String[] parts = input.trim().split(" ");

                        String item = "";
                        for(int i = 1; i < parts.length - 1; i++) {
                            item += parts[i] + " ";
                        }

                        item = item.trim(); // Remove leading and trailing spaces

                        int count = Integer.parseInt(parts.length - 1 > 2 ? parts[parts.length - 1] : "1");
                        if(shop.shopItems.containsKey(item)) {
                            shop.addItemToReceipt(item, count);
                            displayTextSlowly(item + " added to your list.\n", textTimer);
                        } else {
                            System.out.println("Item not found in shop.");
                        }
                    }
                    catch (NumberFormatException e) {
                        displayTextSlowly("Please enter a valid number for quantity.\n", textTimer);
                    }
                    catch(Exception e)
                    {
                        System.out.println("Invalid input. Please use the format: buy <item name> <quantity>");
                    }
                } 
                else if(input.startsWith("remove")) {
                    try{
                        String[] parts = input.trim().split(" ");

                        String item = "";
                        for(int i = 1; i < parts.length - 1; i++) {
                            item += parts[i] + " ";
                        }

                        item = item.trim(); // Remove leading and trailing spaces

                        int count = Integer.parseInt(parts.length - 1 > 2 ? parts[parts.length - 1] : "1");
                        shop.removeItemFromReceipt(item, count);
                    } 
                    catch (NumberFormatException e) {
                        displayTextSlowly("Please enter a valid number for quantity.\n", textTimer);
                    }
                    catch(Exception e) {
                        displayTextSlowly("Invalid input. Please use the format: remove <item name> <quantity>\n", textTimer);
                    }
                } 
                else if(input.equals("review")) {
                    shop.printContentsOfReceipt();
                } 
                else if(input.equals("complete purchase")) {
                    int totalCost = shop.getTotalReceiptCost();

                    //validate the number of points the player has
                    if(totalCost > cur_player.getMoney()) {
                        System.out.println("You do not have enough points to complete this purchase.");
                        continue;
                    } 

                    //first subtract the number of points from the player
                    cur_player.setMoney(cur_player.getMoney() - totalCost);
                        
                    //add items to inventory and clear the receipt
                    cur_player.addResources(shop.getResourceShopItems());

                    //add morale items to the player
                    cur_player.addMorale(shop.getMoraleShopItems());

                    //add crew items to the player 
                    cur_player.addCrewNum(shop.getCrewShopItems());

                    //clear the receipt as you are theoretically done with it
                    shop.clearReceipt();
                } 
                else if(input.equals("show")) {
                    shop.displayStore();
                } 
                else if(input.equals("help")) {
                    frontendUXElements.availableCommands();
                } else {
                    System.out.println("Command not recognized.");
                }
            }
        } else {
            displayTextSlowly("You are not at a shop, you cannot buy items here!\n\n", textTimer);
        }
        
    }

    /**
     * Runs a random event that affects the player's resources and morale.
     * Player can choose to sacrifice crew members to reduce losses.
     * 
     * @param curr The current player
     * @param scannerEvent Scanner for player input
     * @throws NullPointerException If event loading fails
     */
    @SuppressWarnings({ "java:S1301", "Unused", "java:S1126" })
    public static void runEvents(Player curr, Scanner scannerEvent) {
         
        Random random = new Random(); //We will need to simulate randomness
        int eventNumber = random.nextInt(8) + 1; 
        EventSQL eventgetter = new EventSQL(cur_player);
        //return the event
        Event chosen =  eventgetter.getEventFromSQL(eventNumber);
        if (chosen==null){
            throw new NullPointerException("Issue with getting event by index, chosen returning null\n\n\n");
        }

        frontendUXElements.warning();
       
        displayTextSlowly(chosen.getDescription() + "\n\n\n", textTimer);
                
        if ((curr.getResources()-chosen.getResourcesEffect()>=0) &&(curr.getMorale()-chosen.getMoraleEffect()>=0)){
           // displayTextSlowly("You should have survived and the game should continue \n");
           displayTextSlowly("\n\n\n*You have lost " + chosen.getResourcesEffect() + " resources and " +chosen.getMoraleEffect() +" morale*\n\n\n" , textTimer);

           displayTextSlowly("Now... how will you go about responding to this event...\n\n\n", textTimer);
           next(scannerEvent);

           // System.out.print("Input for crew> ");
           int userChoice = -1; 
           int sacNum = -2;
           while (true) {

               frontendUXElements.sacrifice();
               System.out.print("You currently have " +cur_player.getCrewNum()+ " Crew Members ");
               System.out.print("\n\n\nEnter 'No' for no sacrifice or 'Sacrifice them' to sacrifice.");
               inputAsk();

               String userSacrifice = scannerEvent.nextLine().trim();
               //System.out.println("You chose->" +userSacrifice);
               
               if (userSacrifice.equalsIgnoreCase("no") || userSacrifice.equalsIgnoreCase("sacrifice them")) {
                    if (userSacrifice.equalsIgnoreCase("no")) {
                        Random sacrificeMessage = new Random();
                        int randomValue = sacrificeMessage.nextInt();
                        if (randomValue % 2 == 0) {
                            displayTextSlowly("\n\n\nProbably best that you did that...\n\n\n", textTimer);
                        } else {
                            displayTextSlowly("\n\n\nDamn...\n\n\nthe company has gone soft over the past centuries\n\n\n", textTimer);
                        }
                        next(scannerEvent);
                         break; 
                   } else { //if(userSacrifice.equalsIgnoreCase("sacrifice them"))
                         sacNum = chosen.sacrifice();
                         if (sacNum == -1){
                            displayTextSlowly("\nYou accidently sacrificed yourself?\n\n\n", textTimer);
                            curr.setSurvivalBoolean(false);
                            next(scannerEvent);
                         }
                         else if (sacNum==0){
                            displayTextSlowly("\nResources went up but you killed a good friend among your crew, dropping morale\n\n\n", textTimer);
                            displayTextSlowly(""+ cur_player.getCrewNum() + " Crew Members left in your Crew...\n\n\n", textTimer);
                            next(scannerEvent);
                         }
                         else if (sacNum==1){
                            displayTextSlowly("\nResources went up and you killed an annoying person among your crew, increasing morale\n\n\n", textTimer);
                            displayTextSlowly(""+ cur_player.getCrewNum() + " Crew Members left in your Crew...\n\n\n", textTimer);
                            next(scannerEvent);
                         }
                         else{
                            //System.out.println("Unexpected behavior - error to be added");
                            throw new IllegalStateException("Invalid sacrifice outcome: " + sacNum);
                         }
                         break; 
                   }
                   
               } else {
                   displayTextSlowly("Invalid input. State clearly if you would like to sacrifice them.", textTimer);
                   inputAsk();
                   //scannerEvent.next(); 
               }


           }
            displayTextSlowly("\n", textTimer);
           
        }
        else{
            displayTextSlowly("You should be dying and game terminating \n", textTimer);
        }
        chosen.triggerEvent();

        return;
    }

    // This is the Planet Integration

    /**
     * Displays the current planet's information.
     */
    @SuppressWarnings("Unused")
    public static void displayCurrentPlanet(Scanner s) {
        Planet nextPlanet = currentPlanet.getNextPlanet();
        if (currentPlanet.isStartPlanet()) {
            displayTextSlowly("\nDrifting in deep space... ", textTimer);
            wait(100 * textTimer);
            displayTextSlowly("Navigational systems online. ", textTimer);
            wait(100 * textTimer);
            displayTextSlowly("Locking onto the first planet: " + currentPlanet.getName()+ "\n\n\n", textTimer);
        }
        if (currentPlanet != null) {
            displayTextSlowly("\nYou are traveling to: " + currentPlanet.getName() + "\n\n\n\n", textTimer);
            next(s);
            if (currentPlanet.getName().equalsIgnoreCase("Fiador X")) {
                frontendUXElements.fiadorXdisp();
            } else if (currentPlanet.getName().equalsIgnoreCase("Bucephalus")) {
                frontendUXElements.brucephalusPlanetDisp();
            } else if (currentPlanet.getName().equalsIgnoreCase("Norman's Rock")) {
                frontendUXElements.normansPlanetDisp();
            } else if (currentPlanet.getName().equalsIgnoreCase("Atlas Station")) {
                frontendUXElements.atlasPlanetDisp();
            } else if (currentPlanet.getName().equalsIgnoreCase("Ezekiel's Salvation")) {
                frontendUXElements.ezekielPlanetDisp();
            } else if (currentPlanet.getName().equalsIgnoreCase("Mu 6")) {
                frontendUXElements.mu6PlanetDisp();
            } else if (currentPlanet.getName().equalsIgnoreCase("Orion's Bane")) {
                frontendUXElements.orionsPlanetDisp();
            } else if (currentPlanet.getName().equalsIgnoreCase("Technon 9")) {
                frontendUXElements.technonPlanetDisp();
            } else if (currentPlanet.getName().equalsIgnoreCase("Astros Militarum")) {
                frontendUXElements.astrosPlanetDisp();
            } else if (currentPlanet.getName().equalsIgnoreCase("Unknown J76G432")) {
                frontendUXElements.unknownPlanetDisp();
            } else if (currentPlanet.getName().equalsIgnoreCase("Cerberus XVII")) {
                frontendUXElements.cerberusPlanetDisp();
            }
            
            currentPlanet.displayPlanetInfo();
            next(s);
        } else {
            displayTextSlowly("You are lost in space...", textTimer);
        }
    }

    /**
     * Moves to the next planet.
     */
    public static void travelToNextPlanet(Scanner scanner) {
        if (currentPlanet != null && currentPlanet.getNextPlanet() != null) {
            Planet nextPlanet = currentPlanet.getNextPlanet();
    
            // Special case for first travel (coming from deep space)
            if (nextPlanet.isStartPlanet()) {
                displayTextSlowly("\nDrifting in deep space... ", textTimer);
                wait(1000);
                displayTextSlowly("Navigational systems online. ", textTimer);
                wait(1500);
                displayTextSlowly("Locking onto the first planet: " + currentPlanet.getName(), textTimer);
            } else {
                // Normal departure message for every other travel
                displayTextSlowly("\nPreparing for departure from " + currentPlanet.getName() + "...", textTimer);
                wait(1000);
                displayTextSlowly("Launching...\n\n", textTimer);
            }
            wait(2000);
    
            // Simulate space travel
            displayTextSlowly("\n\n\n\n** Traveling through space **\n\n\n\n\n", textTimer);
            wait(1500);
    
            // Mid-travel event trigger
            runEvents(cur_player, scanner);

            frontendUXElements.newSlideScene();

            next(scanner);

            currentPlanet.triggerRandomEvent(cur_player);
    
            // Check if the player survived the event
            if (!cur_player.getSurvivalBoolean()) {
                displayTextSlowly("\nYou have perished in space...", textTimer);
                return;
            }

            // Randomly trigger a (less consequential) mystery event 30% of the time after main event
            Random rand = new Random();
            if(rand.nextInt(10) < 3) {
                MysteryEvent mysteryEvent = new MysteryEvent(cur_player, rand);
                mysteryEvent.triggerMysteryEvent();
            }
    
            // Now officially change the planet
            currentPlanet = nextPlanet;
            visitedPlanets.add(currentPlanet); // Add the planet to the visited list
            cur_player.setCurrentPlanet(currentPlanet);
    
            // Announce arrival
            displayCurrentPlanet(scanner);
            
        } else if (currentPlanet.isLastPlanet()) {
            displayTextSlowly("\nYou have reached your final destination!", textTimer);
        } else {
            displayTextSlowly("\nNo further planets to travel to.", textTimer);
        }
    }

    /*
     * Handles user input.
     */
    public static void inputUser(Scanner scanner) {
        cur_player.display();
        displayTextSlowly("You and your crew are currently at " + currentPlanet.getName() + " with " + currentPlanet.getAmenities().toString() + " amenities, what would you like to do here?", textTimer);
        
        inputAsk();
        String userInput = scanner.nextLine();

        //gives information about the current planet
        if (userInput.equalsIgnoreCase("planet")) {
            displayCurrentPlanet(scanner);
            next(scanner);
            return;
        } else if (userInput.equalsIgnoreCase("travel")) {
            frontendUXElements.newSlideScene();
            travelToNextPlanet(scanner);
            next(scanner);
            return;
        }
        else if (userInput.equalsIgnoreCase("status")) {
            cur_player.detailedDisplay();
            next(scanner);
            return;
        }
        else if (userInput.equalsIgnoreCase("shop")) {
            openPlanetResourceStore(scanner);
            next(scanner);
            return;
        }
        else if (userInput.equalsIgnoreCase("help")) {
            help();
            next(scanner);
            return;
        }
        else if (userInput.equalsIgnoreCase("crew")) {
            if (crewMatesArray == null) {
                crewMatesArray = CrewMates.crewGenerator(cur_player.getCrewNum());
                CrewMates.crewPrinter(crewMatesArray);
                next(scanner);
                return;
            } else {
                crewMatesArray = CrewMates.crewEqualizer(crewMatesArray, cur_player.getCrewNum());
                CrewMates.crewPrinter(crewMatesArray);
                next(scanner);
                return;
            }
        }
        else if (userInput.equalsIgnoreCase("end")) {
            int score = EndGame.scoreCalculatron(cur_player.getMoney(), cur_player.getCrewNum(), cargo, cur_player.getResources(), cur_player.getMorale(), cur_player.getCurrentPlanet().isLastPlanet());
            
            if (score == -2) {
                displayTextSlowly("\ndo you wish to let the company down(yes or no)?:", textTimer);
                String userinput = scanner.nextLine();
                if (userInput.equalsIgnoreCase("yes")) {
                    next(scanner);
                    frontendUXElements.newSlideScene();
                    displayTextSlowly("you have failed", textTimer);
                    gameOver = true;
                    return;
                }
            } else if (score == -1) {
                next(scanner);
                    frontendUXElements.newSlideScene();
                    displayTextSlowly("you have failed", textTimer);
                    gameOver = true;
                    return;
            } else if (score >= 0) {
                next(scanner);
                frontendUXElements.endScreen();
                next(scanner);
                displayTextSlowly("Your Score was " + score, textTimer);
                next(scanner);
                gameOver = true;
            } else {
                displayTextSlowly("uninteligible, continuing...", textTimer);
                next(scanner);
                return;
            }
        } else if (userInput.equalsIgnoreCase("tutorial")) {
            Tutorial.tutorialOperator(scanner);
            return;
        }
        else if (userInput.equalsIgnoreCase("lore")) {
            //display the planets that we have visited
            displayTextSlowly("These are the planets you have currently visited (type \'exit\' to leave): \n", textTimer);
            for(int i = 0; i < visitedPlanets.size(); i++) {
                displayTextSlowly("\n\t - " + visitedPlanets.get(i).getName() + "\n", textTimer);
            }

            while(true) {
                System.out.print("Lore> ");
                String loreInput = scanner.nextLine();

                if (loreInput.equalsIgnoreCase("exit")) {
                    break;
                }

                String lore = LoreLoader.getLoreByPlanetName(loreInput.trim());
                if (lore != null) {
                    displayTextSlowly(loreInput + " - " + lore + "\n", textTimer);
                } else {
                    displayTextSlowly("Lore not found for " + loreInput + ". Please try again.\n", textTimer);
                }
            }
            next(scanner);
            return;
        }
        else {
            displayTextSlowly("Invalid command. Type 'help' for a list of commands.\n\n\n", textTimer);
            next(scanner);
            return;
        }
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
