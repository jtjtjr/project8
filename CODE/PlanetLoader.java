import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
                int atmosphere = Integer.parseInt(data[6].trim());
                boolean firstPlanet = data[7].trim().equalsIgnoreCase("TRUE");
                boolean lastPlanet = data[8].trim().equalsIgnoreCase("TRUE");

                List<String> amenities = new ArrayList<>();
                if (!data[5].trim().equalsIgnoreCase("null")) {
                    amenities.add(data[5].trim());
                }

                // Create planet and store in map
                Planet planet = new Planet(name, affiliation, healthPerDay, crewPerDay, dangerLevel, amenities, atmosphere);
                if (firstPlanet) planet.setStartingPlanet();
                if (lastPlanet) planet.setLastPlanet();

                planetMap.put(name, planet);
                planets.add(planet);
            }
        
            // Now link planets. Reason we do this twice is because the map is not populated with the next planet in the first read
            br.close(); // Close and re-open for safety
            BufferedReader br2 = new BufferedReader(new FileReader(PLANET_CSV_FILE));
            br2.readLine(); // Skip header

            while ((line = br2.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length < 10) continue;

                String name = data[0].trim();
                String nextPlanetName = data[9].trim().equalsIgnoreCase("null") ? null : data[9].trim();

                if (nextPlanetName != null && planetMap.containsKey(nextPlanetName)) {
                    planetMap.get(name).setNextPlanet(planetMap.get(nextPlanetName));
                }
            }
            br2.close();
        } catch (IOException e) {
            System.out.println("Error reading planets CSV: " + e.getMessage());
        }
        return planets;
    }
}