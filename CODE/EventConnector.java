import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/*
 * This Connects the Backend to the SQL server on the VM
 * JUNIT failed when trying to test so will add that for later
 */
class EventConnector {
    public static void main(String args[]) {
        try {
            //Establish a connection to the database - from Devtech 2
            //Make sure to have a terminal up for this
            Connection dbCxn = DriverManager.getConnection(
                "jdbc:mysql://localhost:60000/Events", "root", "1"
            );

            //THIS IS AN EXAMPLE COMMAND
            //Make sure to get values by their ID, we only populate events if they are needed
            //not all events from the database need to be initilized
            String eventQuery = "SELECT * FROM Event";

            //System.out.println(eventQuery);
        
            Statement selectFromEvent = dbCxn.createStatement();
            ResultSet rsEvent = selectFromEvent.executeQuery(eventQuery);
            //Store event objects
            List<Event> events = new ArrayList<>();

            //go through the result set and retrieve attributes
            while (rsEvent.next()) {
                int eventId = rsEvent.getInt("event_id");
                String eventName = rsEvent.getString("event_name");
                String eventDescription = rsEvent.getString("event_description");
                int morale = rsEvent.getInt("morale");
                int resources = rsEvent.getInt("resources");
                
                // public Event(String description, int event_id, String event_name, int morale_effect, int resources_effect, Player player) 
                
                Event event = new Event( eventDescription, eventId, eventName, morale, resources, null);
                events.add(event);
            }

            //Example usage
            if(events.get(0).getEventID()==1 && events.get(0).getEventName().equals("Ion Array Failure")){
                System.out.println("Test 1 completed");
            }

            //end the connection
            dbCxn.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}