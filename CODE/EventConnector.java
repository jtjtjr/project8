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
            String eventQuery = "SELECT * FROM Event ";

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
            
            
            ////NOTE
           // This is a makeshift way to run tests stince JUNIT is not working

            int total_tests_1 = 4;
            int total_tests_2 = 4;
            int test1_passes = 0;
            String failures_test1 = "";
            String failures_test2 = "";
            int test2_passes = 0;

            ////////////////////////////////////////////
            //Example usage
            // Test 1 of ID =1  
            //Test the ID and eventName
            if(events.get(0).getEventID()==1 && events.get(0).getEventName().equals("Ion Array Failure")){
                test1_passes++;
            }
            else{
                failures_test1 = failures_test1 + "Failed EventID or the Name of the event was wrong\n";
            }

            //Test 2 check the description
            if( events.get(0).getDescription().equals("*You hear a loud scrape and the sound of sparks.*Shit...* You hear the high voltage from a transformer. *The Ion Array must have failed.")){
                test1_passes++;
            }
            else{
                failures_test1 = failures_test1 + "Failed the desciption check\n";
            }

            //Test 3 check the resources
            if(events.get(0).getResourcesEffect()==5){
                test1_passes++;
            }
            else{
                failures_test1 = failures_test1 + "Failed to get the right resources\n";
            }

            //Test 4 check the morale
            if(events.get(0).getMoraleEffect()==3 ){
                test1_passes++;
            }
            else{
                failures_test1 = failures_test1 + "Failed to get the right morale\n";
            }

            if (total_tests_1 == test1_passes){
                System.out.println("Test for the first have passed!");
            }
            else{
                System.out.println("Here are the Failures: "+ failures_test1);
            }


            //////////////////////////////////////////
            // Test 1 of ID =2  
            //Test the ID and eventName
            if(events.get(1).getEventID()==2 && events.get(1).getEventName().equals("Solar Panel Malfunction")){
                test2_passes++;
            }
            else{
                failures_test2 = failures_test2 + "Failed EventID or the Name of the event was wrong\n";
            }

            //Test 2 check the description
            if( events.get(1).getDescription().equals("You notice a strange flickering on the solar panels, and power seems unstable. The crew is getting worried about energy loss.")){
                test2_passes++;
            }
            else{
                failures_test2 = failures_test2 + "Failed the desciption check\n";
            }

            //Test 3 check the resources
            if(events.get(1).getResourcesEffect()==4){
                test2_passes++;
            }
            else{
                failures_test2 = failures_test2  + "Failed to get the right resources\n";
            }

            //Test 4 check the morale
            if(events.get(1).getMoraleEffect()==2 ){
                test2_passes++;
            }
            else{
                failures_test2 = failures_test2 + "Failed to get the right morale\n";
            }

            if (total_tests_2 == test2_passes){
                System.out.println("Test for the second item in the db have passed!");
            }
            else{
                System.out.println("Here are the Failures: "+ failures_test2);
            }


           

            //end the connection
            dbCxn.close();
            // System.out.println(events.size());
            // System.out.println(events.get(0));
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}