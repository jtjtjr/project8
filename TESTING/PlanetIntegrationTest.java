import java.util.List;
import java.util.Map;

public class PlanetIntegrationTest {
    public static void main(String[] args) {
        Map<String, List<Planet>> paths = PlanetLoader.loadEasyAndHardPaths();

        System.out.println("=== EASY MODE PATH ===");
        printPath(paths.get("easy"));

        System.out.println("\n=== HARD MODE PATH ===");
        printPath(paths.get("hard"));
    }

    private static void printPath(List<Planet> path) {
        if (path == null || path.isEmpty()) {
            System.out.println("No planets in this path.");
            return;
        }

        for (Planet planet : path) {
            System.out.println("Planet: " + planet.getName());
            System.out.println("Next Planet: " + (planet.getNextPlanet() != null ? planet.getNextPlanet().getName() : "None"));
            System.out.println("Is Start Planet: " + planet.isStartPlanet());
            System.out.println("Is Last Planet: " + planet.isLastPlanet());
            System.out.println("-----------------------------");
        }

        // Extra check: first and last planet match
        String first = path.get(0).getName();
        String last = path.get(path.size() - 1).getName();
        System.out.println("Start = " + first + " | End = " + last);
    }
}