/*
 * 
 */

import java.util.Scanner;

/**
 * This class handles all the tutorials seen throughout the game
 */
public class tutorial {
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
        } else if (userInput.equalsIgnoreCase("exit")) {
            return;
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
        Frontend.displayTextSlowly("If you wish to purchase an item you will");
        System.out.println("\n\n\nDAN YOU NEED TO TELL ME HOW YOUR SHOP LOGIC WORKS\n\n\n");
        Frontend.next(s);
    }

}
