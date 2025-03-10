// package project; // fix this with whole project

import java.util.Scanner;

/*
 * This is the GameLoop where the whole game is run from
 */
public class GameLoop
{
    /*
     * main loop where entire game runs
     */
    public static void main(String[] args) {

        //Initialize scanner to be used by all programs
        Scanner sc = new Scanner(System.in);

        //run the intro slide
        Frontend.introSlide(sc);

        boolean gameOver = false;

        while(!gameOver)
        {
            //get input from the user
            String input = Frontend.inputUser(sc);

            if(input.equals("end"))
            {
                gameOver = true;
            }
        }
        
        // This is the main game loop
        Frontend.gameEnd(gameOver);

        sc.close();
    }
}