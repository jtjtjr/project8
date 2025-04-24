//Test for Display

public class PlayerDisplayTester {

    public static void main(String[] args) {
        //System.out.println("Running Player Display:");
        //display();
        System.out.println("Running Player Description:");
        detailedDescription();
    }

    public static void display() {
        Player test = new Player("TestBob", 5, 50, 500, "TestShip");
        System.out.println("TEST DISPLAY 1: ");
        test.display();
        test.nextDay();
        System.out.println("TEST DISPLAY 2: ");
        test.display();
    }

    public static void detailedDescription() {
        Player test = new Player("TestBob", 5, 50, 500, "TestShip");
        System.out.println("TEST DESCRIPTION 1: ");
        test.detailedDisplay();
        test.nextDay();
        System.out.println("TEST DESCRIPTION 2: ");
        test.detailedDisplay();
    }
}

