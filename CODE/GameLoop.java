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
            if (input.equals("end")) {
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