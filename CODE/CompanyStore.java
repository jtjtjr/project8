import java.util.Scanner;

/*
 * This Class displays the initial store and handles tutorials associated with it
 */
public class CompanyStore {
    
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
     * input gathering function that gets information from user (this will now work for the first planet only at the beginning as its a little bit two specific)
     * 
     * @return - player attributes in a int[] format
     */
    public static int[] StoreFrontCompany(Scanner scanner, int currentPoints, Boolean firstPass) {
        if (firstPass) {
            displayTextSlowly("Do you need a tutorial for the shop - press y for yes: ");
            
            if (scanner.nextLine().equalsIgnoreCase("y")) {
                frontendUXElements.newSlideScene();
                tutorial.shopTutorial(scanner);
            }
        }
        
        int startingPoints = 8000;

        frontendUXElements.newSlideScene();

        Boolean userApproved = false;
        int crewNum = -1;
        int initialMorale = -1;
        int initialResourceCount = -1;

        currentPoints = startingPoints;

        while (!userApproved) {
            frontendUXElements.companyStore();

            if (currentPoints != startingPoints) {
                currentPoints = startingPoints;
            }

            displayTextSlowly("\n\nTotal Balance:" + currentPoints + "\n\n");
    
            //get the number of crew
            displayTextSlowly("How large is your crew?\n\n");
            displayTextSlowly("Crew: ");
             
    
            while (crewNum < 1 || crewNum > 10)
            {
                crewNum = parseInt(scanner, "crew size");
    
                if(crewNum < 1) 
                {
                    displayTextSlowly("Really ... ", 1000);
                    displayTextSlowly("you need SOMEONE to manage the crew!!! \n\nCrew: ", 1000);
                }
                if(crewNum > 10)
                {
                    displayTextSlowly("Hmm ... ", 1000);     
                    displayTextSlowly("That\'s a bit too many mouths to feed. \n\nCrew: ", 1000);   
                }       
            }
    
            currentPoints = currentPoints - crewNum * 100;
    
            displayTextSlowly("\nRemaining Balance:" + currentPoints + "\n\n");
            
            if (currentPoints < 0) {
                displayTextSlowly("THE COMPANY DOES NOT APPROVE OF OVERDRAFTS");
                wait(6000);
                StoreFrontCompany(scanner, startingPoints, false);
            }
    
            displayTextSlowly("Excellent!!!\n\n", 1000);
    
            //initial morale
            displayTextSlowly("On a scale of 1 to 100, how do you want your crew to feel about this journey (remember it costs you!)\n\n");
            displayTextSlowly("Morale: ");
    
            while (initialMorale < 0 || initialMorale > 100)
            {
                initialMorale = parseInt(scanner, "morale");
    
                if(initialMorale < 0) displayTextSlowly("Oh come on you've got to have more than that! ... \n\nMorale: ", 1000);
                if(initialMorale > 100) displayTextSlowly("Woahhh OK, let's dial it down a little! ... \n\nMorale: ", 1000);            
            }
    
            currentPoints = currentPoints - initialMorale * 40;
            
            displayTextSlowly("\nRemaining Balance:" + currentPoints + "\n\n");
    
            if (currentPoints < 0) {
                displayTextSlowly("THE COMPANY DOES NOT APPROVE OF OVERDRAFTS");
                wait(6000);
                StoreFrontCompany(scanner, startingPoints, false);
            }
    
            displayTextSlowly("Excellent!!!\n\n", 1000);
           
            //resources
            displayTextSlowly("How many resources are you planning to fill your ship with, just so you know more resources means less cargo space... \n", 1000);
            displayTextSlowly("Remaining Cargo Space: 1000 Units (1 Resource takes 1 unit!)\n\n");
            displayTextSlowly("Resources: ");
            initialResourceCount = parseInt(scanner, "initial resouce count");
    
            while (initialResourceCount < 50 || initialResourceCount > 1000) { 
                
                initialResourceCount = parseInt(scanner, "resourses");
    
                if(initialResourceCount < 50) {
                    displayTextSlowly("You will be unable to make it to the next planet ...\n\nResources: ");
                }
                if(initialResourceCount > 1000) {
                    displayTextSlowly("You ran out of cargo capacity, dumbass ...\n\nResources: ");
                }
            }
    
            currentPoints = currentPoints - initialResourceCount * 10;
    
            displayTextSlowly("\nRemaining Balance:" + currentPoints + "\n\n");
    
            if (currentPoints < 0) {
                displayTextSlowly("THE COMPANY DOES NOT APPROVE OF OVERDRAFTS");
                wait(6000);
                StoreFrontCompany(scanner, startingPoints, false);
            }
    
            displayTextSlowly("Excellent!!!\n\n", 1000);

            Boolean userInputLoopBoolean = false;
            while (!userInputLoopBoolean) {
                displayTextSlowly("You have selected the items: Crew: " + crewNum + " Morale: " + initialMorale + " Resources: " + initialResourceCount + "\n\nIs this what you would like to continue with these items [y]es/[n]o: "); 
                String userInput = scanner.nextLine();
                if (userInput.equalsIgnoreCase("y")) {
                    int[] resourcesAmount = {crewNum, initialMorale, initialResourceCount, currentPoints};
                    userInputLoopBoolean = true;
                    frontendUXElements.newSlideScene();
                    return resourcesAmount;
                } else if (userInput.equalsIgnoreCase("n")) {
                    StoreFrontCompany(scanner, currentPoints, false);
                } else {
                    displayTextSlowly("Didn't get that. . .\n\n");
                }
            }
        }



        /////////// IMPLEMENT SHIP LOGIC HERE ////////////
        
        int[] resourcesAmount = {crewNum, initialMorale, initialResourceCount, currentPoints};

        wait(2000);

        displayTextSlowly("Player Data: Crew: " + crewNum + " Morale: " + initialMorale + " Resources: " + initialResourceCount + " Remaining balance: " + currentPoints + "\n");

        displayTextSlowly("Launching\n                                         \n                                         \n3                                         \n2                                         \n1                                         \n\n");

        displayTextSlowly("Upload Complete\n");

        return resourcesAmount; 
    }
}
