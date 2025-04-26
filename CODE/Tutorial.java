/*
 * 
 */

import java.util.Random;
import java.util.Scanner;

/**
 * This class handles all the tutorials seen throughout the game
 */
public class Tutorial {
    /*
     * tutorialOperator handles the requests from the user on which tutorial to display
     */
    public static void tutorialOperator(Scanner s) {
        Frontend.displayTextSlowly("Please select the tutorial you want:\n - Intro\n - Shop\n - Event\n - Planet\n - Ship\n - Exit\n\n\n Input>");
        String userInput = s.nextLine();
        if (userInput.equalsIgnoreCase("intro")) {
            frontendUXElements.newSlideScene();
            introTutorial(s);
        } else if (userInput.equalsIgnoreCase("Shop")) {
            frontendUXElements.newSlideScene();
            shopTutorial(s);
        } else if (userInput.equalsIgnoreCase("event")) {
            frontendUXElements.newSlideScene();
            eventTutorial(s, -1);
        } else if (userInput.equalsIgnoreCase("planet")) {
            frontendUXElements.newSlideScene();
            planetTutorial(s);
        } else if (userInput.equalsIgnoreCase("ship")) {
            frontendUXElements.newSlideScene();
            shipTutorial(s);
        } else if (userInput.equalsIgnoreCase("exit")) {
            return;
        } else {
            Frontend.displayTextSlowly("Didnt get that, please, come again...");
            tutorialOperator(s);
        }
    }
    
    /*
     * this tutorial covers how the game works
     */
    public static void introTutorial(Scanner s) {
        String welcomeCaptian = "Welcome New Captian to Galactic Trail! (Presented by Company Corp LLC) \n \n----------------------------------------------------------------------------------------\n\n";
        Frontend.displayTextSlowly(welcomeCaptian);
        Frontend.displayTextSlowly("You are working for Company Corp LLC, a vast Intragalactic conglomerate \n\n");
        Frontend.displayTextSlowly("However, you Captian, will have to haul freight from point A to point B, simple really \n\n");
        Frontend.displayTextSlowly("You might have to deal with some hiccups in the meantime \n \n");
        Frontend.next(s);
    }

    /*
     * This tutorial covers shop functionality
     */
    public static void shopTutorial(Scanner s) {
        Frontend.displayTextSlowly("Throughout the game you will encounter several types of shops\n\n");
        Frontend.displayTextSlowly("Though they all tend to fit a specific format, the first shop you will encounter is the Company Store:\n\n");
        frontendUXElements.shopArtCompany();
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
        
        Frontend.displayTextSlowly("Continue? ");
        Frontend.next(s);

        Frontend.displayTextSlowly("Or it might have a more detailed shop head such as:\n\n");
        Frontend.wait(5000);
        frontendUXElements.shopArtEzekialsSalvation();
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
        Frontend.next(s);
        Frontend.displayTextSlowly("If you wish to purchase an item do the following steps:\n\n");

        Frontend.displayTextSlowly("If you are on a planet enter \'shop\' to access the shop menu\n\n");

        Frontend.displayTextSlowly("Once in the shop enter \'buy <item_name>\' to purchase something\n\n");

        Frontend.displayTextSlowly("type \'remove <item_name> <count>\' to remove an item from your list\n\n");

        Frontend.displayTextSlowly("type \'review\' to see what is currently on your receipt\n\n");
        
        Frontend.displayTextSlowly("type \'complete purchase\' to finalize your purchase\n\n");

        Frontend.displayTextSlowly("type \'exit\' to leave the shop\n\n");

        Frontend.displayTextSlowly("type \'help\' if you need to review these steps again\n\n");

        Frontend.next(s);
    }

    /*
     * This tutorial covers events
     */
    public static void eventTutorial(Scanner s, int crewNum) {
        frontendUXElements.newSlideScene();

        Frontend.displayTextSlowly("Between planets you and your crew will experience events. \n\n\nTypcally these events will cost you morale and resources\n\n\n");
        
        Frontend.displayTextSlowly("Good news, killing... I mean SACRIFICING your crew will boost your resources and sometimes offer a boost in morale, making up for some events that take away your precious resources\n\n\n");
        Frontend.displayTextSlowly("Do you wish to sacrifice your crewmates? This includes yourself but you should try not do that \n\n\n");

        if (crewNum == -1) {
            Frontend.next(s);

            return;
        }

        Frontend.displayTextSlowly(crewNum + " Crew Members in your Crew - if this is zero crew then you can only sacrifice yourself\n\n\ntype [yes] to sacrifice one of the dumb buggers and [no] spare their worthless skin\n\n\n");

        Frontend.next(s);
        frontendUXElements.newSlideScene();
    }

    /*
     * This tutorial covers planets
     */
    public static void planetTutorial(Scanner s) {
        Frontend.displayTextSlowly("Throughout the game, you will visit many planets, some hostile, some less hostile\n\n\nMost of the time, planets will not kill you. Planets can have amenities, denoted in game with []\n\n\nThe two amenities that you can have are Market (called by using the command *shop*) and Finish (called by using the command *end*)\n\n\nFinish will allow you to end the game with a score, if called before this you will lose\n\n\nShop will allow you to purchase various items that the planet you are on has to offer, some planets have better items than others", Frontend.textTimer);
        Frontend.next(s);
    }

    /*
     * This tutorial covers the ship
     */
    public static void shipTutorial (Scanner s) {
        String shipType = Frontend.cur_player.getShip().toString();
        String shipName = Frontend.cur_player.getShipName();
        
        Random random = new Random();
        int randomNumber = random.nextInt(100);
        String a = "";
        if (randomNumber % 2 == 0) {
            a = "unfortunate";
        } else {
            a = "good enough";
        }
        String line1 = "You, being a space cargo hauler and all, have a space freighter, these come in several flavors, yours is " + shipType + " with the " + a + " name of " + shipName + ".";
        Frontend.displayTextSlowly(line1, Frontend.textTimer);
        Frontend.next(s);
    }
}
