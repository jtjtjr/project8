import java.util.List;

public class PlanetIntegrationTest {
    public static void main(String[] args) {
        System.out.println("=== EASY MODE PATH ===");
        List<Planet> easyPlanets = PlanetLoader.loadPlanets(false);
        for (Planet planet : easyPlanets) {
            System.out.println("Planet: " + planet.getName());
            System.out.println("Next Planet: " + (planet.getNextPlanet() != null ? planet.getNextPlanet().getName() : "None"));
            System.out.println("Is Start Planet: " + planet.isStartPlanet());
            System.out.println("Is Last Planet: " + planet.isLastPlanet());
            System.out.println("Hard Mode?: " + isHardMode(planet));
            System.out.println("-----------------------------");
        }

        System.out.println("\n=== HARD MODE PATH ===");
        List<Planet> hardPlanets = PlanetLoader.loadPlanets(true);
        for (Planet planet : hardPlanets) {
            System.out.println("Planet: " + planet.getName());
            System.out.println("Next Planet: " + (planet.getNextPlanet() != null ? planet.getNextPlanet().getName() : "None"));
            System.out.println("Is Start Planet: " + planet.isStartPlanet());
            System.out.println("Is Last Planet: " + planet.isLastPlanet());
            System.out.println("Hard Mode?: " + isHardMode(planet));
            System.out.println("-----------------------------");
        }
    }

    // Since hardMode is private with no getter, we check via trigger
    private static String isHardMode(Planet planet) {
        try {
            java.lang.reflect.Field field = Planet.class.getDeclaredField("hardMode");
            field.setAccessible(true);
            return String.valueOf(field.getBoolean(planet));
        } catch (Exception e) {
            return "Unknown";
        }
    }
}
