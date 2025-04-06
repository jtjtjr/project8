import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;

/**
 * The EventSQL class handles retrieving Event data from an SQL database using an index chosen at random from Frontend.
 */
public class EventSQL {


    private String command;
    private Player player;

    /**
     * Constructs an EventSQL object with the given player.
     *
     * @param player the Player object associated with this EventSQL instance
     */
    public EventSQL(Player player) {
        command = "";
        this.player = player;
    }

    /**
     * Retrieves an Event from the SQL database based on the event ID.
     *
     * @param i the event ID to retrieve
     * @return the Event object corresponding to the given event ID, or null if not found
     */
    public Event getEventFromSQL(int i) {
        try {
            Event event = null;

            Class.forName("com.mysql.cj.jdbc.Driver"); 

            // Establish a connection to the database - from Devtech 2
            // Make sure to have a terminal up for this
            Connection dbCxn = DriverManager.getConnection(
                "jdbc:mysql://host.docker.internal:60000/Events", "root", "1"
            );

            String eventQuery = "SELECT * FROM Event WHERE event_id =" + i + ";";

            // System.out.println(eventQuery);
            Statement selectFromEvent = dbCxn.createStatement();
            ResultSet rsEvent = selectFromEvent.executeQuery(eventQuery);

            // Store event objects
            // go through the result set and retrieve attributes
            while (rsEvent.next()) {
                int eventId = rsEvent.getInt("event_id");
                String eventName = rsEvent.getString("event_name");
                String eventDescription = rsEvent.getString("event_description");
                int morale = rsEvent.getInt("morale");
                int resources = rsEvent.getInt("resources");

                // public Event(String description, int event_id, String event_name, int morale_effect, int resources_effect, Player player)
                event = new Event(eventDescription, eventId, eventName, morale, resources, player);
            }

            // end the connection
            dbCxn.close();
            return event;
        } catch (Exception e) {
            System.out.println(e);
        }

        return null;
    }
}
