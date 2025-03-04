// package project; // fix this with whole project

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 * This is the Frontend
 */
public class Frontend {
    /*
    * Intro for game
    */

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
        /// 
        /// 
        /* Now we do the setup */
        System.out.print("USERNAME: ");
        String username = scanner.nextLine();        
        displayTextSlowly("Welcome, Captain " + username + "! \n\n");

        //get the ship name
        displayTextSlowly("What will you call your ship: ");
        String shipName = scanner.nextLine(); 
        displayTextSlowly("Excellent Name!!!\n\n", 1000);
        
        //get the number of crew
        displayTextSlowly("How large is your crew: ");
        int crewNum = -1; 

        while (crewNum < 1 || crewNum > 15)
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

        displayTextSlowly("Excellent!!!\n\n", 1000);

        //initial morale
        displayTextSlowly("On a scale of 1 to 100, how do you feel about this journey!\n");
        int initialMorale = -1; 

        while (initialMorale < 1 || initialMorale > 100)
        {
            initialMorale = parseInt(scanner, "morale");

            if(initialMorale < 30) displayTextSlowly("Oh come on you've got to have more than that! ... \n", 1000);
            if(initialMorale > 70) displayTextSlowly("Woahhh OK, let's dial it down a little! ... \n", 1000);            
        }

        displayTextSlowly("Excellent!!!\n\n", 1000);
       
        //resources
        displayTextSlowly("How many resources are you planning to fill your ship with ... \n", 1000);
        displayTextSlowly("Oh ...\n", 1000);
        displayTextSlowly("And just so you know ...\n", 1000);
        displayTextSlowly("more resources means less cargo space ... \n", 1000);
        int initalResourceCount = parseInt(scanner, "initial resouce count");

        //set up the player count
        //List<Event> events = new ArrayList<Event>(); //theoretically there should be a way to fill this with events
        //Player player = new Player(0, events, crewNum, initialMorale, initalResourceCount, shipName);

        wait(2000);
        displayTextSlowly("Upload Complete\n");

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

    // Check if user typed "planet" (case insensitive)
    if (userInput.equalsIgnoreCase("planet")) {
        queryPlanets();
    }

    return userInput;
    }

    private static final String URL = "jdbc:mysql://localhost:3306/planet_database";
    private static final String USER = "myuser";
    private static final String PASSWORD = "mypassword";
    
    public static void queryPlanets() {
        try {
            // Load MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to MySQL
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Planet");

            while (rs.next()) {
                System.out.println("Planet: " + rs.getString("name") + " | Affiliation: " + rs.getString("affiliation"));
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Database connection error!");
            e.printStackTrace();
        }
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
