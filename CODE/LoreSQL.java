import java.sql.*;

/**
 * The LoreSQL class handles retrieving Lore data from an SQL database using a planet name
 */
public class LoreSQL {

   /**
     * Constructs an LoreSQL object with the given player.
     *
     * @param player the Player object associated with this EventSQL instance
     */
    public LoreSQL(Player player) {
        command = "";
        this.player = player;
    }

    /**
     * Retrieves aa Lore from the SQL database based on the planet name
     *
     * @param name the name of the planet to receive lore for
     * @return the Lore object corresponding to the given planet name, or null if not found
     */
    public String getLoreFromSQL(String planetName) {
        try {
            String planetLore = null;

            Class.forName("com.mysql.cj.jdbc.Driver"); 

            // Establish a connection to the database - from Devtech 2
            // Make sure to have a terminal up for this
            Connection dbCxn = DriverManager.getConnection(
                "jdbc:mysql://host.docker.internal:60000/PlanetLore", "root", "1"
            );

            String loreQuery = "SELECT * FROM Event PlanetLore event_id =" + planetName + ";";

            Statement selectFromEvent = dbCxn.createStatement();
            ResultSet rsEvent = selectFromEvent.executeQuery(loreQuery);

            // Store event objects
            // go through the result set and retrieve attributes
            while (rsEvent.next()) {
                planetLore = rsEvent.getString("planet_lore");
            }

            // end the connection
            dbCxn.close();
            return planetLore;
        } catch (Exception e) {
            System.out.println(e);
        }

        return null;
    }
}
