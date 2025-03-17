import java.io.*;
import java.util.ArrayList; // Import for ArrayList
import java.util.HashMap;   // Import for HashMap
import java.util.List;      // Import for List
import java.util.Map;       // Import for Map

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
                if (data.length < 10) {
                    System.out.println("Skipping invalid line: " + line);
                    continue;
                }

                // Parse CSV data
                String name = data[0].trim();
                String affiliation = data[1].trim();
                int healthPerDay = Integer.parseInt(data[2].trim());
                int crewPerDay = Integer.parseInt(data[3].trim());
                int dangerLevel = Integer.parseInt(data[4].trim());
                String amenitiesString = data[5].trim(); // Currently have "null" for amenities
                List<String> amenities = new ArrayList<>();
                if (!amenitiesString.equalsIgnoreCase("null")) {
                    amenities.add(amenitiesString); // Add non-null amenities
                }
                int atmosphere = Integer.parseInt(data[6].trim());
                boolean firstPlanet = data[7].trim().equalsIgnoreCase("TRUE");
                boolean lastPlanet = data[8].trim().equalsIgnoreCase("TRUE");
                String nextPlanetName = data[9].trim().equalsIgnoreCase("null") ? null : data[9].trim();

                // Create Planet object
                Planet planet = new Planet(name, affiliation, healthPerDay, crewPerDay, dangerLevel, amenities, atmosphere);
                planetMap.put(name, planet);
                planets.add(planet);

                // Set starting and ending planets
                if (firstPlanet) {
                    planet.setStartingPlanet();
                }
                if (lastPlanet) {
                    planet.setLastPlanet();
                }

                // Link planets
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