import java.io.*;
import java.util.*;

public class LocationMap implements Map<Integer, Location> {

    private static final String LOCATIONS_FILE_NAME = "locations.txt";
    private static final String DIRECTIONS_FILE_NAME = "directions.txt";

    private static HashMap<Integer, Location> locations = new HashMap<>();

    static {
        FileLogger flog = new FileLogger();
        ConsoleLogger clog = new ConsoleLogger();

        try (BufferedReader reader = new BufferedReader(new FileReader(DIRECTIONS_FILE_NAME))){
            String line;
            while ((line = reader.readLine()) != null) {
                int locationId; String exit; int destination;
                String[] data = line.split(",");
                locationId = Integer.parseInt(data[0]);
                exit = data[1];
                destination = Integer.parseInt(data[2]);
            }
        } catch (IOException e){
            System.out.println("[ERROR] IO Exception...");
            e.printStackTrace();
        } catch (NumberFormatException e){
            System.out.println("[ERROR] Number Exception...");
            e.printStackTrace();
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(LOCATIONS_FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                int idSeparator = line.indexOf(",");
                int locationId = Integer.parseInt(line.substring(0, idSeparator));
                String description = line.substring(idSeparator + 1);
                Map<String, Integer> exits = new HashMap<>();
                Location loc = new Location(locationId, description, exits);
            }
        } catch (IOException e) {
            System.out.println("[ERROR] IO Exception");
            e.printStackTrace();
        } catch (NumberFormatException e){
            System.out.println("[ERROR] Number Exception...");
            e.printStackTrace();
        }

        /**TODO
         * Read from DIRECTIONS_FILE_NAME so that a user can move from A to B, i.e. current location to next location
         * use try-with-resources/catch block for the FileReader
         * extract the 3 elements  on each line: location, direction, destination
         * print all locations, directions and destinations to both console and file
         * check the ExpectedOutput files
         * for each location, create a new location object and add its exit
         */

    }

    @Override
    public int size() { // DONE
        return this.size();
    }

    @Override
    public boolean isEmpty() { // DONE
        return this.size() == 0;
    }

    @Override
    public boolean containsKey(Object key) { // DONE
        return this.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) { // DONE
        return this.containsValue(value);
    }

    @Override
    public Location get(Object key) {
        return this.get(key);
    }

    @Override
    public Location put(Integer key, Location value) {
        return this.put(key, value);
    }

    @Override
    public Location remove(Object key) {
        return this.remove(key);
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends Location> m) {
        this.putAll(m);
    }

    @Override
    public void clear() {
        this.clear();
    }

    @Override
    public Set<Integer> keySet() {
        return this.keySet();
    }

    @Override
    public Collection<Location> values() {
        return this.values();
    }

    @Override
    public Set<Entry<Integer, Location>> entrySet() {
        return this.entrySet();
    }
}