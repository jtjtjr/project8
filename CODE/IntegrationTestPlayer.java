//Test for Integration

public class IntegrationTestPlayer {
    public static void main(String[] args) {
        System.out.println("Test1" + test1());
        System.out.println("Test2" + test2());
    }

    //Inital constructor
    public static boolean test1() {
        Player test = new Player();
        test.setShipName("testShip");
        test.setCrewNum(4);
        test.setMorale(100);
        test.setResources(100);

        return test.toString().equals("Player{ shipName=testShip, crewNum=4, morale=100, resources=100, dayNumber=1 }");
    }

    //Testing methods
    public static boolean test2() {
        Player test = new Player();
        test.setShipName("testShip");
        test.setCrewNum(4);
        test.setMorale(100);
        test.setResources(100);

        test.removeCrewNum(2);
        test.removeMorale(20);
        test.removeResources(20);
        for (int i=0; i<3; i++) {
            test.incrementDay();
            test.addCrewNum(1);
            test.addMorale(10);
        }

        return test.toString().equals("Player{ shipName=testShip, crewNum=5, morale=100, resources=80, dayNumber=4 }");
    }
}

