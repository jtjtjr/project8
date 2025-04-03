import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
        return loadPlanets(false); // Default: easy mode
    }

    /**
     * Parses the planets.csv file and returns a list of Planet objects.
     * @param hardMode whether to enable hard mode event injection
     */
    public static List<Planet> loadPlanets(boolean hardMode) {
        Map<String, Planet> planetTemplateMap = new HashMap<>();
        List<Planet> outputPath = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(PLANET_CSV_FILE))) {
            String line;
            br.readLine(); // Skip header

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length < 10) {
                    System.out.println("Skipping invalid line: " + line);
                    continue;
                }

                String name = data[0].trim();
                String affiliation = data[1].trim();
                int healthPerDay = Integer.parseInt(data[2].trim());
                int crewPerDay = Integer.parseInt(data[3].trim());
                int dangerLevel = Integer.parseInt(data[4].trim());
                int atmosphere = Integer.parseInt(data[6].trim());

                List<String> amenities = new ArrayList<>();
                if (!data[5].trim().equalsIgnoreCase("null")) {
                    amenities.add(data[5].trim());
                }

                // Only store the template; we'll clone per path
                planetTemplateMap.put(name, new Planet(name, affiliation, healthPerDay, crewPerDay, dangerLevel, amenities, atmosphere, hardMode));
            }

            // Define the hardcoded easy and hard paths (no overlaps)
            String[] easySequence = {
                "Fiador X", "Bucephalus", "Norman's Rock", "Atlas Station", "Ezekiel's Salvation", "Cerberus XVII"
            };

            String[] hardSequence = {
                "Mu 6", "Orion's Bane", "Technon 9", "Astros Militarum", "Unknown J76G432", "Cerberus XVII"
            };

            String[] sequence = hardMode ? hardSequence : easySequence;

            Planet prev = null;
            for (int i = 0; i < sequence.length; i++) {
                String name = sequence[i];
                Planet base = planetTemplateMap.get(name);

                if (base == null) {
                    System.out.println("Warning: Planet not found in CSV: " + name);
                    continue;
                }

                // Clone planet so easy/hard lists use separate nodes
                Planet clone = new Planet(
                    base.getName(),
                    base.getAffiliation(),
                    base.getHealthPerDay(),
                    base.getCrewPerDay(),
                    base.getDangerLevel(),
                    base.getAmenities(),
                    base.getAtmosphere(),
                    hardMode
                );

                if (i == 0) clone.setStartingPlanet();
                if (i == sequence.length - 1) clone.setLastPlanet();

                if (prev != null) {
                    prev.setNextPlanet(clone);
                }

                outputPath.add(clone);
                prev = clone;
            }

        } catch (IOException e) {
            System.out.println("Error reading planets CSV: " + e.getMessage());
        }

        return outputPath;
    }
}
