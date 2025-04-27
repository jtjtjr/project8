import java.util.ArrayList;
import java.util.List;

public class SageTester {
    public static void main(String[] args) {
        List<Planet> allPlanets = List.of(
            new Planet("Fiador X", "The Company", 0, 0, 0, new ArrayList<>(), 80),
            new Planet("Bucephalus", "None", 0, 0, 0, new ArrayList<>(), 80),
            new Planet("Norman's Rock", "Atlas Station", 0, 0, 1, new ArrayList<>(), 80),
            new Planet("Atlas Station", "Atlas Station", 0, 0, 0, new ArrayList<>(), 80),
            new Planet("Ezekiel's Salvation", "None", 0, 0, 0, new ArrayList<>(), 50),
            new Planet("Mu 6", "Astros Confederation", 1, 1, 1, new ArrayList<>(), 80),
            new Planet("Orion's Bane", "None", 2, 2, 2, new ArrayList<>(), 25),
            new Planet("Technon 9", "Astros Confederation", 0, 1, 1, new ArrayList<>(), 90),
            new Planet("Astros Militarum", "Astros Confederation", 0, 1, 0, new ArrayList<>(), 90),
            new Planet("Unknown J76G432", "None", 2, 2, 2, new ArrayList<>(), 80),
            new Planet("Cerberus XVII", "The Company", 0, 0, 0, new ArrayList<>(), 80)
        );

        Player player = new Player("TestCaptain", 3, 50, 100, "SS Voyager");

        for (Planet planet : allPlanets) {
            System.out.println("--- " + planet.getName() + " ---\n");
            System.out.println(Sage.speak(planet, player));
            System.out.println("\n========================================\n");
        }
    }
}
