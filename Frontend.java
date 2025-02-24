// package project; // fix this with whole project

import java.util.Scanner;

/*
 * This is the Frontend
 */
public class Frontend {
    /*
    * Intro for game
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
        
        System.out.print("USERNAME: ");
        String username = scanner.nextLine();
        
        System.out.println("Welcome, Captain " + username + "! \n");
        /////// Intro block
        
        // THIS IS NOT FINISHED, JUST SIMULATED FOR TESTING///////////////////////////////////////
        boolean connected = false;
        int attempts = 0;
        while (!connected && attempts < 5) {  // Timeout for if it attempst more tha 5 times
            System.out.println("Connecting to servers ...");
            try {
                Thread.sleep(1000); // simulated delay
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
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
    public static String inputUser(Scanner scanner)
    {
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
}
