// package project; // fix this with whole project

import java.util.Scanner;

/*
 * This is the Frontend
 */
public class Frontend {
    public static void wait(int milliseconds)
    {
        try {
            Thread.sleep(milliseconds); // simulated delay
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void displayTextSlowly(String text)
    {
        for(char c : text.toCharArray())
        {
            System.out.print(c);
            wait(30);
        }
    }

    public static void displayTextSlowly(String text, int milliseconds)
    {
        displayTextSlowly(text);
        wait(milliseconds);
    }
    
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

        resourceStore(scanner);

        

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
     * Prompt User for input
     */
    public static String inputUser(Scanner scanner) {
    System.out.print("Input> ");
    String userInput = scanner.nextLine();
    System.out.println("Command Received: " + userInput);
    return userInput;
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

    public static int[] resourceStore(Scanner scanner) {
        int startingPoints = 10000;

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

        System.err.println("Remaining Balance:" + startingPoints);

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

        startingPoints = startingPoints - crewNum * 100;

        System.err.println("Remaining Balance:" + startingPoints);
        
        if (startingPoints < 0) {
            System.out.println("THE COMPANY DOES NOT APPROVE OF OVERDRAFTS");
            gameEnd(false);
            return new int[]{0, 0, 0};
        }

        displayTextSlowly("Excellent!!!\n\n", 1000);

        //initial morale
        displayTextSlowly("On a scale of 1 to 100, how do you want your crew to feel about this journey (remember it costs you!): ");
        int initialMorale = -1; 

        while (initialMorale < 1 || initialMorale > 100)
        {
            initialMorale = parseInt(scanner, "morale");

            if(initialMorale < 30) displayTextSlowly("Oh come on you've got to have more than that! ... \n", 1000);
            if(initialMorale > 70) displayTextSlowly("Woahhh OK, let's dial it down a little! ... \n", 1000);            
        }

        startingPoints = startingPoints - initialMorale * 40;
        
        System.err.println("Remaining Balance:" + startingPoints);

        if (startingPoints < 0) {
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

        startingPoints = startingPoints - initialResourceCount * 10;

        System.err.println("Remaining Balance:" + startingPoints);

        if (startingPoints < 0) {
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
}
