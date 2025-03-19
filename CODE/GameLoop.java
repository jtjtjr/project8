// package project; // fix this with whole project

import java.util.Scanner;

/*
 * This is the GameLoop where the whole game is run from
 */
public class GameLoop {
    private final Scanner scanner;

    // Constructor for dependency injection
    public GameLoop(Scanner scanner) {
        this.scanner = scanner;
    }

    // Default constructor for main
    public GameLoop() {
        this(new Scanner(System.in));
    }

    public void runGame() {
        Frontend.introSlide(scanner);
        boolean gameOver = false;

        while (!gameOver) {
            String input = Frontend.inputUser(scanner);

            //added a player status check to see if they are dead
            if (input.equals("end") || !Frontend.playerStatus()) {
                gameOver = true;
            }
            
        }

        Frontend.gameEnd(gameOver);
    }

    public static void main(String[] args) {
        GameLoop game = new GameLoop();
        game.runGame();
    }
}