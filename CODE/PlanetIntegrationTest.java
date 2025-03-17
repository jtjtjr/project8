import java.util.List;

public class PlanetIntegrationTest {
    public static void main(String[] args) {
        List<Planet> planets = PlanetLoader.loadPlanets();
        for (Planet planet : planets) {
            System.out.println("Planet: " + planet.getName());
            System.out.println("Next Planet: " + (planet.getNextPlanet() != null ? planet.getNextPlanet().getName() : "None"));
            System.out.println("Is Start Planet: " + planet.isStartPlanet());
            System.out.println("Is Last Planet: " + planet.isLastPlanet());
            System.out.println("-----------------------------");
        }
    }
}