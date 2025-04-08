import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class loads lore from a CSV file and creates a linear lore path.
 */
public class LoreLoader {
    private static final String LORE_CSV_FILE = "lore.csv";

    /**
     * Parses the lore.csv file and returns a list of Lore objects.
     * 
     * @return List of lore strings
     */
    public static Map<String, String> loadLore() {
        List<String> loreList = new ArrayList<>();
        Map<String, String> loreMap = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(LORE_CSV_FILE))) {
            String line;
            br.readLine(); // Skip header line

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length < 2) {
                    System.out.println("Skipping invalid line: " + line);
                    continue;
                }

                // Parse CSV data
                String name = data[0].trim();
                String description = data[1].trim();

                // Create lore and store in map
                loreMap.put(name, description);
                loreList.add(description);
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }

        return loreMap; // Return the map of lore
    }

    /**
     * Given the planets that we currently have visited, filter the lore list to only include those planets.
     * 
     * @param visitedPlanets List of planet indices that have been visited
     * @return List of lore strings corresponding to visited planets
     */
    public static Map<String, String> getFilteredLoreList(List<String> visitedPlanets) {
        Map<String, String> filteredLoreList = new HashMap<>(); // Use a map to store filtered lore
        Map<String, String> loreList = loadLore();

        for (int i = 0; i < visitedPlanets.size(); i++) {
            String planetName= visitedPlanets.get(0); // Convert char to int
            if(visitedPlanets.get(i).equals(planetName)) {
                continue; // Skip if the planet is not visited
            }
            filteredLoreList.put(planetName, loreList.get(i)); // Add lore for the visited planet
            
        }

        return filteredLoreList;
    }

    /**
     * Get individual planet lore by planet name.
     */
    public static String getLoreByPlanetName(String planetName) {
        Map<String, String> loreList = loadLore();
        for (Map.Entry<String, String> entry : loreList.entrySet()) {
            if (entry.getKey().equalsIgnoreCase(planetName)) {
                return entry.getValue(); // Return the lore for the given planet name
            }
        }
       
        return null; // If no lore found for the given planet name, return null or an empty string
    }
}
