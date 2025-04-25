import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * The EventCSV class handles retrieving Event data from a CSV file using an index.
 */
public class EventCSV {

    private Player player;
    private final String csvFilePath = "events.csv"; // Update the path if needed

    /**
     * Constructs an EventCSV object with the given player.
     *
     * @param player the Player object associated with this EventCSV instance
     */
    public EventCSV(Player player) {
        this.player = player;
    }

    /**
     * Retrieves an Event from the CSV file based on the event ID.
     *
     * @param id the event ID to retrieve
     * @return the Event object corresponding to the given event ID, or null if not found
     */
    public Event getEventFromCSV(int id) {
        String line;
        String cvsSplitBy = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            // Skip header
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(cvsSplitBy, -1); // -1 to include trailing empty strings

                int eventId = Integer.parseInt(parts[0]);
                if (eventId == id) {
                    String eventName = parts[1];
                    String eventDescription = parts[2];
                    int morale = Integer.parseInt(parts[3]);
                    int resources = Integer.parseInt(parts[4]);

                    return new Event(eventDescription, eventId, eventName, morale, resources, player);
                }
            }

        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }

        return null;
    }
}
