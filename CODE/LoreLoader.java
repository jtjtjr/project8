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
     */
    public static List<String> loadLore() {
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

        return loreList;
    }
}
