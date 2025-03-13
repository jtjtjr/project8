import java.io.*;
import java.util.*;

/**
 * This class loads planets from a CSV file and creates a linear planet path.
 */
public class PlanetLoader {
    private static final String PLANET_CSV_FILE = "planets.csv";

    /**
     * Parses the planets.csv file and returns a list of Planet objects.
     */
    public static List<Planet> loadPlanets() {
        List<Planet> planets = new ArrayList<>();
        Map<String, Planet> planetMap = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(PLANET_CSV_FILE))) {
            String line;
            br.readLine(); // Skip header line

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length < 10) continue; // Ensure valid data

                String name = data[0].replaceAll("\"", "");
                String affiliation = data[1].replaceAll("\"", "");
                int healthPerDay = Integer.parseInt(data[2]);
                int crewPerDay = Integer.parseInt(data[3]);
                int dangerLevel = Integer.parseInt(data[4]);
                int atmosphere = Integer.parseInt(data[5]);
                boolean firstPlanet = Boolean.parseBoolean(data[6]);
                boolean lastPlanet = Boolean.parseBoolean(data[7]);
                String nextPlanetName = data[8].replaceAll("\"", "").equals("null") ? null : data[8].replaceAll("\"", "");

                Planet planet = new Planet(name, affiliation, healthPerDay, crewPerDay, dangerLevel, new ArrayList<>(), atmosphere);
                planetMap.put(name, planet);
                planets.add(planet);

                if (firstPlanet) {
                    planet.setStartingPlanet();
                }
                if (lastPlanet) {
                    planet.setLastPlanet();
                }
            }

            // Link planets
            for (Planet planet : planets) {
                String nextPlanetName = planet.getNextPlanet() != null ? planet.getNextPlanet().getName() : null;
                if (nextPlanetName != null && planetMap.containsKey(nextPlanetName)) {
                    planet.setNextPlanet(planetMap.get(nextPlanetName));
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading planets CSV: " + e.getMessage());
        }

        return planets;
    }
}